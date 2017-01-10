import java.io.FileWriter

import traits.Arg

object Main extends Arg {
  val arq = argstext("file")
  val im = SubImage(arq)
  val instances = im.bodies.groupBy(_._2).toList.sortBy(_._1.hashCode()).zipWithIndex.flatMap { case ((_, seq), idx) =>
    seq map { case ((x, y), _) => (x, y, idx) }
  }
  val classes = instances.map(_._3).distinct
  val header = Seq(s"@relation $arq", s"@attribute x numeric", s"@attribute y numeric", s"@attribute class {${classes.mkString(",")}}")
  val body = "@data" +: instances.map { case (x, y, l) => s"$x,$y,$l" }
  val arff = header ++ body

  val arq2 = arq + ".arff"
  val fw = new FileWriter(arq2)
  fw.write(arff.mkString("\n"))
  fw.close()

  println(s"$arq2 written!")
}
