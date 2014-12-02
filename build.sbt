name := """my-first-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws
)

libraryDependencies ++= Seq(
  "org.squeryl" % "squeryl_2.10" % "0.9.5-6",
  "io.spray" %%  "spray-json" % "1.3.1",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc41"
)