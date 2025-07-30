import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
class Information{
    public Information(){
        JFrame jframe = new JFrame("Information");
        jframe.setLayout(null);
        jframe.setVisible(true);
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon imageIcon = new ImageIcon("image.jpeg");
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(),this);
                } catch (Exception ex) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setLayout(null);
        panel.setBounds(0, 0, jframe.getWidth(), jframe.getHeight());
        jframe.add(panel);

        JLabel headingLabel = new JLabel("Priority Preemptive Scheduling Process",SwingUtilities.CENTER);
        headingLabel.setBounds(0,10,panel.getWidth(),35);
        headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
        headingLabel.setForeground(Color.YELLOW);
        panel.add(headingLabel);

        String defination = "Priority preemptive scheduling is a CPU scheduling algorithm where each process is assigned a priority. The CPU is allocated to the process with the highest priority among the processes that are ready to execute at that time. If a new process arrives with a higher priority than the currently running process, the CPU is preempted from the current process, and given to the new higher priority process. Priority preemptive scheduling is commonly used in real-time systems where tasks have different levels of importance or urgency. It allows critical tasks to be executed promptly while maintaining responsiveness to changing system requirements. However, care must be taken to avoid issues like priority inversion and to ensure fairness in resource allocation.";

        String advantages = "1.Efficient Resource Management \n2.Meeting Deadlines and Enhancing Responsiveness";

        String disadvatages = "1.Risk of Starvation\n2.Complexity and Overhead";

        JLabel deflabel = new JLabel("Definition:", SwingUtilities.LEFT);
        deflabel.setBounds(20,60,jframe.getWidth(),30);
        deflabel.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        deflabel.setForeground(new Color(144, 238, 144));
        panel.add(deflabel);


        JTextArea defArea = new JTextArea();
        defArea.setBounds(20,100,jframe.getWidth()-30,160);
        defArea.setText(defination);
        defArea.setLineWrap(true);
        defArea.setWrapStyleWord(true);
        defArea.setEditable(false);
        defArea.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        defArea.setBackground(new Color(0, 0, 0, 0));
        defArea.setForeground(new Color(255, 215, 0));
        panel.add(defArea);

        JLabel advLabel = new JLabel("Advantages:",SwingUtilities.LEFT);
        advLabel.setBounds(20,275,jframe.getWidth(),30);
        advLabel.setFont(new Font("Times New Roman", Font.PLAIN,28));
        advLabel.setForeground(new Color(144, 238, 144));
        panel.add(advLabel);

        JTextArea advArea = new JTextArea();
        advArea.setBounds(20, 315,jframe.getWidth()-30 , 60);
        advArea.setText(advantages);
        advArea.setLineWrap(true);
        advArea.setWrapStyleWord(true);
        advArea.setEditable(false);
        advArea.setFont(new Font("Times New Roman", Font.PLAIN,22));
        advArea.setBackground(new Color(0, 0, 0, 0));
        advArea.setForeground(new Color(255, 215, 0));
        panel.add(advArea);

        JLabel disLabel = new JLabel("Disadvantages:",SwingUtilities.LEFT);
        disLabel.setBounds(20,385,jframe.getWidth(),30);
        disLabel.setFont(new Font("Times New Roman", Font.PLAIN,28));
        disLabel.setForeground(new Color(144, 238, 144));
        panel.add(disLabel);

        JTextArea disArea = new JTextArea();
        disArea.setBounds(20, 435, jframe.getWidth(),60);
        disArea.setText(disadvatages);
        disArea.setLineWrap(true);
        disArea.setWrapStyleWord(true);
        disArea.setEditable(false);
        disArea.setFont(new Font("Times New Roman", Font.PLAIN,22));
        disArea.setBackground(new Color(0, 0, 0, 0));
        disArea.setForeground(new Color(255, 215, 0));
        panel.add(disArea);


        JButton jbutton = new JButton("Next");
        jbutton.setBounds(jframe.getWidth()/2 - 60,600,120,30);
        jbutton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        jbutton.setForeground(new Color(255, 215, 0));
        jbutton.setBackground(Color.BLACK);
        panel.add(jbutton);

        jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new PriorityPreemptiveProject();
                    }

                });
                jframe.dispose();
            }
        });
        jframe.repaint();
    }
}

public class PriorityPreemptiveProject {
    int[] turnAroundTimeArray= new int[5];
    int[] waitingTimeArray = new int[5];
    int[] responseTimeArray = new int[5];
    private int numberOfProcess = 0;
    private JTextField jtextfield, jtextfield2, jtextfield3;

