package model;

/**
 * A class to represent pixels whose color representation are the values for hue, saturation, and
 * light. Can convert from an HSL pixel to one represented by red, green, and blue values.
 */
public class HSLPixel implements IPixel {
  private final double hue;
  private final double saturation;
  private final double lightness;

  /**
   * Creating a pixel with the triple of hue, saturation, and light if the values are valid.
   *
   * @param hue        0 < the hue < 360
   * @param saturation 0 < the sat < 1
   * @param lightness  0 < the lightness < 1
   */
  public HSLPixel(double hue, double saturation, double lightness) {
    if (hue >= 0 && hue <= 360 && saturation >= 0 && saturation <= 1 && lightness >= 0 &&
            lightness <= 1) {
      this.hue = hue;
      this.saturation = saturation;
      this.lightness = lightness;
    } else {
      throw new IllegalArgumentException("Invalid HSL values");
    }
  }

  /**
   * Gets the hue of this hsl pixel.
   *
   * @return hue field of the pixel
   */
  public double getHue() {
    return this.hue;
  }

  /**
   * Gets the saturation of this hsl pixel.
   *
   * @return saturation field of the pixel
   */
  public double getSaturation() {
    return this.saturation;
  }

  /**
   * Gets the hue of this hsl pixel.
   *
   * @return hue field of the pixel
   */
  public double getLightness() {
    return this.lightness;
  }

  @Override
  public IPixel convertPixel() {
    return this.convertHSLtoRGB();
  }

  @Override
  public IPixel createNew(int val1, int val2, int val3, int val4) {
    return new HSLPixel(val1, val2, val3);
  }

  /**
   * Converts HSL pixels back to RGB.
   */
  public RGBPixel convertHSLtoRGB() {
    int r = (int) (convertFn(this.hue, this.saturation, this.lightness, 0) * 255);
    int g = (int) (convertFn(this.hue, this.saturation, this.lightness, 8) * 255);
    int b = (int) (convertFn(this.hue, this.saturation, this.lightness, 4) * 255);
    return new RGBPixel(r, g, b, 255);
  }

  /**
   * Helper method that performs the translation from the HSL polygonal
   * model to the more familiar RGB model.
   */
  private double convertFn(double hue, double saturation, double lightness, int n) {
    double k = (n + (hue / 30)) % 12;
    double a = saturation * Math.min(lightness, 1 - lightness);
    return lightness - a * Math.max(-1, Math.min(k - 3, Math.min(9 - k, 1)));
  }


  @Override
  public int getRed() {
    return 0;
  }

  @Override
  public int getGreen() {
    return 0;
  }

  @Override
  public int getBlue() {
    return 0;
  }

  @Override
  public int getAlpha() {
    return 0;
  }

}
