name := """playtest"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.0" % Test

libraryDependencies += "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.0.1"
libraryDependencies += "com.typesafe.play" %% "play-ws-standalone-json" % "1.0.1"
libraryDependencies += "com.typesafe.play" %% "play-ws-standalone-xml" % "1.0.1"


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
