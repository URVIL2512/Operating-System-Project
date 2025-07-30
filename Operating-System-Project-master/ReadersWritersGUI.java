import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;

public class ReadersWritersGUI extends JFrame {
    private JTextArea logArea;
    private JTextArea statusArea;
    private JButton addReaderButton, addWriterButton, startSimulationButton, stopSimulationButton;
    private JSpinner readerCountSpinner, writerCountSpinner;
    private volatile boolean simulationRunning = false;
    private ExecutorService executor;
    private int readerCount = 0;
    private int writerCount = 0;
    private final Object mutex = new Object();
    private final Semaphore writeMutex = new Semaphore(1);
    private final Semaphore readMutex = new Semaphore(1);
    
    public ReadersWritersGUI() {
        setTitle("Readers-Writers Problem with Semaphore");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(248, 249, 250)); // Professional Light Gray
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 58, 64)); // Bootstrap Dark
        JLabel titleLabel = new JLabel("Readers-Writers Problem with Semaphore", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Content panel
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        contentPanel.setBackground(new Color(248, 249, 250)); // Professional Light Gray
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Left panel - Controls
        JPanel controlPanel = createControlPanel();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(controlPanel);
        
        // Right panel - Status and Log
        JPanel statusPanel = createStatusPanel();
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(statusPanel);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Footer
        JPanel footerPanel = new JPanel(new FlowLayout());
        footerPanel.setBackground(new Color(52, 58, 64)); // Bootstrap Dark
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(231, 76, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20)); // Add button padding
        backButton.addActionListener(e -> {
            stopSimulation();
            dispose(); // Only close this window, don't open main GUI again
        });
        footerPanel.add(backButton);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Simulation Controls"));
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        
        inputPanel.add(new JLabel("Number of Readers:"));
        readerCountSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        inputPanel.add(readerCountSpinner);
        
        inputPanel.add(new JLabel("Number of Writers:"));
        writerCountSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
        inputPanel.add(writerCountSpinner);
        
        inputPanel.add(new JLabel(""));
        JButton setupButton = new JButton("Setup Simulation");
        setupButton.setBackground(new Color(52, 58, 64)); // Bootstrap Dark
        setupButton.setForeground(Color.WHITE);
        setupButton.setFont(new Font("Arial", Font.BOLD, 14));
        setupButton.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        setupButton.setFocusPainted(false);
        setupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setupButton.addActionListener(e -> setupSimulation());
        inputPanel.add(setupButton);
        
        panel.add(inputPanel, BorderLayout.NORTH);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        addReaderButton = new JButton("Add Reader");
        addReaderButton.setBackground(new Color(0, 123, 255)); // Bootstrap Blue
        addReaderButton.setForeground(Color.WHITE);
        addReaderButton.setFont(new Font("Arial", Font.BOLD, 16));
        addReaderButton.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        addReaderButton.setFocusPainted(false);
        addReaderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addReaderButton.setEnabled(false);
        addReaderButton.addActionListener(e -> addReader());
        buttonPanel.add(addReaderButton);
        
        addWriterButton = new JButton("Add Writer");
        addWriterButton.setBackground(new Color(102, 16, 242)); // Modern Purple
        addWriterButton.setForeground(Color.WHITE);
        addWriterButton.setFont(new Font("Arial", Font.BOLD, 16));
        addWriterButton.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        addWriterButton.setFocusPainted(false);
        addWriterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addWriterButton.setEnabled(false);
        addWriterButton.addActionListener(e -> addWriter());
        buttonPanel.add(addWriterButton);
        
        startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.setBackground(new Color(40, 167, 69)); // Bootstrap Green
        startSimulationButton.setForeground(Color.WHITE);
        startSimulationButton.setFont(new Font("Arial", Font.BOLD, 16));
        startSimulationButton.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        startSimulationButton.setFocusPainted(false);
        startSimulationButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startSimulationButton.setEnabled(false);
        startSimulationButton.addActionListener(e -> startSimulation());
        buttonPanel.add(startSimulationButton);
        
        stopSimulationButton = new JButton("Stop Simulation");
        stopSimulationButton.setBackground(new Color(220, 53, 69)); // Bootstrap Red
        stopSimulationButton.setForeground(Color.WHITE);
        stopSimulationButton.setFont(new Font("Arial", Font.BOLD, 16));
        stopSimulationButton.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        stopSimulationButton.setFocusPainted(false);
        stopSimulationButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stopSimulationButton.setEnabled(false);
        stopSimulationButton.addActionListener(e -> stopSimulation());
        buttonPanel.add(stopSimulationButton);
        
        panel.add(buttonPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Simulation Status"));
        
        // Status area
        statusArea = new JTextArea();
        statusArea.setEditable(false);
        statusArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        statusArea.setBackground(new Color(248, 248, 248));
        JScrollPane statusScrollPane = new JScrollPane(statusArea);
        statusScrollPane.setPreferredSize(new Dimension(300, 200));
        panel.add(statusScrollPane, BorderLayout.NORTH);
        
        // Log area
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane logScrollPane = new JScrollPane(logArea);
        panel.add(logScrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void setupSimulation() {
        readerCount = 0;
        writerCount = 0;
        statusArea.setText("Simulation ready!\nReaders: 0\nWriters: 0\n\nClick 'Start Simulation' to begin.");
        logArea.setText("");
        addReaderButton.setEnabled(true);
        addWriterButton.setEnabled(true);
        startSimulationButton.setEnabled(true);
        stopSimulationButton.setEnabled(false);
    }
    
    private void addReader() {
        readerCount++;
        updateStatus();
        log("Reader " + readerCount + " added to queue");
    }
    
    private void addWriter() {
        writerCount++;
        updateStatus();
        log("Writer " + writerCount + " added to queue");
    }
    
    private void updateStatus() {
        statusArea.setText(String.format("Simulation Status:\nReaders: %d\nWriters: %d\n\nClick 'Start Simulation' to begin.", 
            readerCount, writerCount));
    }
    
    private void startSimulation() {
        if (readerCount == 0 && writerCount == 0) {
            JOptionPane.showMessageDialog(this, "Please add at least one reader or writer!");
            return;
        }
        
        simulationRunning = true;
        executor = Executors.newCachedThreadPool();
        
        addReaderButton.setEnabled(false);
        addWriterButton.setEnabled(false);
        startSimulationButton.setEnabled(false);
        stopSimulationButton.setEnabled(true);
        
        log("=== Starting Readers-Writers Simulation ===");
        
        // Start readers
        for (int i = 1; i <= readerCount; i++) {
            final int readerId = i;
            executor.submit(() -> simulateReader(readerId));
        }
        
        // Start writers
        for (int i = 1; i <= writerCount; i++) {
            final int writerId = i;
            executor.submit(() -> simulateWriter(writerId));
        }
    }
    
    private void stopSimulation() {
        simulationRunning = false;
        if (executor != null) {
            executor.shutdownNow();
        }
        
        addReaderButton.setEnabled(true);
        addWriterButton.setEnabled(true);
        startSimulationButton.setEnabled(true);
        stopSimulationButton.setEnabled(false);
        
        log("=== Simulation Stopped ===");
    }
    
    private void simulateReader(int readerId) {
        try {
            while (simulationRunning) {
                // Reader wants to read
                log("Reader " + readerId + " wants to read");
                
                readMutex.acquire();
                synchronized (mutex) {
                    if (writerCount == 0) {
                        log("Reader " + readerId + " is reading");
                        Thread.sleep(2000 + new Random().nextInt(3000)); // Read for 2-5 seconds
                        log("Reader " + readerId + " finished reading");
                    } else {
                        log("Reader " + readerId + " waiting (writer active)");
                    }
                }
                readMutex.release();
                
                Thread.sleep(1000 + new Random().nextInt(2000)); // Wait before next read
            }
        } catch (InterruptedException e) {
            log("Reader " + readerId + " interrupted");
        }
    }
    
    private void simulateWriter(int writerId) {
        try {
            while (simulationRunning) {
                // Writer wants to write
                log("Writer " + writerId + " wants to write");
                
                writeMutex.acquire();
                synchronized (mutex) {
                    writerCount++;
                    log("Writer " + writerId + " is writing");
                    Thread.sleep(3000 + new Random().nextInt(4000)); // Write for 3-7 seconds
                    log("Writer " + writerId + " finished writing");
                    writerCount--;
                }
                writeMutex.release();
                
                Thread.sleep(2000 + new Random().nextInt(3000)); // Wait before next write
            }
        } catch (InterruptedException e) {
            log("Writer " + writerId + " interrupted");
        }
    }
    
    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append("[" + new Date() + "] " + message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ReadersWritersGUI().setVisible(true);
        });
    }
} 