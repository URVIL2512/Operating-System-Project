import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PrioritySchedulingGUI extends JFrame {
    private JTextArea outputArea;
    private JTextField processNameField, burstTimeField, priorityField;
    private ArrayList<Process> processes = new ArrayList<>();
    private int processId = 1;
    
    public PrioritySchedulingGUI() {
        setTitle("Priority Preemptive Scheduling Algorithm");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 73, 94));
        JLabel titleLabel = new JLabel("Priority Preemptive Scheduling Algorithm", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Content panel
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Left panel - Input
        JPanel inputPanel = createInputPanel();
        contentPanel.add(inputPanel);
        
        // Right panel - Output
        JPanel outputPanel = createOutputPanel();
        contentPanel.add(outputPanel);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Footer
        JPanel footerPanel = new JPanel(new FlowLayout());
        footerPanel.setBackground(new Color(52, 73, 94));
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
        panel.setBorder(BorderFactory.createTitledBorder("Process Input"));
        
        // Input fields panel
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        fieldsPanel.setBackground(Color.WHITE);
        
        fieldsPanel.add(new JLabel("Process Name:"));
        processNameField = new JTextField();
        fieldsPanel.add(processNameField);
        
        fieldsPanel.add(new JLabel("Burst Time:"));
        burstTimeField = new JTextField();
        fieldsPanel.add(burstTimeField);
        
        fieldsPanel.add(new JLabel("Priority (1=Highest):"));
        priorityField = new JTextField();
        fieldsPanel.add(priorityField);
        
        fieldsPanel.add(new JLabel(""));
        JButton addButton = new JButton("Add Process");
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> addProcess());
        fieldsPanel.add(addButton);
        
        panel.add(fieldsPanel, BorderLayout.NORTH);
        
        // Process list
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Added Processes"));
        
        JTextArea processList = new JTextArea();
        processList.setEditable(false);
        processList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(processList);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        
        JButton clearButton = new JButton("Clear All");
        clearButton.setBackground(new Color(231, 76, 60));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(e -> {
            processes.clear();
            processNameField.setText("");
            processList.setText("");
            outputArea.setText("");
        });
        listPanel.add(clearButton, BorderLayout.SOUTH);
        
        panel.add(listPanel, BorderLayout.CENTER);
        
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
        
        JButton runButton = new JButton("Run Scheduling");
        runButton.setFont(new Font("Arial", Font.BOLD, 14));
        runButton.setBackground(new Color(52, 152, 219));
        runButton.setForeground(Color.WHITE);
        runButton.addActionListener(e -> runScheduling());
        panel.add(runButton, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void addProcess() {
        try {
            String name = processNameField.getText();
            int burstTime = Integer.parseInt(burstTimeField.getText());
            int priority = Integer.parseInt(priorityField.getText());
            
            if (burstTime <= 0 || priority <= 0) {
                JOptionPane.showMessageDialog(this, "Burst time and priority must be positive integers!");
                return;
            }
            
            processes.add(new Process(name, burstTime, priority));
            processNameField.setText("");
            burstTimeField.setText("");
            priorityField.setText("");
            
            updateProcessList();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for burst time and priority!");
        }
    }
    
    private void updateProcessList() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-12s %-10s\n", "Process", "Burst Time", "Priority"));
        sb.append("-".repeat(35)).append("\n");
        
        for (Process p : processes) {
            sb.append(String.format("%-10s %-12d %-10d\n", p.name, p.burstTime, p.priority));
        }
        
        // Update the process list display - simplified
        updateProcessListDisplay(sb.toString());
    }
    
    private void updateProcessListDisplay(String text) {
        // This method will be called to update the process list display
        // For now, we'll just print to console
        System.out.println("Process List Updated:");
        System.out.println(text);
    }
    
    private void runScheduling() {
        if (processes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one process!");
            return;
        }
        
        // Priority preemptive scheduling simulation
        ArrayList<Process> readyQueue = new ArrayList<>(processes);
        readyQueue.sort((p1, p2) -> Integer.compare(p1.priority, p2.priority)); // Lower priority number = higher priority
        
        StringBuilder result = new StringBuilder();
        result.append("PRIORITY PREEMPTIVE SCHEDULING RESULTS\n");
        result.append("=".repeat(50)).append("\n\n");
        
        result.append("Process Execution Order:\n");
        result.append("-".repeat(30)).append("\n");
        
        int currentTime = 0;
        int totalTurnaroundTime = 0;
        int totalWaitingTime = 0;
        
        while (!readyQueue.isEmpty()) {
            Process current = readyQueue.remove(0);
            result.append(String.format("Time %d: %s (Priority: %d, Burst: %d)\n", 
                currentTime, current.name, current.priority, current.burstTime));
            
            totalTurnaroundTime += currentTime + current.burstTime;
            totalWaitingTime += currentTime;
            currentTime += current.burstTime;
        }
        
        result.append("\n").append("=".repeat(50)).append("\n");
        result.append("PERFORMANCE METRICS:\n");
        result.append("-".repeat(20)).append("\n");
        result.append(String.format("Total Execution Time: %d units\n", currentTime));
        result.append(String.format("Average Turnaround Time: %.2f units\n", 
            (double) totalTurnaroundTime / processes.size()));
        result.append(String.format("Average Waiting Time: %.2f units\n", 
            (double) totalWaitingTime / processes.size()));
        
        outputArea.setText(result.toString());
    }
    
    private static class Process {
        String name;
        int burstTime;
        int priority;
        
        Process(String name, int burstTime, int priority) {
            this.name = name;
            this.burstTime = burstTime;
            this.priority = priority;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PrioritySchedulingGUI().setVisible(true);
        });
    }
} 