import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    
    private JRadioButton priorityButton;
    private JRadioButton readersWritersButton;
    private JRadioButton fcfsButton;
    private JRadioButton lruButton;
    private ButtonGroup algorithmGroup;
    
    public MainMenu() {
        setTitle("Operating System Algorithms Project");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(800, 60));
        
        JLabel headerLabel = new JLabel("Operating System Algorithms");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);
        
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Available Algorithms"));
        
        JTextArea algorithmDescriptions = new JTextArea();
        algorithmDescriptions.setEditable(false);
        algorithmDescriptions.setFont(new Font("Arial", Font.PLAIN, 12));
        algorithmDescriptions.setText(
            "PRIORITY PREEMPTIVE SCHEDULING\n" +
            "• Schedules processes based on priority\n" +
            "• Higher priority processes execute first\n" +
            "• Preemptive - running process can be interrupted\n" +
            "• Professional GUI with real-time visualization\n\n" +
            
            "READERS-WRITERS PROBLEM\n" +
            "• Classic synchronization problem\n" +
            "• Multiple readers can read simultaneously\n" +
            "• Only one writer can write at a time\n" +
            "• Semaphore-based implementation\n\n" +
            
            "FCFS DISK SCHEDULING\n" +
            "• First Come First Serve algorithm\n" +
            "• Requests processed in arrival order\n" +
            "• Simple but may not be optimal\n" +
            "• Visual disk track representation\n\n" +
            
            "LRU PAGE REPLACEMENT\n" +
            "• Least Recently Used algorithm\n" +
            "• Replaces least recently used page\n" +
            "• Effective for memory management\n" +
            "• Memory frame visualization\n\n" +
            
            "Select an algorithm from the right panel and click 'Launch Algorithm' to begin."
        );
        
        JScrollPane scrollPane = new JScrollPane(algorithmDescriptions);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Select Algorithm"));
        
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        
        priorityButton = new JRadioButton("Priority Preemptive Scheduling");
        readersWritersButton = new JRadioButton("Readers-Writers Problem");
        fcfsButton = new JRadioButton("FCFS Disc Scheduling");
        lruButton = new JRadioButton("LRU Algorithm");
        
        priorityButton.setSelected(true);
        
        algorithmGroup = new ButtonGroup();
        algorithmGroup.add(priorityButton);
        algorithmGroup.add(readersWritersButton);
        algorithmGroup.add(fcfsButton);
        algorithmGroup.add(lruButton);
        
        selectionPanel.add(priorityButton);
        selectionPanel.add(Box.createVerticalStrut(10));
        selectionPanel.add(readersWritersButton);
        selectionPanel.add(Box.createVerticalStrut(10));
        selectionPanel.add(fcfsButton);
        selectionPanel.add(Box.createVerticalStrut(10));
        selectionPanel.add(lruButton);
        selectionPanel.add(Box.createVerticalGlue());
        
        rightPanel.add(selectionPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton launchButton = new JButton("Launch Algorithm");
        launchButton.setBackground(new Color(52, 152, 219));
        launchButton.setForeground(Color.WHITE);
        launchButton.setFont(new Font("Arial", Font.BOLD, 12));
        launchButton.addActionListener(e -> launchSelectedAlgorithm());
        
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(231, 76, 60));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 12));
        exitButton.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(launchButton);
        buttonPanel.add(exitButton);
        
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void launchSelectedAlgorithm() {
        if (priorityButton.isSelected()) {
            new PrioritySchedulingGUI().setVisible(true);
        } else if (readersWritersButton.isSelected()) {
            new ReadersWritersGUI().setVisible(true);
        } else if (fcfsButton.isSelected()) {
            new FCFSDiskSchedulingGUI().setVisible(true);
        } else if (lruButton.isSelected()) {
            new LRUPageReplacementGUI().setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
} 