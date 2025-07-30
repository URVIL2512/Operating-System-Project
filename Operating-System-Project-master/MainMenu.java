import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    
    public MainMenu() {
        setTitle("Operating System Algorithms - Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255)); // Light blue background
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        JLabel titleLabel = new JLabel("Operating System Algorithms", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Welcome message
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(240, 248, 255));
        JLabel welcomeLabel = new JLabel("Welcome to Operating System Algorithms Project!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(70, 130, 180));
        welcomePanel.add(welcomeLabel);
        mainPanel.add(welcomePanel, BorderLayout.CENTER);
        
        // Algorithm buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonsPanel.setBackground(new Color(240, 248, 255));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        // Create algorithm buttons
        JButton priorityButton = createAlgorithmButton("Priority Preemptive Scheduling", 
            "Schedule processes based on priority", new Color(52, 73, 94));
        JButton readersWritersButton = createAlgorithmButton("Readers-Writers Problem", 
            "Classic synchronization problem", new Color(155, 89, 182));
        JButton fcfsButton = createAlgorithmButton("FCFS Disk Scheduling", 
            "First Come First Serve algorithm", new Color(39, 174, 96));
        JButton lruButton = createAlgorithmButton("LRU Page Replacement", 
            "Least Recently Used algorithm", new Color(230, 126, 34));
        
        // Add action listeners
        priorityButton.addActionListener(e -> {
            new PrioritySchedulingGUI().setVisible(true);
        });
        
        readersWritersButton.addActionListener(e -> {
            new ReadersWritersGUI().setVisible(true);
        });
        
        fcfsButton.addActionListener(e -> {
            new FCFSDiskSchedulingGUI().setVisible(true);
        });
        
        lruButton.addActionListener(e -> {
            new LRUPageReplacementGUI().setVisible(true);
        });
        
        // Add buttons to panel
        buttonsPanel.add(priorityButton);
        buttonsPanel.add(readersWritersButton);
        buttonsPanel.add(fcfsButton);
        buttonsPanel.add(lruButton);
        
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
    }
    
    private JButton createAlgorithmButton(String title, String description, Color color) {
        JButton button = new JButton("<html><center><b>" + title + "</b><br><small>" + description + "</small></center></html>");
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
            System.out.println("Main Menu is now running!");
        });
    }
} 