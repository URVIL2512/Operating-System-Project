import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LRUPageReplacementGUI extends JFrame {
    private JTextArea outputArea;
    private JTextField pageField, frameCountField;
    private JButton addPageButton, runLRUButton, clearButton;
    private ArrayList<Integer> pageRequests = new ArrayList<>();
    private int frameCount = 3;
    private JPanel memoryPanel;
    
    public LRUPageReplacementGUI() {
        setTitle("LRU Page Replacement Algorithm");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(139, 69, 19)); // Saddle Brown
        JLabel titleLabel = new JLabel("LRU (Least Recently Used) Page Replacement", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Content panel
        JPanel contentPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Left panel - Input
        JPanel inputPanel = createInputPanel();
        contentPanel.add(inputPanel);
        
        // Center panel - Memory visualization
        JPanel visualPanel = createVisualPanel();
        contentPanel.add(visualPanel);
        
        // Right panel - Output
        JPanel outputPanel = createOutputPanel();
        contentPanel.add(outputPanel);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Footer
        JPanel footerPanel = new JPanel(new FlowLayout());
        footerPanel.setBackground(new Color(139, 69, 19)); // Saddle Brown
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(231, 76, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20)); // Add button padding
        backButton.addActionListener(e -> {
            dispose(); // Only close this window, don't open main GUI again
        });
        footerPanel.add(backButton);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Page Request Input"));
        
        // Input fields
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        fieldsPanel.setBackground(Color.WHITE);
        
        fieldsPanel.add(new JLabel("Number of Frames:"));
        frameCountField = new JTextField();
        fieldsPanel.add(frameCountField);
        
        fieldsPanel.add(new JLabel("Page Number:"));
        pageField = new JTextField();
        fieldsPanel.add(pageField);
        
        fieldsPanel.add(new JLabel(""));
        addPageButton = new JButton("Add Page Request");
        addPageButton.setBackground(new Color(160, 82, 45)); // Saddle Brown
        addPageButton.setForeground(Color.WHITE);
        addPageButton.addActionListener(e -> addPageRequest());
        fieldsPanel.add(addPageButton);
        
        panel.add(fieldsPanel, BorderLayout.NORTH);
        
        // Page request list
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Page Request Sequence"));
        
        JTextArea pageList = new JTextArea();
        pageList.setEditable(false);
        pageList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(pageList);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        
        clearButton = new JButton("Clear All");
        clearButton.setBackground(new Color(231, 76, 60));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(e -> {
            pageRequests.clear();
            frameCount = 0;
            frameCountField.setText("");
            updatePageListDisplay("");
            outputArea.setText("");
            updateMemoryVisualization();
        });
        listPanel.add(clearButton, BorderLayout.SOUTH);
        
        panel.add(listPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createVisualPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Memory Frame Visualization"));
        
        memoryPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = getWidth();
                int height = getHeight();
                int frameWidth = width / frameCount;
                
                // Draw memory frames
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.setStroke(new BasicStroke(2));
                
                for (int i = 0; i < frameCount; i++) {
                    int x = i * frameWidth;
                    g2d.drawRect(x + 10, 50, frameWidth - 20, height - 100);
                    g2d.drawString("Frame " + i, x + 20, 30);
                }
                
                // Draw pages in frames (simplified visualization)
                g2d.setColor(Color.BLUE);
                g2d.setFont(new Font("Arial", Font.BOLD, 16));
                
                for (int i = 0; i < Math.min(frameCount, pageRequests.size()); i++) {
                    int x = i * frameWidth;
                    int pageNum = pageRequests.get(i);
                    g2d.drawString("Page " + pageNum, x + 20, height / 2);
                }
                
                // Draw legend
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.PLAIN, 12));
                g2d.drawString("Memory Frames: " + frameCount, 10, height - 40);
                g2d.drawString("Page Requests: " + pageRequests.size(), 10, height - 20);
            }
        };
        
        panel.add(memoryPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("LRU Algorithm Results"));
        
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        runLRUButton = new JButton("Run LRU Algorithm");
        runLRUButton.setFont(new Font("Arial", Font.BOLD, 14));
        runLRUButton.setBackground(new Color(139, 69, 19)); // Saddle Brown
        runLRUButton.setForeground(Color.WHITE);
        runLRUButton.addActionListener(e -> runLRUAlgorithm());
        panel.add(runLRUButton, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void addPageRequest() {
        try {
            int page = Integer.parseInt(pageField.getText());
            if (page < 0) {
                JOptionPane.showMessageDialog(this, "Page number must be non-negative!");
                return;
            }
            
            pageRequests.add(page);
            pageField.setText("");
            updateMemoryVisualization();
            
            // Update page request list
            StringBuilder sb = new StringBuilder();
            sb.append("Page Request Sequence:\n");
            sb.append("-".repeat(30)).append("\n");
            for (int i = 0; i < pageRequests.size(); i++) {
                sb.append(String.format("%d. Page %d\n", i + 1, pageRequests.get(i)));
            }
            
            // Update the page list display - simplified
            updatePageListDisplay(sb.toString());
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid page number!");
        }
    }
    
    private void updateMemoryVisualization() {
        try {
            frameCount = Integer.parseInt(frameCountField.getText());
        } catch (NumberFormatException ex) {
            frameCount = 3;
        }
        memoryPanel.repaint();
    }
    
    private void runLRUAlgorithm() {
        if (pageRequests.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one page request!");
            return;
        }
        
        try {
            frameCount = Integer.parseInt(frameCountField.getText());
            if (frameCount <= 0) {
                JOptionPane.showMessageDialog(this, "Number of frames must be positive!");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of frames!");
            return;
        }
        
        StringBuilder result = new StringBuilder();
        result.append("LRU PAGE REPLACEMENT RESULTS\n");
        result.append("=".repeat(40)).append("\n\n");
        
        result.append("Number of Frames: ").append(frameCount).append("\n");
        result.append("Page Request Sequence: ");
        for (int i = 0; i < pageRequests.size(); i++) {
            result.append(pageRequests.get(i));
            if (i < pageRequests.size() - 1) result.append(" → ");
        }
        result.append("\n\n");
        
        // LRU Algorithm simulation
        LinkedList<Integer> frames = new LinkedList<>();
        int pageFaults = 0;
        
        result.append("Page Replacement Process:\n");
        result.append("-".repeat(30)).append("\n");
        
        for (int i = 0; i < pageRequests.size(); i++) {
            int page = pageRequests.get(i);
            
            if (frames.contains(page)) {
                // Page hit - move to end (most recently used)
                frames.remove(Integer.valueOf(page));
                frames.addLast(page);
                result.append(String.format("Page %d: HIT (Frames: %s)\n", page, frames));
            } else {
                // Page fault
                pageFaults++;
                if (frames.size() >= frameCount) {
                    // Remove least recently used page (first in list)
                    int removedPage = frames.removeFirst();
                    result.append(String.format("Page %d: FAULT - Replace Page %d (Frames: %s)\n", 
                        page, removedPage, frames));
                } else {
                    result.append(String.format("Page %d: FAULT - Add to empty frame (Frames: %s)\n", 
                        page, frames));
                }
                frames.addLast(page);
            }
        }
        
        result.append("\n").append("=".repeat(40)).append("\n");
        result.append("PERFORMANCE METRICS:\n");
        result.append("-".repeat(20)).append("\n");
        result.append(String.format("Total Page Requests: %d\n", pageRequests.size()));
        result.append(String.format("Page Faults: %d\n", pageFaults));
        result.append(String.format("Page Hits: %d\n", pageRequests.size() - pageFaults));
        result.append(String.format("Page Fault Rate: %.2f%%\n", 
            (double) pageFaults / pageRequests.size() * 100));
        result.append(String.format("Page Hit Rate: %.2f%%\n", 
            (double) (pageRequests.size() - pageFaults) / pageRequests.size() * 100));
        
        outputArea.setText(result.toString());
        updateMemoryVisualization();
    }
    
    private void updatePageListDisplay(String text) {
        // This method will be called to update the page list display
        // For now, we'll just print to console
        System.out.println("Page List Updated:");
        System.out.println(text);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LRUPageReplacementGUI().setVisible(true);
        });
    }
} 