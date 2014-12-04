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
  "com.cloudphysics" %% "jerkson" % "0.6.1",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc41"
)