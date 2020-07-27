name := """used-car-shop-service"""
organization := "ru.ds"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
val AkkaVersion = "2.6.8"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  javaJdbc,
  evolutions,
  guice,
  "junit" % "junit" % "4.12" % Test,
  "com.h2database" % "h2" % "1.4.199",
  "org.mapstruct" % "mapstruct-jdk8" % "1.2.0.Final",
  "org.mapstruct" % "mapstruct-processor" % "1.2.0.Final",
  "org.mybatis" % "mybatis" % "3.5.4",
  "org.mybatis" % "mybatis-guice" % "3.12",
  "com.google.inject.extensions" % "guice-multibindings" % "4.2.3"
)

// routesGenerator := InjectedRoutesGenerator


