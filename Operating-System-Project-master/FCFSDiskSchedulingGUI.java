import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class FCFSDiskSchedulingGUI extends JFrame {
    private JTextArea outputArea;
    private JTextField requestField, headPositionField;
    private JButton addRequestButton, runSchedulingButton, clearButton;
    private ArrayList<Integer> requests = new ArrayList<>();
    private int headPosition = 50;
    private JPanel diskPanel;
    
    public FCFSDiskSchedulingGUI() {
        setTitle("FCFS Disk Scheduling Algorithm");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 128, 128)); // Teal
        JLabel titleLabel = new JLabel("FCFS (First Come First Serve) Disk Scheduling", SwingConstants.CENTER);
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
        
        // Center panel - Visual representation
        JPanel visualPanel = createVisualPanel();
        contentPanel.add(visualPanel);
        
        // Right panel - Output
        JPanel outputPanel = createOutputPanel();
        contentPanel.add(outputPanel);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Footer
        JPanel footerPanel = new JPanel(new FlowLayout());
        footerPanel.setBackground(new Color(0, 128, 128)); // Teal
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
        panel.setBorder(BorderFactory.createTitledBorder("Disk Request Input"));
        
        // Input fields
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        fieldsPanel.setBackground(Color.WHITE);
        
        fieldsPanel.add(new JLabel("Head Position:"));
        headPositionField = new JTextField();
        fieldsPanel.add(headPositionField);
        
        fieldsPanel.add(new JLabel("Disk Request:"));
        requestField = new JTextField();
        fieldsPanel.add(requestField);
        
        fieldsPanel.add(new JLabel(""));
        addRequestButton = new JButton("Add Request");
        addRequestButton.setBackground(new Color(0, 150, 136)); // Dark Teal
        addRequestButton.setForeground(Color.WHITE);
        addRequestButton.addActionListener(e -> addRequest());
        fieldsPanel.add(addRequestButton);
        
        panel.add(fieldsPanel, BorderLayout.NORTH);
        
        // Request list
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Request Queue"));
        
        JTextArea requestList = new JTextArea();
        requestList.setEditable(false);
        requestList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(requestList);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        
        clearButton = new JButton("Clear All");
        clearButton.setBackground(new Color(231, 76, 60));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(e -> {
            requests.clear();
            headPosition = 0;
            headPositionField.setText("");
            updateRequestListDisplay("");
            outputArea.setText("");
            updateDiskVisualization();
        });
        listPanel.add(clearButton, BorderLayout.SOUTH);
        
        panel.add(listPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createVisualPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Disk Visualization"));
        
        diskPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = getWidth();
                int height = getHeight();
                int centerX = width / 2;
                int centerY = height / 2;
                
                // Draw disk tracks
                g2d.setColor(Color.LIGHT_GRAY);
                for (int i = 0; i < 8; i++) {
                    int radius = 30 + i * 25;
                    g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
                }
                
                // Draw head position
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(3));
                int headRadius = 80;
                int headAngle = (headPosition * 360) / 200; // Scale to 0-200 range
                int headX = centerX + (int) (headRadius * Math.cos(Math.toRadians(headAngle)));
                int headY = centerY - (int) (headRadius * Math.sin(Math.toRadians(headAngle)));
                g2d.fillOval(headX - 8, headY - 8, 16, 16);
                
                // Draw request positions
                g2d.setColor(Color.BLUE);
                for (int i = 0; i < requests.size(); i++) {
                    int request = requests.get(i);
                    int angle = (request * 360) / 200;
                    int radius = 60 + (i % 3) * 20;
                    int x = centerX + (int) (radius * Math.cos(Math.toRadians(angle)));
                    int y = centerY - (int) (radius * Math.sin(Math.toRadians(angle)));
                    g2d.fillOval(x - 5, y - 5, 10, 10);
                }
                
                // Draw legend
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.PLAIN, 12));
                g2d.drawString("Red: Head Position", 10, height - 60);
                g2d.drawString("Blue: Requests", 10, height - 40);
                g2d.drawString("Head: " + headPosition, 10, height - 20);
            }
        };
        
        panel.add(diskPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Scheduling Results"));
        
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        runSchedulingButton = new JButton("Run FCFS Scheduling");
        runSchedulingButton.setFont(new Font("Arial", Font.BOLD, 14));
        runSchedulingButton.setBackground(new Color(0, 105, 148)); // Darker Teal
        runSchedulingButton.setForeground(Color.WHITE);
        runSchedulingButton.addActionListener(e -> runFCFSScheduling());
        panel.add(runSchedulingButton, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void addRequest() {
        try {
            int request = Integer.parseInt(requestField.getText());
            if (request < 0 || request > 200) {
                JOptionPane.showMessageDialog(this, "Request must be between 0 and 200!");
                return;
            }
            
            requests.add(request);
            requestField.setText("");
            updateDiskVisualization();
            
            // Update request list
            StringBuilder sb = new StringBuilder();
            sb.append("Request Queue:\n");
            sb.append("-".repeat(20)).append("\n");
            for (int i = 0; i < requests.size(); i++) {
                sb.append(String.format("%d. %d\n", i + 1, requests.get(i)));
            }
            
            // Update the request list display - simplified
            updateRequestListDisplay(sb.toString());
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!");
        }
    }
    
    private void updateDiskVisualization() {
        diskPanel.repaint();
    }
    
    private void runFCFSScheduling() {
        if (requests.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one disk request!");
            return;
        }
        
        try {
            headPosition = Integer.parseInt(headPositionField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid head position!");
            return;
        }
        
        StringBuilder result = new StringBuilder();
        result.append("FCFS DISK SCHEDULING RESULTS\n");
        result.append("=".repeat(40)).append("\n\n");
        
        result.append("Initial Head Position: ").append(headPosition).append("\n\n");
        result.append("Request Sequence: ");
        for (int i = 0; i < requests.size(); i++) {
            result.append(requests.get(i));
            if (i < requests.size() - 1) result.append(" → ");
        }
        result.append("\n\n");
        
        result.append("Head Movement:\n");
        result.append("-".repeat(20)).append("\n");
        
        int currentPosition = headPosition;
        int totalMovement = 0;
        
        for (int i = 0; i < requests.size(); i++) {
            int request = requests.get(i);
            int movement = Math.abs(currentPosition - request);
            totalMovement += movement;
            
            result.append(String.format("%d → %d (Movement: %d)\n", 
                currentPosition, request, movement));
            
            currentPosition = request;
        }
        
        result.append("\n").append("=".repeat(40)).append("\n");
        result.append("PERFORMANCE METRICS:\n");
        result.append("-".repeat(20)).append("\n");
        result.append(String.format("Total Head Movement: %d cylinders\n", totalMovement));
        result.append(String.format("Average Seek Time: %.2f ms\n", totalMovement * 0.1));
        result.append(String.format("Throughput: %.2f requests/second\n", 
            (double) requests.size() / (totalMovement * 0.001)));
        
        outputArea.setText(result.toString());
        updateDiskVisualization();
    }
    
    private void updateRequestListDisplay(String text) {
        // This method will be called to update the request list display
        // For now, we'll just print to console
        System.out.println("Request List Updated:");
        System.out.println(text);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FCFSDiskSchedulingGUI().setVisible(true);
        });
    }
} 