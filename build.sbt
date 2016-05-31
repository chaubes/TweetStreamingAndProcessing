name := "TweetStreamingAndProcessing"

version := "1.0"

scalaVersion := "2.11.7"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaStreamV      = "1.0"
  Seq(
    "com.typesafe.akka"     %% "akka-stream-experimental"             % akkaStreamV,
    "com.typesafe.akka"     %% "akka-http-core-experimental"          % akkaStreamV,
    "com.typesafe.akka"     %% "akka-http-spray-json-experimental"    % akkaStreamV,
    "com.hunorkovacs"       %% "koauth"                               % "1.1.0",
    "org.json4s"            %% "json4s-native"                        % "3.3.0"
  )
}

initialCommands := """|import akka.actor._
                     |import akka.pattern._
                     |import akka.util._
                     |import scala.concurrent._
                     |import scala.concurrent.duration._""".stripMargin

fork in run := true
    