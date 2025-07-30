import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class introClass{
    public introClass(){
        JFrame jframe = new JFrame("Introduction to LRU Page Replacement Algorithm");
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(null);
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon imageIcon = new ImageIcon("pexels-noah-erickson-404280.jpg");
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception ex) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setBounds(0,0,jframe.getWidth(),jframe.getHeight());
        panel.setLayout(null);
        jframe.add(panel);

        JLabel headingLabel = new JLabel("Least Recently Used (LRU) Page Replacement Algorithm",SwingConstants.CENTER);
        headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        headingLabel.setForeground(new Color(0, 255, 233));
        headingLabel.setBounds(0,10,jframe.getWidth(),40);
        panel.add(headingLabel);

        JLabel jLabel = new JLabel("What is Page Replacement?",SwingConstants.LEFT);
        jLabel.setBounds(20,60,jframe.getWidth(),30);
        jLabel.setForeground(new Color(25, 241, 94));
        jLabel.setFont(new Font("Times New Roman", Font.BOLD,26));
        panel.add(jLabel);

        String pageReplacement = "Page replacement is a fundamental concept in operating systems, particularly in systems that utilize virtual memory. In a system with virtual memory, the main memory (RAM) is divided into fixed-size blocks called pages, and the secondary storage (such as a hard disk) is divided into fixed-size blocks called page frames.\nWhen a program needs to access data that is not currently in the main memory, a page fault occurs. This means that the required page is not present in a page frame in RAM, and the operating system must bring the required page into memory from secondary storage. However, if all page frames in memory are already occupied, the operating system needs to decide which page to remove (or replace) from memory to make room for the new page.\nPage replacement refers to the process of selecting which page to remove from memory when a page fault occurs and there are no available page frames. The goal of page replacement algorithms is to minimize the number of page faults and optimize the usage of available memory.";

        JTextArea jTextArea = new JTextArea();
        jTextArea.setFont(new Font("Times New Roman", Font.PLAIN,18));
        jTextArea.setBackground(new Color(0,0,0,0));
        jTextArea.setText(pageReplacement);
        jTextArea.setEditable(false);
        jTextArea.setFocusable(false);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setForeground(new Color(252, 213, 212));
        jTextArea.setBounds(20,100,jframe.getWidth() - 50,160);
        panel.add(jTextArea);

        JLabel definition = new JLabel("What is Least recently Used Page Replacement Algorithm?",SwingConstants.LEFT);
        definition.setBounds(20,260,jframe.getWidth(),30);
        definition.setFont(new Font("Times New Roman", Font.BOLD,26));
        definition.setForeground(new Color(25, 241, 94));
        panel.add(definition);

        String definitionLRU = "The Least Recently Used (LRU) page replacement algorithm is used in operating systems to manage memory efficiently, particularly in systems with virtual memory. Its primary goal is to minimize the number of page faults by replacing pages in memory that have not been accessed for the longest period of time.\n\nHere's a detailed explanation of how the LRU algorithm works:\n1.Page Tracking: The LRU algorithm keeps track of the usage history of pages in memory. It typically maintains a data structure, such as a queue, linked list, or a combination of both, to record the order in which pages are accessed.\n2.Accessing Pages: Whenever a page is accessed (read or written) by a process, the LRU algorithm updates its usage history. If the page is already in memory, it is marked as 'used' or moved to the front of the tracking data structure to indicate that it has been accessed recently. If the page is not in memory, a page fault occurs, and the operating system brings the required page into memory from secondary storage.\n3.Page Replacement: When a page fault occurs and there are no available page frames in memory (i.e., all page frames are occupied), the LRU algorithm selects the page that has not been accessed for the longest period of time for replacement. This means that the page that is least recently used is evicted from memory to make room for the newly requested page.\n4.Updating Usage History: After selecting the page for replacement, the LRU algorithm updates the usage history data structure to reflect the access pattern of pages. This may involve removing the least recently used page from the tracking data structure and adding the newly requested page to the front of the structure.\n5.Performance Considerations: The LRU algorithm is effective in many scenarios because it tends to replace pages that are least likely to be used in the near future. However, implementing LRU may require maintaining a large data structure to track the usage history of pages, which can consume memory and impact performance. Various data structures and optimizations can be employed to mitigate these concerns.\n\nOverall, the LRU page replacement algorithm is a widely used approach to manage memory efficiently by evicting the least recently used pages from memory when needed. Its effectiveness lies in its ability to adapt to the access patterns of processes and minimize the occurrence of page faults.";

        JTextArea deftext = new JTextArea();
        deftext.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        deftext.setLineWrap(true);
        deftext.setEditable(false);
        deftext.setFocusable(false);
        deftext.setText(definitionLRU);
        deftext.setWrapStyleWord(true);
        deftext.setBounds(20,300,jframe.getWidth()-50,420);
        deftext.setBackground(new Color(0,0,0,0));
        deftext.setForeground(new Color(252, 213, 212));
//        deftext.setForeground(Color.BLACK);
        panel.add(deftext);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(550,730,120,30);
        nextButton.setFont(new Font("Times New Roman", Font.PLAIN,20));
        nextButton.setForeground(Color.CYAN);
        nextButton.setBackground(Color.BLACK);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new LRUImplementation();
                    }
                });
                jframe.dispose();
            }
        });
        panel.add(nextButton);

        jframe.repaint();
    }
}

