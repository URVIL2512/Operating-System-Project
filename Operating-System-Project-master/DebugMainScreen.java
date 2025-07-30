import javax.swing.*;
import java.awt.*;

public class DebugMainScreen extends JFrame {
    
    public DebugMainScreen() {
        super("Operating System Projects - DEBUG");
        System.out.println("Creating GUI window...");
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Make sure window is visible
        setVisible(true);
        toFront();
        requestFocus();
        
        System.out.println("GUI window created and should be visible!");
        
        // Simple content
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        
        JLabel label = new JLabel("Operating System Project is Running!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label);
        
        JButton testButton = new JButton("Test Button - Click Me!");
        testButton.addActionListener(e -> {
            System.out.println("Button clicked! GUI is working!");
            JOptionPane.showMessageDialog(this, "GUI is working correctly!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(testButton);
        
        setContentPane(panel);
        
        System.out.println("Window should be visible on your screen now!");
        System.out.println("If you don't see it, check your taskbar or try Alt+Tab");
    }
    
    public static void main(String[] args) {
        System.out.println("Starting DebugMainScreen...");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));
        
        SwingUtilities.invokeLater(() -> {
            System.out.println("Creating GUI in SwingUtilities.invokeLater...");
            DebugMainScreen frame = new DebugMainScreen();
            System.out.println("GUI creation completed!");
        });
        
        System.out.println("Main method completed. GUI should be running...");
    }
} 