import java.io.File
import javax.imageio.ImageIO

import scala.collection.mutable
import scala.reflect.ClassTag

/**
  * Useful functions to find elements inside an image.
  */
case class SubImage(file: String) {
  private lazy val img = ImageIO.read(new File(file))
  lazy val (w, h) = img.getWidth -> img.getHeight
  lazy val (r, g, b, alpha, color) = {
    val (r, g, b, a, co) = (alocate(0), alocate(0), alocate(0), alocate(0), alocate(0))
    for {x <- 0 until w; y <- 0 until h} {
      val color = img.getRGB(x, h - y - 1)
      r(x)(y) = color >> 16 & 0xff // red
      g(x)(y) = color >> 8 & 0xff // green
      b(x)(y) = color & 0xff // blue
      a(x)(y) = color >> 24 & 0xff // alpha
      co(x)(y) = color
    }
    (r, g, b, a, co)
  }
  lazy val bodies: List[((Int, Int), List[((Int, Int), Int)])] = {
    val m = alocate(initialValue = false)
    for {x <- 0 until w; y <- 0 until h; if r(x)(y) < 255 || g(x)(y) < 255 || b(x)(y) < 255} m(x)(y) = true
    val seq = for {x <- 0 until w; y <- 0 until h; if m(x)(y)} yield {
      val xys = mutable.Queue[((Int, Int), Int)]()
      connect(m, x, y, xys)
      val (xc, yc) = center(xys.map(_._1._1)) -> center(xys.map(_._1._2))
      (xc, yc) -> xys.toList.map { case ((x2, y2), c) => (x2 - xc, y2 - yc) -> c }
    }
    seq.toList
  }

  def alocate[T: ClassTag](initialValue: T): Array[Array[T]] = Array.fill(w)(Array.fill(h)(initialValue.asInstanceOf[T]))

  def connect(m: Array[Array[Boolean]], x: Int, y: Int, xys: mutable.Queue[((Int, Int), Int)]): Unit = {
    xys.enqueue((x, y) -> color(x)(y))
    m(x)(y) = false
    for (dx <- -1 to 1; dy <- -1 to 1)
      if (m(x + dx)(y + dy)) connect(m, x + dx, y + dy, xys)
  }

  def center(seq: Seq[Int]): Int = ((seq.max + seq.min) / 2d).round.toInt
}
