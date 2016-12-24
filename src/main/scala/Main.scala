import java.io.File
import javax.imageio.ImageIO

import scala.collection.mutable

object Main extends Arg {
  val startsOfList = Seq()
  val img = ImageIO.read(new File("fig2.bmp"))
  val m = Array.fill(img.getWidth)(Array.fill(img.getHeight)(false))
  for {x <- 0 until img.getWidth; y <- 0 until img.getHeight} {
    val color = img.getRGB(x, y)
    //    m(x)(y) = (color >> 24  & 0xff ) < 255 // alpha
    //    m(x)(y) = (color >> 16  & 0xff ) < 255 // red
    m(x)(y) = (color >> 8 & 0xff) < 255 // green
    //    m(x)(y) = (color) < 255 // blue

  }


  def connect(m: Array[Array[Boolean]], x: Int, y: Int, xys: mutable.Queue[(Int, Int)]): Unit = {
    def walk(dx: Int, dy: Int) = if (m(x + dx)(y + dy)) connect(m, x + dx, y + dy, xys)

    xys.enqueue((x, y))
    m(x)(y) = false
    walk(-1, -1)
    walk(-1, 0)
    walk(-1, 1)
    walk(0, -1)
    walk(0, 1)
    walk(1, -1)
    walk(1, 0)
    walk(1, 1)
  }

  def center(seq: Seq[Int]): Int = ((seq.max + seq.min) / 2d).round.toInt

  for {x <- 0 until img.getWidth; y <- 0 until img.getHeight} {
    if (m(x)(y)) {
      val xys = mutable.Queue[(Int, Int)]()
      connect(m, x, y, xys)
      val len = xys.length
      val (discard, n) = if (len % 2 == 0) (len / 2 - 1, 2) else (len / 2, 1)
      val (xc, yc) = center(xys.map(_._1)) -> center(xys.map(_._2))
      println(xc + " " + yc)
    }
  }
}
