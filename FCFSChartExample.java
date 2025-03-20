// import org.jfree.chart.ChartFactory;
// import org.jfree.chart.ChartPanel;
// import org.jfree.chart.JFreeChart;
// import org.jfree.data.xy.XYSeries;
// import org.jfree.data.xy.XYSeriesCollection;

// import javax.swing.*;
// import java.awt.*;

// public class FCFSChartExample extends JFrame {
//     public FCFSChartExample() {
//         super("FCFS Disk Scheduling Chart");

//         // Create data series for the chart
//         XYSeries series = new XYSeries("Disk Scheduling");
//         // Add data points representing the disk access sequence
//         series.add(0, 50);
//         series.add(50, 100);
//         series.add(100, 25);
//         series.add(25, 75);

//         XYSeriesCollection dataset = new XYSeriesCollection();
//         dataset.addSeries(series);

//         // Create the chart
//         JFreeChart chart = ChartFactory.createXYLineChart(
//                 "FCFS Disk Scheduling", // Chart title
//                 "Request", // X-axis label
//                 "Cylinder", // Y-axis label
//                 dataset // Data
//         );

//         // Customize chart appearance if needed
//         chart.setBackgroundPaint(Color.WHITE);

//         // Create and configure chart panel
//         ChartPanel chartPanel = new ChartPanel(chart);
//         chartPanel.setPreferredSize(new Dimension(800, 600));

//         // Add chart panel to frame
//         setContentPane(chartPanel);

//         pack();
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             new FCFSChartExample().setVisible(true);
//         });
//     }
// }
