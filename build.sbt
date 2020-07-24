name := """used-car-shop-service"""
organization := "ru.ds"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  jdbc,
  evolutions,
  guice,
  "junit" % "junit" % "4.12" % Test,
  "com.h2database" % "h2" % "1.4.199"
)

// routesGenerator := InjectedRoutesGenerator


