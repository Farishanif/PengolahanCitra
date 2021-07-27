/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

/**
 * @see https://stackoverflow.com/q/40537278/230513
 * @see https://stackoverflow.com/q/11870416/230513
 * @see https://stackoverflow.com/a/28519356/230513
 */

/**
 *
 * @author user
 */
public class Histogram {
    private JFrame f = new JFrame("Histogram");
    private JLabel label = new JLabel("label");
        
    
    private static final int BINS = 256;
    public BufferedImage image = null;
    private HistogramDataset dataset;
    private XYBarRenderer renderer;

    /*private BufferedImage getImage() {
        try {
            return ImageIO.read(new File(
                "C:\\apyr.jpg"));
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }*/

    private ChartPanel createChartPanel(BufferedImage image) {
        // dataset
        dataset = new HistogramDataset();
        Raster raster = image.getRaster();
        final int w = image.getWidth();
        final int h = image.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        dataset.addSeries("Red", r, BINS);
        r = raster.getSamples(0, 0, w, h, 1, r);
        dataset.addSeries("Green", r, BINS);
        r = raster.getSamples(0, 0, w, h, 2, r);
        dataset.addSeries("Blue", r, BINS);
        // chart
        JFreeChart chart = ChartFactory.createHistogram("Histogram", "Value",
            "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardXYBarPainter());
        // translucent red, green & blue
        Paint[] paintArray = {
            new Color(0x80ff0000, true),
            new Color(0x8000ff00, true),
            new Color(0x800000ff, true)
        };
        plot.setDrawingSupplier(new DefaultDrawingSupplier(
            paintArray,
            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.add(new JCheckBox(new VisibleAction(0)));
        panel.add(new JCheckBox(new VisibleAction(1)));
        panel.add(new JCheckBox(new VisibleAction(2)));
        return panel;
    }

    public class VisibleAction extends AbstractAction {

        private final int i;

        public VisibleAction(int i) {
            this.i = i;
            this.putValue(NAME, (String) dataset.getSeriesKey(i));
            this.putValue(SELECTED_KEY, true);
            renderer.setSeriesVisible(i, true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            renderer.setSeriesVisible(i, !renderer.getSeriesVisible(i));
        }
    }
    
    public ImageIcon ResizeImage(BufferedImage MyImage)
    {
    //ImageIcon MyImage = new ImageIcon(ImagePath);
    label.setSize(345, 515);
    ImageIcon MImage = new ImageIcon(MyImage);
    Image img = MImage.getImage();
    Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon image = new ImageIcon(newImg);
    return image;
}

    public void display(BufferedImage image) {
        //label.setSize(345, 450);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.add(createChartPanel(image));
        f.add(createControlPanel(), BorderLayout.SOUTH);
        f.add(label, BorderLayout.WEST);
        //label.setIcon(ResizeImage(image));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Histogram().display(image);
        });
    }
*/
}
