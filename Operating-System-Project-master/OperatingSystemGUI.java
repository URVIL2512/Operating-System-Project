import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperatingSystemGUI extends JFrame {
    
    public OperatingSystemGUI() {
        setTitle("Operating System Algorithms");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Set up the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255)); // Light blue background
        
        // Create header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        JLabel titleLabel = new JLabel("Operating System Algorithms Project", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Create content panel
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        contentPanel.setBackground(new Color(240, 248, 255));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Left panel - Project information
        JPanel leftPanel = createLeftPanel();
        contentPanel.add(leftPanel);
        
        // Right panel - Algorithm selection
        JPanel rightPanel = createRightPanel();
        contentPanel.add(rightPanel);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        

        
        setContentPane(mainPanel);
    }
    
    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Available Algorithms"));
        
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        infoArea.setBackground(new Color(248, 248, 248));
        infoArea.setText(
            "1. PRIORITY PREEMPTIVE SCHEDULING\n" +
            "   • Schedules processes based on priority\n" +
            "   • Higher priority processes execute first\n" +
            "   • Preemptive - running process can be interrupted\n\n" +
            
            "2. READERS-WRITERS PROBLEM\n" +
            "   • Classic synchronization problem\n" +
            "   • Multiple readers can read simultaneously\n" +
            "   • Only one writer can write at a time\n\n" +
            
            "3. FCFS DISK SCHEDULING\n" +
            "   • First Come First Serve algorithm\n" +
            "   • Requests processed in arrival order\n" +
            "   • Simple but may not be optimal\n\n" +
            
            "4. LRU PAGE REPLACEMENT\n" +
            "   • Least Recently Used algorithm\n" +
            "   • Replaces least recently used page\n" +
            "   • Effective for memory management\n\n" +
            
            "Select an algorithm from the right panel and click 'Launch Algorithm' to begin."
        );
        
        JScrollPane scrollPane = new JScrollPane(infoArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Select Algorithm"));
        
        // Selection panel
        JPanel selectionPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        selectionPanel.setBackground(Color.WHITE);
        
        String[] algorithms = {
            "Priority Preemptive Scheduling",
            "Readers-Writers Problem",
            "FCFS Disc Scheduling",
            "LRU Algorithm"
        };
        
        ButtonGroup buttonGroup = new ButtonGroup();
        
        for (int i = 0; i < algorithms.length; i++) {
            JRadioButton radioButton = new JRadioButton(algorithms[i]);
            radioButton.setFont(new Font("Arial", Font.PLAIN, 14));
            radioButton.setBackground(Color.WHITE);
            final int index = i;
            radioButton.addActionListener(e -> {
                // Handle selection
                System.out.println("Selected: " + algorithms[index]);
            });
            buttonGroup.add(radioButton);
            selectionPanel.add(radioButton);
            
            // Select first option by default
            if (i == 0) radioButton.setSelected(true);
        }
        
        panel.add(selectionPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        
        JButton launchButton = new JButton("Launch Algorithm");
        launchButton.setFont(new Font("Arial", Font.BOLD, 14));
        launchButton.setBackground(new Color(70, 130, 180));
        launchButton.setForeground(Color.WHITE);
        launchButton.addActionListener(e -> {
            // Get selected algorithm
            for (int i = 0; i < selectionPanel.getComponentCount(); i++) {
                JRadioButton rb = (JRadioButton) selectionPanel.getComponent(i);
                if (rb.isSelected()) {
                    launchAlgorithm(rb.getText());
                    break;
                }
            }
        });
        
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBackground(new Color(220, 20, 60));
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to exit?", "Exit", 
                JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        
        buttonPanel.add(launchButton);
        buttonPanel.add(exitButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void launchAlgorithm(String algorithm) {
        try {
            switch (algorithm) {
                case "Priority Preemptive Scheduling":
                    new PrioritySchedulingGUI().setVisible(true);
                    break;
                case "Readers-Writers Problem":
                    new ReadersWritersGUI().setVisible(true);
                    break;
                case "FCFS Disc Scheduling":
                    new FCFSDiskSchedulingGUI().setVisible(true);
                    break;
                case "LRU Algorithm":
                    new LRUPageReplacementGUI().setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, 
                        "Algorithm not implemented yet: " + algorithm, 
                        "Not Available", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error launching " + algorithm + ":\n" + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OperatingSystemGUI frame = new OperatingSystemGUI();
            frame.setVisible(true);
            System.out.println("Operating System GUI is now running!");
        });
    }
} 