class LogicLRU {
    private int framesNumber;
    private int[] accessPattern;
    private StringBuilder pageReplacementSteps;
    private LinkedHashSet<Integer> memoryFrames;
    private int pageFaults;

    public LogicLRU() {
        framesNumber = 0;
        accessPattern = new int[0];
        pageReplacementSteps = new StringBuilder();
    }

    public LogicLRU(int framesNumber, int[] accessPattern) {
        this.framesNumber = framesNumber;
        this.accessPattern = accessPattern.clone();
        pageReplacementSteps = new StringBuilder();
    }

    public int getFramesNumber() {
        return framesNumber;
    }

    public int[] getAccessPattern() {
        return accessPattern;
    }

    public StringBuilder getPageReplacementSteps() {
        return pageReplacementSteps;
    }

    public LinkedHashSet<Integer> getMemoryFrames() {
        return memoryFrames;
    }

    public int getPageFaults() {
        return pageFaults;
    }

    public void simulateLRU() {
        pageReplacementSteps.setLength(0); // Reset StringBuilder
        memoryFrames = new LinkedHashSet<>();
        pageFaults = 0;

        for (int page : accessPattern) {
            if (!memoryFrames.contains(page)) {
                pageFaults++;
                if (memoryFrames.size() == framesNumber) {
                    int leastRecentlyUsed = memoryFrames.iterator().next();
                    memoryFrames.remove(leastRecentlyUsed);
                    pageReplacementSteps.append("Page ").append(leastRecentlyUsed).append(" replaced by Page ").append(page).append(".\n");
                }
                pageReplacementSteps.append("Page ").append(page).append(" brought into memory.\n");
            } else {
                memoryFrames.remove(page);
                pageReplacementSteps.append("Page ").append(page).append(" is already in memory. \n");
            }
            memoryFrames.add(page);
        }
    }
}

