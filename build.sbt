
lazy val partial = (project in file("."))
  .settings(
    scalaVersion := "2.12.2",
    organization := "com.github.methrat0n",
    scalacOptions := Seq (
      "-encoding", "utf-8",
      "-explaintypes",
      "-deprecation",
      "-unchecked",
      "-feature",
      "-Ywarn-value-discard",
      "-Ywarn-unused",
      "-Ypartial-unification",
      "-Ywarn-infer-any",
      "-Xcheckinit",
      "-Xfatal-warnings",
      "-Xlint"
    ),
    scalariformSettings,
    resolvers += Resolver.sonatypeRepo("releases"),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    libraryDependencies += scalaVersion("org.scala-lang" % "scala-reflect" % _).value
  )

lazy val test = (project in file("./test"))
  .settings(
    scalaVersion := "2.12.2",
    organization := "com.github.methrat0n",
    scalacOptions := Seq (
      "-encoding", "utf-8",
      "-explaintypes",
      "-deprecation",
      "-unchecked",
      "-feature",
      "-Ywarn-value-discard",
      "-Ywarn-unused",
      "-Ypartial-unification",
      "-Ywarn-infer-any",
      "-Xcheckinit",
      "-Xfatal-warnings",
      "-Xlint"
    ),
    resolvers += Resolver.sonatypeRepo("releases"),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    scalariformSettings,
  ).dependsOn(partial)

import scalariform.formatter.preferences._

lazy val scalariformSettings = Seq(
  scalariformPreferences := scalariformPreferences.value
    .setPreference(AlignSingleLineCaseStatements, true)
    .setPreference(CompactControlReadability, true)
    .setPreference(DanglingCloseParenthesis, Force)
    .setPreference(IndentLocalDefs, true)
    .setPreference(NewlineAtEndOfFile, true)
)
