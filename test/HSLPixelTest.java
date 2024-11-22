import org.junit.Test;

import model.HSLPixel;
import model.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the methods in the HSL pixel class.
 */

public class HSLPixelTest {
  HSLPixel pixel1 = new HSLPixel(110, .4, .6);
  HSLPixel pixel2 = new HSLPixel(240, .9, .1);

  @Test
  public void testgetHue() {
    assertEquals(110, pixel1.getHue(), .001);
    assertEquals(240, pixel2.getHue(), .001);
  }

  @Test
  public void testgetSaturation() {
    assertEquals(.4, pixel1.getSaturation(), .001);
    assertEquals(.9, pixel2.getSaturation(), .001);
  }

  @Test
  public void testgetLightness() {
    assertEquals(.6, pixel1.getLightness(), .001);
    assertEquals(.1, pixel2.getLightness(), .001);
  }

  @Test
  public void testHSLtoRGB() {
    RGBPixel rgbpixel1 = new RGBPixel(125, 193, 112, 255);
    RGBPixel rgbpixel2 = new RGBPixel(2, 2, 48, 255);
    assertEquals(rgbpixel1, pixel1.convertHSLtoRGB());
    assertEquals(rgbpixel2, pixel2.convertHSLtoRGB());
  }


}