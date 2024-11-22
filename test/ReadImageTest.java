import org.junit.Test;

import model.ImageUtil;
import model.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * This class' purpose is to test the ImageUtil class' methods that read images.
 */
public class ReadImageTest {

  @Test
  public void testreadJPEG() {
    RGBPixel[][] imagelayer = new RGBPixel[2][2];
    imagelayer[0][0] = new RGBPixel(176, 131, 174, 255);
    imagelayer[0][1] = new RGBPixel(178, 133, 176, 255);
    imagelayer[1][0] = new RGBPixel(172, 129, 174, 255);
    imagelayer[1][1] = new RGBPixel(173, 130, 175, 255);
    RGBPixel[][] pixels = (RGBPixel[][]) ImageUtil.readJPEG("res/mountains.jpg");
    //get the first 4 new pixels, which should be set to the image pixels
    RGBPixel pixel00 = pixels[0][0];
    RGBPixel pixel01 = pixels[0][1];
    RGBPixel pixel10 = pixels[1][0];
    RGBPixel pixel11 = pixels[1][1];
    //put them into an array to check against the expected pixel values
    RGBPixel[][] arrayof4 = new RGBPixel[2][2];
    arrayof4[0][0] = pixel00;
    arrayof4[0][1] = pixel01;
    arrayof4[1][0] = pixel10;
    arrayof4[1][1] = pixel11;
    assertEquals(imagelayer, arrayof4);
  }

  @Test
  public void testreadPNG() {
    RGBPixel[][] imagelayer = new RGBPixel[2][2];
    imagelayer[0][0] = new RGBPixel(192, 173, 175, 255);
    imagelayer[0][1] = new RGBPixel(199, 181, 179, 255);
    imagelayer[1][0] = new RGBPixel(194, 176, 176, 255);
    imagelayer[1][1] = new RGBPixel(201, 183, 179, 255);
    RGBPixel[][] pixels = (RGBPixel[][]) ImageUtil.readPNG("res/flowers.png");
    //get the first 4 new pixels, which should be set to the image pixels
    RGBPixel pixel00 = pixels[0][0];
    RGBPixel pixel01 = pixels[0][1];
    RGBPixel pixel10 = pixels[1][0];
    RGBPixel pixel11 = pixels[1][1];
    //put them into an array to check against the expected pixel values
    RGBPixel[][] arrayof4 = new RGBPixel[2][2];
    arrayof4[0][0] = pixel00;
    arrayof4[0][1] = pixel01;
    arrayof4[1][0] = pixel10;
    arrayof4[1][1] = pixel11;
    assertEquals(imagelayer, arrayof4);
  }

  @Test
  public void testreadPPM() {
    RGBPixel[][] imagelayer = new RGBPixel[2][2];
    imagelayer[0][0] = new RGBPixel(173, 179, 151, 255);
    imagelayer[0][1] = new RGBPixel(173, 179, 151, 255);
    imagelayer[1][0] = new RGBPixel(173, 179, 151, 255);
    imagelayer[1][1] = new RGBPixel(173, 179, 151, 255);
    RGBPixel[][] pixels = ImageUtil.readImage("res/tako.ppm");
    //get the first 4 new pixels, which should be set to the image pixels
    RGBPixel pixel00 = pixels[0][0];
    RGBPixel pixel01 = pixels[0][1];
    RGBPixel pixel10 = pixels[1][0];
    RGBPixel pixel11 = pixels[1][1];
    //put them into an array to check against the expected pixel values
    RGBPixel[][] arrayof4 = new RGBPixel[2][2];
    arrayof4[0][0] = pixel00;
    arrayof4[0][1] = pixel01;
    arrayof4[1][0] = pixel10;
    arrayof4[1][1] = pixel11;
    assertEquals(imagelayer, arrayof4);
  }

}
