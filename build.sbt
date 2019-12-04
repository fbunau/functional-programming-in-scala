name := "functional-programming-in-scala"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.1.0" % "test"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:existentials",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings",
  "-explaintypes",
  "-Xcheckinit",
  "-Xfatal-warnings",

  //"-Xlint:adapted-args",
  "-Xlint:nullary-unit",
  "-Xlint:inaccessible",
  "-Xlint:nullary-override",
  "-Xlint:infer-any",
  "-Xlint:missing-interpolator",
  "-Xlint:doc-detached",
  "-Xlint:private-shadow",
  "-Xlint:type-parameter-shadow",
  "-Xlint:poly-implicit-overload",
  "-Xlint:option-implicit",
  "-Xlint:delayedinit-select",
  "-Xlint:package-object-classes",
  "-Xlint:stars-align",
  "-Xlint:constant",
  "-Xlint:unused",
  "-Xlint:nonlocal-return",
  "-Xlint:implicit-not-found",
  "-Xlint:serial",
  "-Xlint:valpattern",
  "-Xlint:eta-zero",
  "-Xlint:eta-sam",
  "-Xlint:deprecation",

  "-Ywarn-dead-code",
  "-Ywarn-extra-implicit",
  "-Ywarn-octal-literal",
  "-Ywarn-value-discard",
)