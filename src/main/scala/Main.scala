import java.io.File
import javax.imageio.ImageIO

object Main extends Arg {
  val img = ImageIO.read(new File("example.bmp"))
  val green = Array.fill(img.getHeight)(Array.fill(img.getWidth)(new Byte))
  for {
    y <- 0 until img.getHeight
    x <- 0 until img.getWidth
  } {
    val color = img.getRGB(x, y)
    //alpha[x][y] = (byte)(color>>24);
    //red[x][y] = (byte)(color>>16);
    green(x)(y) = (color >> 8).toByte
    //blue[x][y] = (byte)(color);
  }
}
