import javax.swing.*;
import  java.awt.*;
import java.awt.event.*;
class GUI {
    public GUI() {
        JFrame jframe = new JFrame("Reader Writer Problem");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(null);
        jframe.setVisible(true);
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon imageIcon = new ImageIcon("hi-tech-interface-abstract-9-4k.jpg");
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception ex) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setLayout(null);
        panel.setBounds(0, 0, jframe.getWidth(),jframe.getHeight());
        jframe.add(panel);

        JLabel HeadingLabel = new JLabel("ReaderWriteProblem", SwingConstants.CENTER);
        HeadingLabel.setBounds(0,10,jframe.getWidth(),50);
        HeadingLabel.setFont(new Font("Times New Roman", Font.BOLD, 46));
        HeadingLabel.setForeground(Color.RED);
        panel.add(HeadingLabel);

        JLabel SubHeading = new JLabel("What is the problem?", SwingConstants.LEFT);
        SubHeading.setBounds(20,80,jframe.getWidth(),40);
        SubHeading.setFont(new Font("Times New Roman", Font.PLAIN, 38));
        SubHeading.setForeground(new Color(255, 253, 208));
        panel.add(SubHeading);

        JTextArea textArea = new JTextArea();
        String text = "The Reader-Writer problem is a classic synchronization problem in computer science, particularly in the field of operating systems. It addresses the issue of managing access to a shared resource, such as a file, database, or memory space, by multiple processes or threads. Specifically, the problem involves two types of processes:\n\n" +
                "Readers: Processes that only need to read the shared resource. Multiple readers can access the resource simultaneously without any issues.\n\n" +
                "Writers: Processes that need to write to the shared resource. Writers should have exclusive access to the resource, meaning that no other reader or writer should be able to access the resource while a writer is writing to it.\n\n" +
                "The main challenge in the Reader-Writer problem is to ensure that concurrent access to the shared resource is managed correctly to prevent race conditions and ensure data consistency. However, we also want to maximize concurrency to allow multiple readers to access the resource simultaneously." +
                "The Reader-Writer problem is a fundamental concept in concurrent programming and synchronization in operating systems. Understanding and effectively solving this problem is crucial for developing efficient and correct concurrent software systems.";
        textArea.setText(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBounds(20,150,jframe.getWidth() - 50,500);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        textArea.setBackground(new Color(0, 0, 0, 0));
        textArea.setForeground(new Color(255, 215, 0));
        panel.add(textArea);

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Times New Roman", Font.PLAIN,26));
        nextButton.setForeground(Color.YELLOW);
        nextButton.setBackground(Color.BLACK);
        nextButton.setBounds(jframe.getWidth() / 2-60 , 700, 120, 30);
        panel.add(nextButton);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new InputScreenGUI();
                    }
                });
                jframe.dispose();
            }
        });
        panel.repaint();
        panel.revalidate();
    }
}
class ReaderWriter {
    public int readerCount;
    public int writerCount;
    public boolean writing;

    public ReaderWriter() {
        this.readerCount = 0;
        this.writerCount = 0;
        this.writing = false;
    }

    private JTextArea outputTextArea;


    public ReaderWriter(JTextArea outputTextArea) {
        this.outputTextArea = outputTextArea;

    }
    public synchronized void startReading() {
        while (writing || writerCount > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readerCount++;
        String output = "Reader " + readerCount + " is reading.";
        outputTextArea.append(output + "\n");

    }

    // Reader finishes reading

    public synchronized void Readingsignal() {
        readerCount--;
        if (readerCount == 0) {
            notifyAll();
        }
    }

    public synchronized void startWriting() {
        while (writing || readerCount > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writerCount++;
        writing = true;
        String output = "Writer " + writerCount + " is writing.";
        writerCount++;
        outputTextArea.append(output + "\n");
    }

    // Writer finishes writing
    public synchronized void Writingsignal() {
        writerCount--;
        writing = false;
        notifyAll();
    }
}

public class semahoreproject {
    public static void main(String[] args) {
        GUI gui = new GUI();
    }
}

class InputScreenGUI {
        int noOfReader;
        int noOFWriter;

        public InputScreenGUI() {
            JFrame jframe = new JFrame("Reader Writer By Using Semaphores");
            jframe.setLayout(null);
            jframe.setVisible(true);
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    try {
                        ImageIcon imageIcon = new ImageIcon("binary-flow-digital-art-uhdpaper.com-8K-4.2044.jpg");
                        Image image = imageIcon.getImage();
                        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                    } catch (Exception ex) {
                        g.setColor(Color.GRAY);
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                }
            };

            panel.setLayout(null);
            panel.setBounds(0, 0, jframe.getWidth(), jframe.getHeight());
            jframe.add(panel);

            JLabel headingLabel = new JLabel("Reader Writer", SwingUtilities.CENTER);
            headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 45));
            headingLabel.setBounds(0, 10, jframe.getWidth(), 50);
            headingLabel.setForeground(new Color(230, 62, 59));
            panel.add(headingLabel);

