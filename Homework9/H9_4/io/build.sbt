name := "smile-io"

libraryDependencies ++= {
  val version = "0.11.0"
  Seq(
    "org.apache.arrow" % "arrow-memory" % version,
    "org.apache.arrow" % "arrow-vector" % version
  )
}

// parquet-jackson includes jackson
libraryDependencies += "org.apache.parquet" % "parquet-hadoop" % "1.10.0" exclude("org.slf4j", "slf4j-log4j12")

// required by parquet
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "3.2.1" exclude("org.slf4j", "slf4j-log4j12")

libraryDependencies += "org.apache.avro" % "avro" % "1.8.2" exclude("org.slf4j", "slf4j-log4j12")

libraryDependencies += "com.epam" % "parso" % "2.0.10"

libraryDependencies += "org.apache.commons" % "commons-csv" % "1.6"

libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.25.2" % Test