    public PriorityPreemptiveProject() {
        JFrame jframe = new JFrame("Priority Preemptive");

        jframe.setLayout(null);
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon imageIcon = new ImageIcon("pexels-aphiwat-chuangchoem-359989.jpg");
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(),this);
                } catch (Exception ex) {
                    g.setColor(Color.GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setLayout(null);
        panel.setBounds(0, 0, jframe.getWidth(), jframe.getHeight());
        jframe.add(panel);

        JLabel headingLabel = new JLabel("Priority Preemptive", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        headingLabel.setBounds(0, 20, jframe.getWidth(), 40);
        headingLabel.setForeground(Color.BLACK);
        panel.add(headingLabel);

        JLabel jLabel = new JLabel("There are five processes namely P0, P1, P2, P3, P4",SwingUtilities.LEFT);
        jLabel.setBounds(20, 170, jframe.getWidth(), 30);
        jLabel.setFont(new Font("Times New Roman", Font.PLAIN,28));
        jLabel.setForeground(Color.BLACK);
        panel.add(jLabel);

        // Arrival time
        JLabel jLabel2 = new JLabel("Arrival Time ",SwingUtilities.LEFT);
        jLabel2.setBounds(20, 220, 150, 30);
        jLabel2.setFont(new Font("Times New Roman", Font.PLAIN,24));
        jLabel2.setForeground(Color.BLACK);
        panel.add(jLabel2);

        jtextfield = new JTextField();
        jtextfield.setBounds(200, 220, 200, 30);
        jtextfield.setFont(new Font("Times New Roman", Font.PLAIN,24));
        panel.add(jtextfield);

        // Burst time
        JLabel jLabel3 = new JLabel("Burst Time");
        jLabel3.setBounds(20, 280, 150, 30);
        jLabel3.setFont(new Font("Times New Roman", Font.PLAIN,24));
        jLabel3.setForeground(Color.BLACK);
        panel.add(jLabel3);

        jtextfield2 = new JTextField();
        jtextfield2.setBounds(200, 280, 200, 30);
        jtextfield2.setFont(new Font("Times New Roman", Font.PLAIN,24));
        panel.add(jtextfield2);

        // Priority
        JLabel jLabel4 = new JLabel("Priority");
        jLabel4.setBounds(20, 340, 150, 30);
        jLabel4.setFont(new Font("Times New Roman", Font.PLAIN,24));
        jLabel4.setForeground(Color.BLACK);
        panel.add(jLabel4);

        jtextfield3 = new JTextField();
        jtextfield3.setBounds(200, 340, 200, 30);
        jtextfield3.setFont(new Font("Times New Roman", Font.PLAIN,24));
        panel.add(jtextfield3);

        JButton jbutton = new JButton("Add Process");
        jbutton.setBounds(150, 420, 175, 30);
        jbutton.setFont(new Font("Times New Roman", Font.PLAIN,20));
        jbutton.setBackground(Color.BLACK);
        jbutton.setForeground(Color.WHITE);
        panel.add(jbutton);

        jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String arrival = jtextfield.getText().trim();
                String burst = jtextfield2.getText().trim();
                String priority = jtextfield3.getText().trim();
                if (arrival.matches(".*[^\\d,].*") || burst.matches(".*[^\\d,].*") || priority.matches(".*[^\\d,].*")) {
                    JOptionPane.showMessageDialog(null, "Input characters should only contain integers.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return; 
                }
      
                String [] stringArrivalArray = arrival.split(",");
                String [] stringBurstArray = burst.split(",");
                String [] stringPriorityArray = priority.split(",");
              
                int [] arrivalTimeArray = new int[stringArrivalArray.length];
                int [] OriginalBursttimeArray = new int[stringBurstArray.length];
                int [] PriorityArray = new int[stringPriorityArray.length];
     

                for(int i = 0; i < stringArrivalArray.length; i++){
                    arrivalTimeArray[i] = Integer.parseInt(stringArrivalArray[i]);
                    OriginalBursttimeArray[i] = Integer.parseInt(stringBurstArray[i]);
                    PriorityArray[i] = Integer.parseInt(stringPriorityArray[i]);
                }
                numberOfProcess = arrivalTimeArray.length;
                if (numberOfProcess == 5) {
                    JOptionPane.showMessageDialog(null, "You have entered 5 processes.");
                    SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new ProcessTableGUI(arrivalTimeArray, OriginalBursttimeArray, PriorityArray, turnAroundTimeArray,waitingTimeArray,responseTimeArray);
                    }
                });
                    jframe.dispose();
            }
        }
    });
        jframe.repaint();
    }

    public static void main(String[] args) {
        Information ift = new Information();
    }
}

class ProcessTableGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public ProcessTableGUI(int[] arrivalTimeArray, int[] OriginalBursttimeArray, int[] PriorityArray,int[] turnAroundTimeArray ,int[] waitingTimeArray, int[] responseTimeArray) {
        int[] processID = {1,2,3,4,5};
        int[] startTime = new int[5];
        int[] completionTimeArray = new int[5];
        int[] BurstTimeArray = OriginalBursttimeArray.clone();
        int CurrentTime = 0;
        int completed = 0;

        while (completed != 5){
            int highestPriority = Integer.MAX_VALUE;
            int selectedProcess = -1;
            for (int i = 0; i < 5; i++) {
                if (arrivalTimeArray[i] <= CurrentTime && BurstTimeArray[i] > 0 && PriorityArray[i] < highestPriority) {
                    highestPriority = PriorityArray[i];
                    selectedProcess = i;
                }
            }
            if (selectedProcess != -1){
                if (startTime[selectedProcess] == 0 && arrivalTimeArray[selectedProcess] == 0) {
                    startTime[selectedProcess]=0;
                }
                else if(startTime[selectedProcess] == 0){
                    startTime[selectedProcess] = CurrentTime;
                }
                BurstTimeArray[selectedProcess]--;
                CurrentTime++;
                if (BurstTimeArray[selectedProcess] == 0){
                    completionTimeArray[selectedProcess] = CurrentTime;
                    turnAroundTimeArray[selectedProcess] = completionTimeArray[selectedProcess] - arrivalTimeArray[selectedProcess];
                    waitingTimeArray[selectedProcess] = turnAroundTimeArray[selectedProcess] - OriginalBursttimeArray[selectedProcess];
                    responseTimeArray[selectedProcess] = startTime[selectedProcess] - arrivalTimeArray[selectedProcess];
                    completed++;
                }
            } else {
                CurrentTime++;
            }
        }

        double totalTurnAroundTime= 0;
        double totalWaitingTime = 0;
        double totalResponseTime = 0;

        for (int i = 0; i < 5; i++) {
            totalTurnAroundTime += turnAroundTimeArray[i];
            totalWaitingTime += waitingTimeArray[i];
            totalResponseTime += responseTimeArray[i];
        }

        double avgturnAroundTime= totalTurnAroundTime / 5;
        double avgwaitingTime = totalWaitingTime / 5;
        double avgResponseTime = totalResponseTime / 5;


        frame = new JFrame("Process Details");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon backgroundImage = new ImageIcon("pexels-cats-coming-707582.jpg");
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception ex) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setLayout(null);
        panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(panel);

        JLabel heading = new JLabel("Process Details", SwingConstants.CENTER);
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 36));
        heading.setBounds(0,20,frame.getWidth(),40);
        panel.add(heading);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Process");
        tableModel.addColumn("Arrival Time");
        tableModel.addColumn("Burst Time");
        tableModel.addColumn("Priority");
        tableModel.addColumn("Turnaround Time");
        tableModel.addColumn("Waiting Time");
        tableModel.addColumn("Response Time");

        table = new JTable(tableModel);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        table.setForeground(Color.BLACK);
        table.setBounds(0,100,frame.getWidth(),100);
        panel.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, frame.getWidth(),100);
        panel.add(scrollPane);

        for (int i = 0; i < 5; i++) {
            tableModel.addRow(new Object[]{
                    "P" + i,
                    arrivalTimeArray[i],
                    OriginalBursttimeArray[i],
                    PriorityArray[i],
                    turnAroundTimeArray[i],
                    waitingTimeArray[i],
                    responseTimeArray[i]
            });
        }

        JLabel jlabel1 = new JLabel("The Average Turnaround Time of the process is " + avgturnAroundTime + "s.", SwingUtilities.LEFT);
        jlabel1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        jlabel1.setBounds(20,300,frame.getWidth(),30);
        jlabel1.setForeground(Color.BLACK);
        panel.add(jlabel1);

        JLabel jlabel2 = new JLabel("The Average Waiting Time of the process is " + avgwaitingTime + "s.");
        jlabel2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        jlabel2.setBounds(20,340,frame.getWidth(),30);
        jlabel2.setForeground(Color.BLACK);
        panel.add(jlabel2);

        JLabel jlabel3 = new JLabel("The Average Response Time of the process is " + avgResponseTime + "s.");
        jlabel3.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        jlabel3.setBounds(20,380,frame.getWidth(),30);
        jlabel3.setForeground(Color.BLACK);
        panel.add(jlabel3);

        JButton jButton = new JButton("Home");
        jButton.setBounds(frame.getWidth()/2 - 60,600,120,30);
        jButton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        jButton.setForeground(Color.WHITE);
        jButton.setBackground(Color.BLACK);
        panel.add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OS os = new OS();
                frame.dispose();
            }
        });

        frame.repaint();
    }
}
