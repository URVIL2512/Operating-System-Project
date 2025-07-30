import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FCFSChartExample extends JFrame {
    public FCFSChartExample() {
        super("FCFS Disk Scheduling Simulation");

        setLayout(new BorderLayout());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a simple panel to show FCFS disk scheduling
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("FCFS Disk Scheduling Algorithm", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a simple visualization panel
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw a simple representation of disk scheduling
                int width = getWidth();
                int height = getHeight();
                int centerX = width / 2;
                int centerY = height / 2;

                // Draw disk tracks
                g2d.setColor(Color.LIGHT_GRAY);
                for (int i = 0; i < 5; i++) {
                    int radius = 50 + i * 30;
                    g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
                }

                // Draw FCFS sequence
                int[] sequence = {50, 100, 25, 75};
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(3));
                
                int prevX = centerX;
                int prevY = centerY - 50;
                
                for (int i = 0; i < sequence.length; i++) {
                    int angle = (sequence[i] * 360) / 100;
                    int radius = 80;
                    int x = centerX + (int) (radius * Math.cos(Math.toRadians(angle)));
                    int y = centerY - (int) (radius * Math.sin(Math.toRadians(angle)));
                    
                    g2d.fillOval(x - 5, y - 5, 10, 10);
                    if (i > 0) {
                        g2d.drawLine(prevX, prevY, x, y);
                    }
                    prevX = x;
                    prevY = y;
                }

                // Add labels
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.PLAIN, 12));
                g2d.drawString("FCFS Sequence: 50 → 100 → 25 → 75", 20, height - 40);
                g2d.drawString("Total Head Movement: 150", 20, height - 20);
            }
        };
        chartPanel.setPreferredSize(new Dimension(600, 400));
        mainPanel.add(chartPanel, BorderLayout.CENTER);

        // Add control panel
        JPanel controlPanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        controlPanel.add(closeButton);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FCFSChartExample().setVisible(true);
        });
    }
}
