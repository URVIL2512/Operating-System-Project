import javax.swing.*;
import java.awt.*;

public class InformationAboutDiscScheduling {
    public InformationAboutDiscScheduling() {
        JFrame frame = new JFrame("FCFS Disk Scheduling Information");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("FCFS Disk Scheduling Algorithm", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Information text
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        infoArea.setText(
            "FCFS (First Come First Serve) Disk Scheduling Algorithm:\n\n" +
            "• FCFS is the simplest disk scheduling algorithm\n" +
            "• Requests are processed in the order they arrive\n" +
            "• No optimization is done for head movement\n" +
            "• Fair but may not be efficient for large disk access\n\n" +
            "Advantages:\n" +
            "• Simple to implement\n" +
            "• Fair to all requests\n" +
            "• No starvation\n\n" +
            "Disadvantages:\n" +
            "• High seek time\n" +
            "• Poor performance for large disks\n" +
            "• No optimization for head movement\n\n" +
            "Example: If requests come in order [50, 100, 25, 75]\n" +
            "Head movement: |50-100| + |100-25| + |25-75| = 50 + 75 + 50 = 175"
        );

        JScrollPane scrollPane = new JScrollPane(infoArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton demoButton = new JButton("Run Demo");
        JButton closeButton = new JButton("Close");

        demoButton.addActionListener(e -> {
            new FCFSChartExample().setVisible(true);
        });

        closeButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(demoButton);
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
} 