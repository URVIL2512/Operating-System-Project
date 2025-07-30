import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleMainScreen extends JFrame {
    
    public SimpleMainScreen() {
        super("Operating System Projects");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Title
        JLabel titleLabel = new JLabel("Operating System Projects", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Content panel
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Left panel - Project list
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Available Projects"));
        
        JTextArea projectList = new JTextArea();
        projectList.setEditable(false);
        projectList.setFont(new Font("Arial", Font.PLAIN, 14));
        projectList.setText(
            "1. Priority (Preemptive) Scheduling Algorithm\n\n" +
            "2. Readers-Writers Problem with Semaphore\n\n" +
            "3. FCFS Disk Scheduling Algorithm\n\n" +
            "4. Least Recently Used (LRU) Page Replacement Algorithm"
        );
        projectList.setBackground(new Color(240, 240, 240));
        
        JScrollPane scrollPane = new JScrollPane(projectList);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Team members
        JTextArea teamMembers = new JTextArea();
        teamMembers.setEditable(false);
        teamMembers.setFont(new Font("Arial", Font.PLAIN, 12));
        teamMembers.setText("Team Members:\n\n1. Ayush Patel\n2. Ayush Prajapati\n3. Urvil Solanki\n4. Achyut Koul\n5. Bhavya Patel");
        teamMembers.setBackground(new Color(240, 240, 240));
        
        JPanel teamPanel = new JPanel(new BorderLayout());
        teamPanel.add(teamMembers, BorderLayout.CENTER);
        teamPanel.setBorder(BorderFactory.createTitledBorder("Team Members"));
        
        JPanel leftContent = new JPanel(new BorderLayout());
        leftContent.add(leftPanel, BorderLayout.CENTER);
        leftContent.add(teamPanel, BorderLayout.SOUTH);
        
        // Right panel - Selection
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(250, 250, 250));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Select Project"));
        
        JLabel selectLabel = new JLabel("Please choose a project to view:", SwingConstants.CENTER);
        selectLabel.setFont(new Font("Arial", Font.BOLD, 16));
        selectLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        rightPanel.add(selectLabel, BorderLayout.NORTH);
        
        String[] options = {"Priority Preemptive", "Reader Writer", "FCFS Disc Scheduling", "LRU Algorithm"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        rightPanel.add(comboBox, BorderLayout.CENTER);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton proceedButton = new JButton("Launch Project");
        JButton exitButton = new JButton("Exit");
        
        proceedButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProject = (String) comboBox.getSelectedItem();
                try {
                    if (selectedProject.equals("Priority Preemptive")) {
                        new Information();
                        dispose();
                    } else if (selectedProject.equals("Reader Writer")) {
                        new GUI();
                        dispose();
                    } else if (selectedProject.equals("FCFS Disc Scheduling")) {
                        new InformationAboutDiscScheduling();
                        dispose();
                    } else if (selectedProject.equals("LRU Algorithm")) {
                        new introClass();
                        dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SimpleMainScreen.this, 
                        "Error launching " + selectedProject + ": " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SimpleMainScreen.this, 
                    "Thank you for using our Operating System Project!", 
                    "Goodbye", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        
        buttonPanel.add(proceedButton);
        buttonPanel.add(exitButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        contentPanel.add(leftContent);
        contentPanel.add(rightPanel);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleMainScreen frame = new SimpleMainScreen();
            frame.setVisible(true);
            System.out.println("Operating System Project GUI is now visible!");
        });
    }
} 