name := "image2arff"

version := "1.0"

scalaVersion := "2.12.1"

lazy val root = Project("image2arff", sbt.file(".")).dependsOn(args).dependsOn(args).dependsOn(subimage)

lazy val args = ProjectRef(uri("https://github.com/davips/args.git"), "args")

lazy val subimage = ProjectRef(uri("https://github.com/davips/subimage.git"), "subimage")
