import com.typesafe.startscript.StartScriptPlugin

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

name := "ScalaConfJPCountDown"

version := "1.0"

scalaVersion := "2.9.2"

resolvers += "twitter-repo" at "http://maven.twttr.com"

libraryDependencies ++= Seq("com.twitter" % "finagle-core" % "1.9.0", "com.twitter" % "finagle-http" % "1.9.0")

libraryDependencies ++= Seq(
  "org.twitter4j" % "twitter4j-core" % "2.2.5",
  "org.twitter4j" % "twitter4j-stream" % "2.2.5"
)