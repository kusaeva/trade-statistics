name := "trade-statistics"
organization := "com.github.kusaeva"

version := "0.1"

scalaVersion := "2.13.0"

//libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0-M4"

libraryDependencies ++= Seq(
  "com.typesafe.akka"     %% "akka-http"            % "10.1.9",
  "com.typesafe.akka"     %% "akka-stream"          % "2.5.23",
  "org.scalactic"         %% "scalactic"            % "3.0.8",
  "org.scalatest"         %% "scalatest"            % "3.0.8" % "test",
  "com.typesafe.akka"     %% "akka-testkit"         % "2.5.23" % Test,
  "com.typesafe.akka"     %% "akka-http-spray-json" % "10.1.9",
  "com.typesafe.akka"     %% "akka-http-testkit"    % "10.1.9",
  "com.softwaremill.sttp" %% "core"                 % "1.6.4" % "test"
)

enablePlugins(JavaAppPackaging)

dockerBaseImage := "anapsix/alpine-java"
dockerExposedPorts ++= Seq(8080)
