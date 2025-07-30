import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OS{
    public OS(){
        JFrame jframe = new JFrame("Our OS Project");
        jframe.setLayout(null);
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon imageIcon = new ImageIcon("pexels-katrin-bolovtsova-6193131.jpg");
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception ex) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setLayout(null);
        panel.setBounds(0,0,600,jframe.getHeight());
        jframe.add(panel);

        JLabel headingLabel = new JLabel("Operating System Projects",SwingConstants.CENTER);
        headingLabel.setBounds(0,10,panel.getWidth(),40);
        headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
        panel.add(headingLabel);

        JLabel team = new JLabel("Our Projects:");
        team.setBounds(15,60,300,30);
        team.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        panel.add(team);

        String projects = "1.Priority (Preemptive) Scheduling Algorithm.\n2.Readers-Writers Problem with Semaphore.\n3.FCFS Disk Scheduling Algorithm.\n4.Least Recently Used(LRU) Page Replacement Algorithm.";

        JTextArea projectNames = new JTextArea();
        projectNames.setBounds(15,100,570,130);
        projectNames.setText(projects);
        projectNames.setFocusable(false);
        projectNames.setFont(new Font("Times New Roman", Font.PLAIN,22));
        projectNames.setEditable(false);
        projectNames.setLineWrap(false);
        projectNames.setBackground(new Color(0,0,0,0));
        projectNames.setWrapStyleWord(false);
        panel.add(projectNames);

        JLabel ourProjects = new JLabel("Our Team Members:");
        ourProjects.setBounds(260,290,300,30);
        ourProjects.setFont(new Font("Times New Roman",Font.PLAIN,28));
        panel.add(ourProjects);

        String members = "1.Ayush Patel\n\n2.Ayush Prajapati\n\n3.Urvil Solanki\n\n4.Achyut Koul\n\n5.Bhavya Patel";

        JTextArea teamMembers = new JTextArea();
        teamMembers.setBounds(260,340,250,270);
        teamMembers.setText(members);
        teamMembers.setLineWrap(true);
        teamMembers.setWrapStyleWord(true);
        teamMembers.setFont(new Font("Times New Roman", Font.PLAIN,22));
        teamMembers.setEditable(false);
        teamMembers.setFocusable(false);
        teamMembers.setBackground(new Color(0,0,0,0));
        panel.add(teamMembers);

        JPanel jPanel = new JPanel(){
            @Override
            protected  void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    ImageIcon imageIcon = new ImageIcon("120155669-operating-system-word-cloud-technology-concept-background.jpg");
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception ex) {
                    g.setColor(Color.GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        jPanel.setBounds(600,0,jframe.getWidth() - 600, jframe.getHeight());
        jPanel.setLayout(null);
        jframe.add(jPanel);

        JLabel selectProject = new JLabel("Please choose a project for viewing:");
        selectProject.setBounds(120,150,550,70);
        selectProject.setFont(new Font("Times New Roman", Font.PLAIN,26));
        jPanel.add(selectProject);

        String [] options = {"Priority Preemptive","Reader Writer","FCFS Disc Scheduling","LRU Algorithm"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setForeground(new Color(244, 234, 226));
        comboBox.setBackground(new Color(27, 17, 10));
        comboBox.setBounds(140,230,320,30);
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jPanel.add(comboBox);

        JButton proceed = new JButton("Proceed");
        proceed.setBounds(140,420,120,40);
        proceed.setFont(new Font("Times New Roman", Font.PLAIN,20));
        proceed.setForeground(new Color(244, 234, 226));
        proceed.setBackground(new Color(27, 17, 10));
        proceed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProject = (String) comboBox.getSelectedItem();
                if(selectedProject.equals("Priority Preemptive")){
                    Information i = new Information();
                    jframe.dispose();
                }
                else if(selectedProject.equals("Reader Writer")){
                    GUI gui = new GUI();
                    jframe.dispose();
                }
                else if(selectedProject.equals("FCFS Disc Scheduling")){
                    InformationAboutDiscScheduling info = new InformationAboutDiscScheduling();
                    jframe.dispose();
                }
                else if(selectedProject.equals("LRU Algorithm")){
                    introClass ic = new introClass();
                    jframe.dispose();
                }
            }
        });
        jPanel.add(proceed);

        JButton exit = new JButton("Exit");
        exit.setBounds(370,420,120,40);
        exit.setForeground(new Color(244, 234, 226));
        exit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        exit.setBackground(new Color(27, 17, 10));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Goodbye! Thank you for using our project.");
                jframe.dispose();
            }
        });
        jPanel.add(exit);

        jframe.repaint();
    }
}
public class MainScreenOS {
    public static void main(String[] args){
        System.out.println("[DEBUG] Starting MainScreenOS...");
        // Open all available modules automatically
        javax.swing.SwingUtilities.invokeLater(() -> {
            try { System.out.println("[DEBUG] Launching Priority Preemptive module..."); new Information(); } catch (Exception e) { e.printStackTrace(); }
            try { System.out.println("[DEBUG] Launching Reader Writer module..."); new GUI(); } catch (Exception e) { e.printStackTrace(); }
            try { System.out.println("[DEBUG] Launching LRU Algorithm module..."); new introClass(); } catch (Exception e) { e.printStackTrace(); }
            // FCFS Disc Scheduling is skipped (class missing)
        });
    }
}