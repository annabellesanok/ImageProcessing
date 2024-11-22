package model;


/**
 * An interface to represent a pixel and the behaviors one would need to get information
 * about a certain pixel. Pixels make up images.
 */
public interface IPixel {

  /**
   * Gets the red value of this pixel, if applicable.
   *
   * @return the red field
   */
  int getRed();

  /**
   * Gets the green value of this pixel, if applicable.
   *
   * @return the green field
   */
  int getGreen();

  /**
   * Gets the blue value of this pixel, if applicable.
   *
   * @return the blue field
   */
  int getBlue();

  /**
   * Gets the alpha value of this pixel, if applicable.
   *
   * @return the alpha field
   */
  int getAlpha();


  /**
   * Gets the hue of this hsl pixel.
   *
   * @return hue field of the pixel
   */
  double getHue();

  /**
   * Gets the saturation of this hsl pixel.
   *
   * @return saturation field of the pixel
   */
  double getSaturation();

  /**
   * Gets the hue of this hsl pixel.
   *
   * @return hue field of the pixel
   */
  double getLightness();

  /**
   * Converts an RGB to an HSL pixel, or an HSL pixel to an RGB pixel with the proper values.
   */
  IPixel convertPixel();

  /**
   * Creates a new pixel of the correct type with the given values as its triple.
   */
  IPixel createNew(int val1, int val2, int val3, int val4);
}
