import javax.swing.*;
import java.awt.*;

public class TestGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test GUI");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            
            JLabel label = new JLabel("GUI is working! Operating System Project is ready.");
            label.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(label);
            
            frame.add(panel);
            frame.setVisible(true);
            
            System.out.println("GUI window should be visible now!");
        });
    }
} 