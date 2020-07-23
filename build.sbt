name := """used-car-shop-service"""
organization := "ru.ds"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  javaJdbc,
  javaCore,
  "org.mybatis" % "mybatis" % "3.3.0",
  "org.mybatis" % "mybatis-guice" % "3.6",
  "com.google.inject.extensions" % "guice-multibindings" % "4.0",
  "org.hibernate" % "hibernate-core" % "4.2.3.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.2.3.Final",
  "junit" % "junit" % "4.12" % Test,
  "com.h2database" % "h2" % "1.4.188"
)
routesGenerator := InjectedRoutesGenerator