            JLabel jlabel = new JLabel("Enter the number of readers reading the Database:");
            jlabel.setBounds(20, 100, jframe.getWidth() - 50, 30);
            jlabel.setFont(new Font("Times New Roman", Font.PLAIN, 28));
            jlabel.setForeground(new Color(230, 62, 59));
            panel.add(jlabel);

            JTextField jtextfield = new JTextField();
            jtextfield.setBounds(30, 150, 200, 30);
            jtextfield.setFont(new Font("Times New Roman", Font.PLAIN,28));
            jtextfield.setOpaque(false);
            jtextfield.setForeground(new Color(255, 219, 88));
            panel.add(jtextfield);

            JLabel jlabel2 = new JLabel("Enter the number of writers writing in the Database:");
            jlabel2.setBounds(20, 200, jframe.getWidth(), 30);
            jlabel2.setFont(new Font("Times New Roman", Font.PLAIN, 28));
            jlabel2.setForeground(new Color(230, 62, 59));
            panel.add(jlabel2);

            JTextField jtextfield2 = new JTextField();
            jtextfield2.setBounds(30, 250, 200, 30);
            jtextfield2.setBackground(new Color(0, 0, 0, 10));
            jtextfield2.setFont(new Font("Times New Roman", Font.PLAIN,28));
            jtextfield2.setOpaque(false);
            jtextfield2.setForeground(new Color(255, 219, 88));
            panel.add(jtextfield2);

            JButton simButton = new JButton("Simulate");
            simButton.setBounds(250, 330, 120, 30);
            simButton.setForeground(Color.BLACK);
            simButton.setBackground(new Color(242, 185, 58));
            panel.add(simButton);

            JLabel outLabel = new JLabel("Output:");
            outLabel.setBounds(800, 150, 300, 30);
            outLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
            outLabel.setForeground(new Color(230, 62, 59));
            panel.add(outLabel);

            JTextArea outputTextArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(outputTextArea);
            scrollPane.setBounds(800, 200, 350, 400);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            outputTextArea.setForeground(Color.BLACK);
            outputTextArea.setEditable(false);
            outputTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            panel.add(scrollPane);

            simButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String Reader = jtextfield.getText();
                    String  Writer = jtextfield2.getText();
                    if(Writer.matches(".*[\\D].*") || Reader.matches(".*[\\D].*")){
                        JOptionPane.showMessageDialog(null, "Input characters should only contain integers.", "Warning", JOptionPane.WARNING_MESSAGE);
                        return; 
                    }
                    int noOfReader = Integer.parseInt(Reader);
                    noOFWriter = Integer.parseInt(Writer);
                    jtextfield.setText("");
                    jtextfield2.setText("");

                    final ReaderWriter rw = new ReaderWriter(outputTextArea);

                    // Readers
                    for (int i = 1; i <= noOfReader; i++) {
                        final int readerId = i;
                        new Thread(() -> {
                            rw.startReading(); // Reader starts reading
                            try {
                                Thread.sleep(1000);
                            } catch (Exception InterruptedExceptione) {

                            }

                            rw.Readingsignal(); // Reader finishes reading
                        }).start();
                    }
                    // Writers
                    for (int i = 1; i <= noOFWriter; i++) {
                        final int writerId = i;
                        new Thread(() -> {
                            rw.startWriting(); // Writer starts writing
                            // Simulating writing operation
                            try {
                                Thread.sleep(1000);
                            } catch (Exception InterruptedExceptionee) {

                            }

                            rw.Writingsignal(); // Writer finishes writing
                        }).start();
                    }
                }
            });
            JButton home = new JButton("Home");
            home.setBounds(250, 600, 120, 30);
            home.setForeground(Color.BLACK);
            home.setBackground(new Color(242, 185, 58));
            home.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    OS os = new OS();
                    jframe.dispose();
                }
            });
            panel.add(home);
        }
}


