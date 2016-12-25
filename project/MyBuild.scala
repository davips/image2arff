import sbt._
import Keys._

object MyBuild extends Build {

  lazy val root: Project = Project("image2arff", file("."))
//    .aggregate(subimage, args)
    .dependsOn(args)
    .dependsOn(subimage)

  lazy val args = ProjectRef(uri("https://github.com/davips/args.git"), "args")
  lazy val subimage = ProjectRef(uri("https://github.com/davips/subimage.git"), "subimage")

}

