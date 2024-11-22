package model;

import model.filtercommands.FilterCommand;
import model.filtercommands.Normal;

/**
 * Represents a layer that can be added, and that can be set to one of the following filters.
 * Each layer has a name, a list of images that have been added to it, the filter that it is set
 * to, and the position of the layer (with 0 representing the bottom-most background layer). It
 * also has a project, which is the project that it belongs to.
 */

public class CollageLayer implements Layer {
  private final String name;
  private FilterCommand filter;
  private RGBPixel[][] pixels;
  private RGBPixel[][] ogpixels;
  private boolean hasImage;

  /**
   * Represents a layer of a collage that can be added, and that images can be added to.
   *
   * @param name   name of the layer
   * @param pixels pixels that make up the layer
   */
  public CollageLayer(String name, RGBPixel[][] pixels) {
    if (name == null) {
      throw new IllegalArgumentException("No null values");
    }
    this.name = name;
    this.pixels = pixels;
    this.ogpixels = this.getPixelsCopy();
    this.filter = new Normal();
    this.hasImage = false;
  }

  /**
   * Gets a copy of the current pixels on this layer.
   *
   * @return the 2D array of pixels on this layer.
   */
  public RGBPixel[][] getPixelsCopy() {

    RGBPixel[][] destination = new RGBPixel[this.pixels.length][];

    for (int i = 0; i < this.pixels.length; ++i) {

      // allocating space for each row of destination array
      destination[i] = new RGBPixel[this.pixels[i].length];
      System.arraycopy(this.pixels[i], 0, destination[i], 0, destination[i].length);
    }
    return destination;
  }

  @Override
  public RGBPixel[][] getOgPixelsCopy() {

    RGBPixel[][] destination = new RGBPixel[this.ogpixels.length][];

    for (int i = 0; i < this.ogpixels.length; ++i) {

      // allocating space for each row of destination array
      destination[i] = new RGBPixel[this.ogpixels[i].length];
      System.arraycopy(this.ogpixels[i], 0, destination[i], 0, destination[i].length);
    }
    return destination;
  }

  @Override
  public boolean hasImage() {
    return this.hasImage;
  }

  @Override
  public void setFilter(FilterCommand f) {
    this.filter = f;
    f.execute(this);
  }

  /**
   * Adding the image onto the layer.
   *
   * @param imagepath the name of the new image
   * @param x         the x position of the image
   * @param y         the y position of the image
   */
  public void addImageToLayer(String imagepath, int x, int y) {
    int width = this.pixels.length;
    int height = this.pixels[0].length;
    IPixel[][] imagePixels = null;
    String extension = getFileExtension(imagepath);
    if (extension.equals("ppm")) {
      imagePixels = ImageUtil.readImage(imagepath);
    } else if (extension.equals("jpg")) {
      imagePixels = ImageUtil.readJPEG(imagepath);
    } else if (extension.equals("png")) {
      imagePixels = ImageUtil.readPNG(imagepath);
    } else {
      return;
    }
    int imageWidth = imagePixels[0].length;
    int imageHeight = imagePixels.length;
    for (int j = 0; j < imageHeight; j++) {
      for (int i = 0; i < imageWidth; i++) {
        IPixel pixel = imagePixels[j][i];
        // calculate the position on the layer for this pixel
        int layerX = x + i;
        int layerY = y + j;
        // check if the position is within the layer bounds
        if (layerX >= 0 && layerX < width && layerY >= 0 && layerY < height) {
          // blend the pixel on the existing layer with this new pixel (to account for transparency)
          RGBPixel existingPixel = this.pixels[layerX][layerY];
          int alpha = pixel.getAlpha();
          int newRed = (alpha * pixel.getRed() + (255 - alpha) * existingPixel.getRed()) / 255;
          int newGreen = (alpha * pixel.getGreen() + (255 - alpha) *
                  existingPixel.getGreen()) / 255;
          int newBlue = (alpha * pixel.getBlue() + (255 - alpha) * existingPixel.getBlue()) / 255;
          int newAlpha = existingPixel.getAlpha() + alpha
                  - (existingPixel.getAlpha() * alpha) / 255;
          //updates the pixels field of this layer
          this.pixels[layerX][layerY] = new RGBPixel(newRed, newGreen, newBlue, newAlpha);
          this.ogpixels[layerX][layerY] = new RGBPixel(newRed, newGreen, newBlue, newAlpha);
          this.hasImage = true;
        }
      }
    }
  }

  private String getFileExtension(String filename) {
    String extension = "";
    int dotIndex = filename.lastIndexOf('.');
    if (dotIndex > 0 && dotIndex < filename.length() - 1) {
      extension = filename.substring(dotIndex + 1);
    }
    return extension;
  }

  @Override
  public String getFilterName() {
    return this.filter.toString();
  }

  @Override
  public String getLayerName() {
    return this.name;
  }

  @Override
  public String getLayerContents() {
    int width = this.pixels.length;
    int height = this.pixels[0].length;
    String layerContentFormat = "";
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGBPixel pixel = pixels[x][y]; //gets the pixel at x, y
        layerContentFormat = layerContentFormat + pixel.getRed() + " " + pixel.getGreen() + " " +
                pixel.getBlue() + "\n";
      }
    }
    return layerContentFormat;
  }

  @Override
  public IPixel[][] getPixels() {
    return this.pixels;
  }

  @Override
  public IPixel[][] getOgPixels() {
    return this.ogpixels;
  }

  @Override
  public void setLayer(IPixel[][] layer) {
    this.pixels = (RGBPixel[][]) layer;
    if (!this.hasImage) {
      this.ogpixels = getPixelsCopy();
    }
  }

}
