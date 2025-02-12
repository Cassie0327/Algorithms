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

package smile.demo.clustering;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import smile.clustering.DeterministicAnnealing;
import smile.plot.Palette;
import smile.plot.PlotCanvas;
import smile.plot.ScatterPlot;

/**
 *
 * @author Haifeng Li
 */
@SuppressWarnings("serial")
public class DeterministicAnnealingDemo extends ClusteringDemo {

    JTextField alphaField;
    double alpha = 0.9;

    public DeterministicAnnealingDemo() {
        alphaField = new JTextField(Double.toString(alpha), 5);
        optionPane.add(new JLabel("alpha:"));
        optionPane.add(alphaField);
    }

    @Override
    public JComponent learn() {
        try {
            alpha = Double.parseDouble(alphaField.getText().trim());
            if (alpha <= 0 || alpha >= 1) {
                JOptionPane.showMessageDialog(this, "Invalid alpha: " + alpha, "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid K: " + alphaField.getText(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        long clock = System.currentTimeMillis();
        DeterministicAnnealing annealing = DeterministicAnnealing.fit(dataset[datasetIndex], clusterNumber, alpha, 100, 1E-4, 1E-2);
        System.out.format("Deterministic Annealing clusterings %d samples in %dms\n", dataset[datasetIndex].length, System.currentTimeMillis()-clock);

        PlotCanvas plot = ScatterPlot.plot(annealing.centroids, '@');
        for (int k = 0; k < clusterNumber; k++) {
            if (annealing.size[k] > 0) {
                double[][] cluster = new double[annealing.size[k]][];
                for (int i = 0, j = 0; i < dataset[datasetIndex].length; i++) {
                    if (annealing.y[i] == k) {
                        cluster[j++] = dataset[datasetIndex][i];
                    }
                }

                plot.points(cluster, pointLegend, Palette.COLORS[k % Palette.COLORS.length]);
            }
        }
        plot.points(annealing.centroids, '@');
        return plot;
    }

    @Override
    public String toString() {
        return "Deterministic Annealing";
    }

    public static void main(String argv[]) {
        ClusteringDemo demo = new DeterministicAnnealingDemo();
        JFrame f = new JFrame("Deterministic Annealing");
        f.setSize(new Dimension(1000, 1000));
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(demo);
        f.setVisible(true);
    }
}