class LRUImplementation{
    StringBuilder outputString;
    public LRUImplementation(){
        JFrame jframe = new JFrame("Implementation of Least Recently Used Page Replacement Algorithm");
        jframe.setLayout(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon imageIcon = new ImageIcon("pexels-sergei-starostin-6636474.jpg");
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(),this);
                } catch (Exception ex) {
                    g.setColor(Color.GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setBounds(0,0,jframe.getWidth(),jframe.getHeight());
        panel.setLayout(null);
        jframe.add(panel);

        JLabel headingLabel = new JLabel("Least Recently Used (LRU) Page Replacement Algorithm",SwingConstants.CENTER);
        headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        headingLabel.setForeground(new Color(183, 238, 59));
        headingLabel.setBounds(0,5,jframe.getWidth(),40);
        panel.add(headingLabel);

        JLabel jLabel = new JLabel("Number of Page Frames: ");
        jLabel.setBounds(20,70,280,30);
        jLabel.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        jLabel.setForeground(new Color(251, 210, 212));
        panel.add(jLabel);

        JTextField jTextField = new JTextField();
        jTextField.setBounds(320,70,300,30);
        jTextField.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        jTextField.setBackground(new Color(0,0,0,0));
        jTextField.setForeground(new Color(208, 89, 89));
        jTextField.setOpaque(false);
        panel.add(jTextField);

        JLabel jLabel1 = new JLabel("Memory Access Pattern:");
        jLabel1.setBounds(20,130,280,30);
        jLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        jLabel1.setForeground(new Color(251, 210, 212));
        panel.add(jLabel1);

        JTextField jTextField1 = new JTextField();
        jTextField1.setBounds(320,130,300,30);
        jTextField1.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        jTextField1.setForeground(new Color(208, 89, 89));
        jTextField1.setBackground(new Color(0,0,0,0));
        jTextField1.setOpaque(false);
        panel.add(jTextField1);

        JLabel jLabel2 = new JLabel("Output:",SwingConstants.LEFT);
        jLabel2.setBounds(20,220,jframe.getWidth(),35);
        jLabel2.setForeground(new Color(183, 238, 59));
        jLabel2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        panel.add(jLabel2);

        JTextArea jTextArea = new JTextArea();
        jTextArea.setForeground(new Color(39, 219, 237));
        jTextArea.setOpaque(false);
        jTextArea.setEditable(false);
        jTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 26));

        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setBounds(20, 260, 550, 450);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setOpaque(false); // Make the scroll pane transparent
        scrollPane.getViewport().setOpaque(false); // Make the viewport transparent
        panel.add(scrollPane);


        JButton jButton = new JButton("Calculate");
        jButton.setBounds(400,180,120,30);
        jButton.setFont(new Font("Times New Roman", Font.PLAIN,20));
        jButton.setForeground(new Color(170, 60, 60));
        jButton.setBackground(new Color(134, 157, 130));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Frames = jTextField.getText();
                String accessPattern = jTextField1.getText();
                if(Frames.matches(".*[\\D].*")  || accessPattern.matches(".*[^\\d,].*") ){
                    JOptionPane.showMessageDialog(null, "Input characters should only contain integers.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return; 
                }
                int memoryFrames = Integer.parseInt(Frames);
                
                String [] accessSeq = accessPattern.split(",");
                int [] accessSequence = new int[accessSeq.length];
                for(int i = 0; i < accessSeq.length; i++){
                    accessSequence[i] = Integer.parseInt(accessSeq[i]);
                }
                LogicLRU logicLRU = new LogicLRU(memoryFrames,accessSequence);
                logicLRU.simulateLRU();
                String str1 = "Page Faults: "+logicLRU.getPageFaults()+"\n"+"Pagereplacement Steps:\n"+ logicLRU.getPageReplacementSteps().toString()+"Final state of Memory Page Frames:"+logicLRU.getMemoryFrames().toString();
                jTextArea.setText(str1);
                jframe.repaint();
            }
        });
        panel.add(jButton);

        JButton jButton1 = new JButton("Home");
        jButton1.setBounds(400,730,120,30);
        jButton1.setFont(new Font("Times New Roman", Font.PLAIN,20));
        jButton1.setForeground(new Color(170, 60, 60));
        jButton1.setBackground(new Color(134, 157, 130));
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
                OS os = new OS();
            }
        });
        panel.add(jButton1);

        jframe.repaint();
    }
}
public class LRUPageReplacementAlgo{
    public static void main(String[] args){
        introClass ic = new introClass();
    }
}