name := "graphite-query"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-feature")

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)


libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.0",
  "com.twitter" %% "util-collection" % "6.27.0",

  "org.scalatest" %% "scalatest" % "3.0.0-M15" % "test"
)
