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

package smile.data.vector;

import smile.data.measure.DiscreteMeasure;
import smile.data.measure.Measure;
import smile.data.type.StructField;

import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * An immutable float vector.
 *
 * @author Haifeng Li
 */
class FloatVectorImpl implements FloatVector {
    /** The name of vector. */
    private String name;
    /** Optional measure. */
    private Optional<Measure> measure;
    /** The vector data. */
    private float[] vector;

    /** Constructor. */
    public FloatVectorImpl(String name, float[] vector) {
        this.name = name;
        this.measure = Optional.empty();
        this.vector = vector;
    }

    /** Constructor. */
    public FloatVectorImpl(StructField field, float[] vector) {
        if (field.measure.isPresent() && field.measure.get() instanceof DiscreteMeasure) {
            throw new IllegalArgumentException(String.format("Invalid measure %s for %s", field.measure.get(), type()));
        }

        this.name = field.name;
        this.measure = field.measure;
        this.vector = vector;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Optional<Measure> measure() {
        return measure;
    }

    @Override
    public float[] array() {
        return vector;
    }

    @Override
    public double[] toDoubleArray(double[] a) {
        for (int i = 0; i < a.length; i++) a[i] = vector[i];
        return a;
    }

    @Override
    public float getFloat(int i) {
        return vector[i];
    }

    @Override
    public Float get(int i) {
        return vector[i];
    }

    @Override
    public FloatVector get(int... index) {
        float[] v = new float[index.length];
        for (int i = 0; i < index.length; i++) v[i] = vector[index[i]];
        return new FloatVectorImpl(name, v);
    }

    @Override
    public int size() {
        return vector.length;
    }

    @Override
    public DoubleStream stream() {
        return IntStream.range(0, vector.length).mapToDouble(i -> vector[i]);
    }

    @Override
    public String toString() {
        return toString(10);
    }
}