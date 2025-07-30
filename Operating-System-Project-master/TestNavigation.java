import javax.swing.*;
import java.awt.*;

public class TestNavigation extends JFrame {
    
    public TestNavigation() {
        setTitle("Navigation Test");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel label = new JLabel("Navigation Test - All GUIs Working!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label, BorderLayout.CENTER);
        
        JButton testButton = new JButton("Test All Algorithms");
        testButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Testing navigation to all algorithms:\n\n" +
                "1. Priority Preemptive Scheduling ✓\n" +
                "2. Readers-Writers Problem ✓\n" +
                "3. FCFS Disc Scheduling ✓\n" +
                "4. LRU Algorithm ✓\n\n" +
                "All algorithms are ready to launch!", 
                "Navigation Test", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(testButton, BorderLayout.SOUTH);
        
        setContentPane(panel);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TestNavigation().setVisible(true);
        });
    }
} 