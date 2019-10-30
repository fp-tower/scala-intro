lazy val baseSettings: Seq[Setting[_]] = Seq(
  scalaVersion       := "2.13.0",
  scalacOptions     ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions", "-language:existentials",
    "-unchecked",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard"
  ),
  resolvers += Resolver.sonatypeRepo("releases")
)

lazy val intro = project.in(file("."))
  .settings(moduleName := "scala-intro")
  .settings(baseSettings: _*)
  .aggregate(core, slides)
  .dependsOn(core, slides)

lazy val core = project
  .settings(moduleName := "scala-intro-core")
  .settings(baseSettings: _*)

lazy val docs = project.in(file("slides2"))
  .enablePlugins(MdocPlugin)

lazy val slides = project
  .settings(moduleName := "scala-intro-slides")
  .settings(baseSettings: _*)
  .settings(
    tutSourceDirectory := baseDirectory.value / "tut",
    tutTargetDirectory := baseDirectory.value / "../docs",
    watchSources ++= (tutSourceDirectory.value ** "*.html").get
  ).dependsOn(core)
  .enablePlugins(TutPlugin)
