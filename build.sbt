name := "spray-album"

version := "0.1.0"

scalaVersion := "2.11.8"

libraryDependencies ++={
  val sprayV = "1.3.3"
  val akkaV = "2.3.9"
  val slickV = "3.1.1"

  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "io.spray" %%  "spray-json" % "1.3.1",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka" %% "akka-slf4j" % akkaV,
    "com.typesafe.slick" %% "slick" % slickV,
    "com.typesafe.slick" %% "slick-hikaricp" % slickV,
    "com.h2database" % "h2" % "1.3.175",
    "com.gettyimages" %% "spray-swagger" % "0.5.1"
  )

}

mainClass := Some("com.amitrai48.MainApp")