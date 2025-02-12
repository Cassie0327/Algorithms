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

package smile

import java.io._
import java.nio.file.{Path, Paths}
import java.sql.ResultSet
import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import com.thoughtworks.xstream.XStream
import org.apache.commons.csv.CSVFormat
import smile.data.{DataFrame, Dataset, Instance}
import smile.data.`type`.StructType
import smile.io.{DatasetReader, DatasetWriter, JSON}
import smile.util.SparseArray

/** Output operators. */
object write {
  /** Serializes a `Serializable` object/model to a file. */
  def apply[T <: Serializable](x: T, file: String): Unit = apply(x, Paths.get(file))

  /** Serializes a `Serializable` object/model to a file. */
  def apply[T <: Serializable](x: T, file: Path): Unit = {
    val oos = new ObjectOutputStream(new FileOutputStream(file.toFile))
    oos.writeObject(x)
    oos.close
  }

  /** Serializes an object/model to a file by XStream. */
  def xstream[T <: Object](x: T, file: String): Unit = xstream(x, Paths.get(file))

  /** Serializes an object/model to a file by XStream. */
  def xstream[T <: Object](x: T, file: Path): Unit = {
    val xstream = new XStream
    val xml = xstream.toXML(x)
    new PrintWriter(file.toFile) {
      write(xml)
      close
    }
  }

  /** Writes an array to a text file line by line.
    *
    * @param data an array.
    * @param file the file path
    */
  def apply[T](data: Array[T], file: String): Unit = apply(data, Paths.get(file))

  /** Writes an array to a text file line by line.
    *
    * @param data an array.
    * @param file the file path
    */
  def apply[T](data: Array[T], file: Path): Unit = {
    val writer = new PrintWriter(file.toFile)
    data.foreach(writer.println(_))
    writer.close
  }

  /** Writes a data frame to an Apache Arrow file. */
  def arrow(data: DataFrame, file: String): Unit = arrow(data, Paths.get(file))

  /** Writes a data frame to an Apache Arrow file. */
  def arrow(data: DataFrame, file: Path): Unit = DatasetWriter.arrow(data, file)

  /** Writes a data frame to an ARFF file. */
  def arff(data: DataFrame, file: String, relation: String): Unit = arff(data, Paths.get(file), relation)

  /** Writes a data frame to an ARFF file. */
  def arff(data: DataFrame, file: Path, relation: String): Unit = DatasetWriter.arff(data, file, relation)

  /** Writes an AttributeDataset to a delimited text file.
    *
    * @param data an attribute dataset.
    * @param file the file path
    */
  def csv(data: DataFrame, file: String, delimiter: Char = ','): Unit = csv(data, Paths.get(file), delimiter)

  /** Writes an AttributeDataset to a delimited text file.
    *
    * @param data an attribute dataset.
    * @param file the file path
    */
  def csv(data: DataFrame, file: Path, delimiter: Char): Unit = {
    val format = CSVFormat.DEFAULT.withDelimiter(delimiter)
    DatasetWriter.csv(data, file, format)
  }

  /** Writes a two-dimensional array to a delimited text file.
    *
    * @param data a two-dimensional array.
    * @param file the file path
    * @param delimiter delimiter string
    */
  def table[T](data: Array[Array[T]], file: String, delimiter: Char = ','): Unit = table(data, Paths.get(file), delimiter)

  /** Writes a two-dimensional array to a delimited text file.
    *
    * @param data a two-dimensional array.
    * @param file the file path
    * @param delimiter delimiter string
    */
  def table[T](data: Array[Array[T]], file: Path, delimiter: Char): Unit = {
    val writer = new PrintWriter(file.toFile)
    val sb = new StringBuilder
    val del = sb.append(delimiter).toString

    data.foreach { row =>
      writer.println(row.mkString(del))
    }

    writer.close
  }
}

/** Input operators. */
object read {
  /** Reads a `Serializable` object/model. */
  def apply(file: String): AnyRef = apply(Paths.get(file))

  /** Reads a `Serializable` object/model. */
  def apply(file: Path): AnyRef = {
    val ois = new ObjectInputStream(new FileInputStream(file.toFile))
    val o = ois.readObject
    ois.close
    o
  }

  /** Reads an object/model that was serialized by XStream. */
  def xstream(file: String): AnyRef = xstream(Paths.get(file))

  /** Reads an object/model that was serialized by XStream. */
  def xstream(file: Path): AnyRef = {
    val xml = Source.fromFile(file.toFile).mkString
    val xstream = new XStream
    xstream.fromXML(xml)
  }

  /** Reads a JDBC query result to a data frame. */
  def jdbc(rs: ResultSet): DataFrame = {
    DataFrame.of(rs)
  }

