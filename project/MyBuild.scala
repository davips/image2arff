import sbt._
import Keys._

object MyBuild extends Build {

  lazy val root: Project = Project("image2arff", file("."))
    .dependsOn(p1)
  lazy val p1 = ProjectRef(uri("https://github.com/davips/args.git"), "args")

}

