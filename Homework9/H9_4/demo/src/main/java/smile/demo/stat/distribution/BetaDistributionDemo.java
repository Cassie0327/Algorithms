/*******************************************************************************
 * Copyright (c) 2010-2019 Haifeng Li
 *
 * Smile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * Smile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Smile.  If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/

package smile.demo.stat.distribution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import smile.math.MathEx;
import smile.plot.Histogram;
import smile.plot.PlotCanvas;
import smile.plot.Line;
import smile.plot.LinePlot;
import smile.plot.QQPlot;
import smile.stat.distribution.BetaDistribution;

/**
 *
 * @author Haifeng Li
 */
@SuppressWarnings("serial")
public class BetaDistributionDemo extends JPanel implements ChangeListener {

    private JPanel optionPane;
    private JPanel canvas;
    private PlotCanvas pdf;
    private PlotCanvas cdf;
    private PlotCanvas histogram;
    private PlotCanvas qqplot;
    private JSlider alphaSlider;
    private JSlider betaSlider;
    private double alpha = 2;
    private double beta = 5;

    public BetaDistributionDemo() {
        super(new BorderLayout());

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        for (int i = 0; i <= 100; i+=20) {
            labelTable.put(new Integer(i), new JLabel(String.valueOf(i / 10)));
        }

        alphaSlider = new JSlider(0, 100, (int) Math.round(alpha * 10));
        alphaSlider.addChangeListener(this);
        alphaSlider.setLabelTable(labelTable);
        alphaSlider.setMajorTickSpacing(10);
        alphaSlider.setMinorTickSpacing(2);
        alphaSlider.setPaintTicks(true);
        alphaSlider.setPaintLabels(true);

        betaSlider = new JSlider(0, 100, (int) Math.round(beta * 10));
        betaSlider.addChangeListener(this);
        betaSlider.setLabelTable(labelTable);
        betaSlider.setMajorTickSpacing(10);
        betaSlider.setMinorTickSpacing(2);
        betaSlider.setPaintTicks(true);
        betaSlider.setPaintLabels(true);

        optionPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionPane.setBorder(BorderFactory.createRaisedBevelBorder());
        optionPane.add(new JLabel("\u03B1:"));
        optionPane.add(alphaSlider);
        optionPane.add(new JLabel("\u03B2:"));
        optionPane.add(betaSlider);

        add(optionPane, BorderLayout.NORTH);

        canvas = new JPanel(new GridLayout(2, 2));
        add(canvas, BorderLayout.CENTER);

        BetaDistribution dist = new BetaDistribution(alpha, beta);

        double[][] p = new double[99][2];
        double[][] q = new double[99][2];
        for (int i = 0; i < p.length; i++) {
            p[i][0] = (i+1) / 100.0;
            p[i][1] = dist.p(p[i][0]);
            q[i][0] = (i+1) / 100.0;
            q[i][1] = dist.cdf(p[i][0]);
        }

        pdf = LinePlot.plot(p, Line.Style.SOLID, Color.BLUE);
        pdf.setTitle("PDF");
        canvas.add(pdf);

        cdf = LinePlot.plot(q, Line.Style.SOLID, Color.BLUE);
        cdf.setTitle("CDF");
        canvas.add(cdf);

        double[] data = new double[500];
        for (int i = 0; i < data.length; i++) {
            data[i] = dist.rand();
        }

        histogram = Histogram.plot(data, 20);
        histogram.setTitle("Histogram");
        canvas.add(histogram);

        qqplot = QQPlot.plot(data, dist);
        qqplot.setTitle("Q-Q Plot");
        canvas.add(qqplot);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == alphaSlider || e.getSource() == betaSlider) {
            alpha = alphaSlider.getValue() / 10.0;
            beta = betaSlider.getValue() / 10.0;
            if (alpha == 0) alpha = 0.01;
            if (beta == 0) beta = 0.01;

            BetaDistribution dist = new BetaDistribution(alpha, beta);

            double[][] p = new double[99][2];
            double[][] q = new double[99][2];
            for (int i = 0; i < p.length; i++) {
                p[i][0] = (i+1) / 100.0;
                p[i][1] = dist.p(p[i][0]);
                q[i][0] = (i+1) / 100.0;
                q[i][1] = dist.cdf(p[i][0]);
            }

            pdf.clear();
            pdf.line(p, Line.Style.SOLID, Color.BLUE);

            cdf.clear();
            cdf.line(q, Line.Style.SOLID, Color.BLUE);

            double[] data = new double[500];
            for (int i = 0; i < data.length; i++) {
                data[i] = dist.rand();
            }

            histogram.clear();
            histogram.histogram(data, 20, Color.BLUE);

            qqplot.clear();
            qqplot.add(new QQPlot(data, dist));
            canvas.repaint();
        }
    }

    @Override
    public String toString() {
        return "Beta";
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Beta Distribution");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(new BetaDistributionDemo());
        frame.setVisible(true);
    }
}
