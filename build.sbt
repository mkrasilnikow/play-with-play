name := """used-car-shop-service"""
organization := "ru.ds"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SwaggerPlugin)
swaggerPlayJava := true
swaggerDomainNameSpaces := Seq("models")

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  javaJdbc,
  evolutions,
  guice,
  "com.h2database" % "h2" % "1.4.199",
  "org.mybatis" % "mybatis" % "3.5.4",
  "org.mybatis" % "mybatis-guice" % "3.12",
  "org.webjars" % "swagger-ui" % "2.2.0",
)

// routesGenerator := InjectedRoutesGenerator


