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

/**
 * Variogram functions. In spatial statistics the theoretical variogram
 * 2&gamma;(x,y) is a function
 * describing the degree of spatial dependence of a spatial random field
 * or stochastic process Z(x). It is defined as the expected squared increment
 * of the values between locations x and y:
 * <p>
 * 2&gamma;(x,y)=E(|Z(x)-Z(y)|<sup>2</sup>)
 * <p>
 * where &gamma;(x,y) itself is called the semivariogram. In case of a stationary
 * process the variogram and semivariogram can be represented as a function
 * &gamma;<sub>s</sub>(h) = &gamma;(0, 0 + h) of the difference h = y - x
 * between locations only, by the following relation:
 * <p>
 * &gamma;(x,y) = &gamma;<sub>s</sub>(y - x).
 * <p>
 * In Kriging interpolation or Gaussian process regression, we employ this kind
 * of variogram as an estimation of the mean square variation of the
 * interpolation/fitting function. For interpolation, even very crude
 * variogram estimate works fine.
 * <p>
 * The variogram characterizes the spatial continuity or roughness of a data set.
 * Ordinary one dimensional statistics for two data sets may be nearly identical,
 * but the spatial continuity may be quite different. Variogram analysis consists
 * of the experimental variogram calculated from the data and the variogram model
 * fitted to the data. The experimental variogram is calculated by averaging one half
 * the difference squared of the z-values over all pairs of observations with the
 * specified separation distance and direction. It is plotted as a two-dimensional
 * graph. The variogram model is chosen from a set of mathematical functions that
 * describe spatial relationships. The appropriate model is chosen by matching
 * the shape of the curve of the experimental variogram to the shape of the curve
 * of the mathematical function.
 * 
 * @author Haifeng Li
 */
package smile.interpolation.variogram;
