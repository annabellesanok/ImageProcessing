package model;

import java.util.Objects;

/**
 * A class to represent a pixel, which has RGBA values. Pixels make up an image. This can be
 * converted into an HSL pixel as well.
 */
public class RGBPixel implements IPixel {
  private final int red;
  private final int green;
  private final int blue;
  private int alpha;

  /**
   * A constructor for a pixel.
   *
   * @throws IllegalArgumentException if any of the given values are not between 0 and 255.
   */
  public RGBPixel(int red, int green, int blue, int alpha) {
    if (red >= 0 && red <= 255 && green >= 0 && green <= 255 &&
            blue >= 0 && blue <= 255 && alpha >= 0 && alpha <= 255) {
      this.red = red;
      this.green = green;
      this.blue = blue;
      this.alpha = alpha;
    } else {
      throw new IllegalArgumentException("RGBA values must be in between 0-255");
    }
  }

  /**
   * Constructor for a pixel represented by hsl values -- with no 4th or alpha value.
   */
  public RGBPixel(int red, int green, int blue) {
    if (red >= 0 && red <= 255 && green >= 0 && green <= 255 &&
            blue >= 0 && blue <= 255) {
      this.red = red;
      this.green = green;
      this.blue = blue;
    } else {
      throw new IllegalArgumentException("RGBA values must be in between 0-255");
    }
  }

  /**
   * Gets the red value of this pixel.
   *
   * @return the red field
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Gets the green value of this pixel.
   *
   * @return the green field
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Gets the blue value of this pixel.
   *
   * @return the blue field
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Gets the alpha value of this pixel.
   *
   * @return the alpha field
   */
  public int getAlpha() {
    if (this.alpha == -1) {
      return 255;
    }
    return this.alpha;
  }

  @Override
  public double getHue() {
    return 0;
  }

  @Override
  public double getSaturation() {
    return 0;
  }

  @Override
  public double getLightness() {
    return 0;
  }

  @Override
  public IPixel convertPixel() {
    return this.rgbtohslpixel();
  }

  @Override
  public IPixel createNew(int val1, int val2, int val3, int val4) {
    return new RGBPixel(val1, val2, val3, val4);
  }

  /**
   * A representation of this pixel's color and transparency values in the form of a string.
   */
  public String toString() {
    return red + " " + green + " " + blue + " " + alpha;
  }

  /**
   * Overrides equals method and compares if two pixels are equal based off of their RBGA values.
   *
   * @param other the other object being compared to this object
   * @return a boolean, true if the two objects are pixels with the same color values
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof RGBPixel)) {
      return false;
    } else if (this == other) {
      return true;
    } else {
      RGBPixel otherPixel = (RGBPixel) other;
      return this.toString().equals(otherPixel.toString());
    }
  }

  /**
   * Overrides hashCode using toString.
   *
   * @return integer representing the hashCode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.toString());
  }

  /**
   * Converts a pixel to a new pixel with HSL values.
   */
  public HSLPixel rgbtohslpixel() {
    double r = this.getRed() / 255.0;
    double g = this.getGreen() / 255.0;
    double b = this.getBlue() / 255.0;
    double componentMax = Math.max(r, Math.max(g, b));
    double componentMin = Math.min(r, Math.min(g, b));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin) / 2;
    double hue;
    double saturation;
    if (delta == 0) {
      hue = 0;
      saturation = 0;
    } else {
      saturation = delta / (1 - Math.abs(2 * lightness - 1));
      hue = 0;
      if (componentMax == r) {
        hue = (g - b) / delta;
        while (hue < 0) {
          hue += 6; //hue must be positive to find the appropriate modulus
        }
        hue = hue % 6;
      } else if (componentMax == g) {
        hue = (b - r) / delta;
        hue += 2;
      } else if (componentMax == b) {
        hue = (r - g) / delta;
        hue += 4;
      }

      hue = hue * 60;
    }
    return new HSLPixel(hue, saturation, lightness);
  }
}