  /** Reads a CSV file. */
  def csv(file: String, delimiter: Char = ',', header: Boolean = true, quote: Char = '"', escape: Char = '\\', schema: StructType = null): DataFrame =
    csv(Paths.get(file), delimiter, header, quote, escape, schema)

  /** Reads a CSV file. */
  def csv(file: Path, delimiter: Char, header: Boolean, quote: Char, escape: Char, schema: StructType): DataFrame = {
    var format = CSVFormat.DEFAULT.withDelimiter(delimiter).withQuote(quote).withEscape(escape)
    if (header) format = format.withFirstRecordAsHeader
    DatasetReader.csv(file, format, schema)
  }

  /** Reads a CSV file. */
  def csv(file: String, format: CSVFormat, schema: StructType): DataFrame = csv(Paths.get(file), format, schema)

  /** Reads a CSV file. */
  def csv(file: Path, format: CSVFormat, schema: StructType): DataFrame = DatasetReader.csv(file, format, schema)

  /** Reads a JSON file. */
  def json(file: String): DataFrame = json(Paths.get(file))

  /** Reads a JSON file. */
  def json(file: Path): DataFrame = DatasetReader.json(file)

  /** Reads a JSON file. */
  def json(file: String, mode: JSON.Mode, schema: StructType): DataFrame = json(Paths.get(file), mode, schema)

  /** Reads a JSON file. */
  def json(file: Path, mode: JSON.Mode, schema: StructType): DataFrame = DatasetReader.json(file, mode, schema)

  /** Reads an ARFF file. */
  def arff(file: String): DataFrame = arff(Paths.get(file))

  /** Reads an ARFF file. */
  def arff(file: Path): DataFrame = DatasetReader.arff(file)

  /** Reads a SAS7BDAT file. */
  def sas(file: String): DataFrame = sas(Paths.get(file))

  /** Reads a SAS7BDAT file. */
  def sas(file: Path): DataFrame = DatasetReader.sas(file)

  /** Reads an Apache Arrow file. */
  def arrow(file: String): DataFrame = arrow(Paths.get(file))

  /** Reads an Apache Arrow file. */
  def arrow(file: Path): DataFrame = DatasetReader.arrow(file)

  /** Reads an Apache Avro file. */
  def avro(file: String, schema: org.apache.avro.Schema): DataFrame = avro(Paths.get(file), schema)

  /** Reads an Apache Avro file. */
  def avro(file: Path, schema: org.apache.avro.Schema): DataFrame = DatasetReader.avro(file, schema)

  /** Reads an Apache Parquet file. */
  def parquet(file: String): DataFrame = parquet(Paths.get(file))

  /** Reads an Apache Parquet file. */
  def parquet(file: Path): DataFrame = DatasetReader.parquet(file)

  /** Reads a LivSVM file. */
  def libsvm(file: String): Dataset[Instance[SparseArray]] = libsvm(Paths.get(file))

  /** Reads a LivSVM file. */
  def libsvm(file: Path): Dataset[Instance[SparseArray]] = DatasetReader.libsvm(file)

  /** Reads a Wavefront OBJ file. */
  def wavefront(file: String): (Array[Array[Double]], Array[Array[Int]]) = wavefront(Paths.get(file))

  /** Reads a Wavefront OBJ file. The OBJ file format is a simple format of 3D geometry including
    * the position of each vertex, the UV position of each texture coordinate vertex,
    * vertex normals, and the faces that make each polygon defined as a list of vertices,
    * and texture vertices. Vertices are stored in a counter-clockwise order by default,
    * making explicit declaration of face normals unnecessary. OBJ coordinates have no units,
    * but OBJ files can contain scale information in a human readable comment line.
    *
    * Note that we parse only vertex and face elements. All other information ignored.
    *
    * @param file the file path
    * @return a tuple of vertex array and edge array.
    */
  def wavefront(file: Path): (Array[Array[Double]], Array[Array[Int]]) = {
    val vertices = new ArrayBuffer[Array[Double]]
    val edges = new ArrayBuffer[Array[Int]]

    Source.fromFile(file.toFile).getLines foreach { line =>
      val tokens = line.split("\\s+")

      if (tokens.size > 1) {
        tokens(0) match {
          case "v" =>
            require(tokens.size == 4 || tokens.size == 5, s"Invalid vertex element: $line")
            vertices += Array(tokens(1).toDouble, tokens(2).toDouble, tokens(3).toDouble)
          case "f" =>
            require(tokens.size >= 3, s"Invalid face element: $line")
            val face = tokens.drop(1).map(_.toInt - 1)
            for (i <- 1 until face.size) edges += Array(face(i-1), face(i))
            edges += Array(face(0), face.last)
          case _ => // ignore all other elements
        }
      }
    }
    (vertices.toArray, edges.toArray)
  }
}
