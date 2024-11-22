import org.junit.Test;

import model.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the constructor and methods in the RGB pixel class.
 */
public class RGBPixelTest {
  RGBPixel pixel1 = new RGBPixel(100, 100, 100, 255);
  RGBPixel pixel2 = new RGBPixel(4, 16, 250, 100);

  @Test
  public void testNegativeRed() {
    try {
      this.pixel1 = new RGBPixel(-4, 100, 100, 100);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  //tests when red is over 255
  @Test
  public void testInvalidRed() {
    try {
      this.pixel1 = new RGBPixel(300, 100, 100, 100);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  //tests when green is negative
  @Test
  public void testNegativeGreen() {
    try {
      this.pixel1 = new RGBPixel(100, -50, 100, 100);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  //tests when green is over 255
  @Test
  public void testInvalidGreen() {
    try {
      this.pixel1 = new RGBPixel(100, 500, 100, 100);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  //tests when blue is negative
  @Test
  public void testNegativeBlue() {
    try {
      this.pixel1 = new RGBPixel(100, 100, -12, 100);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  //tests when blue is over 255
  @Test
  public void testInvalidBlue() {
    try {
      this.pixel1 = new RGBPixel(100, 100, 300, 100);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  //tests when alpha is negative
  @Test
  public void testNegativeAlpha() {
    try {
      this.pixel1 = new RGBPixel(100, 100, 100, -41);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  //tests when alpha is over 255
  @Test
  public void testInvalidAlpha() {
    try {
      this.pixel1 = new RGBPixel(100, 100, 100, 300);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  @Test
  public void testgetRed() {
    assertEquals(100, pixel1.getRed());
    assertEquals(4, pixel2.getRed());
  }

  @Test
  public void testgetBlue() {
    assertEquals(100, pixel1.getBlue());
    assertEquals(250, pixel2.getBlue());
  }

  @Test
  public void testgetGreen() {
    assertEquals(100, pixel1.getGreen());
    assertEquals(16, pixel2.getGreen());
  }

  @Test
  public void testgetAlpha() {
    assertEquals(255, pixel1.getAlpha());
    assertEquals(100, pixel2.getAlpha());
  }

  @Test
  public void testToString() {
    assertEquals("100 100 100 255", pixel1.toString());
    assertEquals("4 16 250 100", pixel2.toString());
  }


}
