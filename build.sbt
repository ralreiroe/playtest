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

libraryDependencies += "com.github.kxbmap" %% "configs" % "0.4.4"



fork in Test := true
envVars in Test := Map("envbasedir" -> "foo")
javaOptions += "-Dconfig.file=/i/p/ralfoenning/playtest/test/resources/application2.conf" //for ConfigFactory.load()

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

//https://stackoverflow.com/questions/7979336/how-do-i-get-sbt-to-gather-all-the-jar-files-my-code-depends-on-into-one-place
//retrieveManaged := true   //uncomment to all the project's dependencies in lib_managed
