package model;

import model.filtercommands.FilterCommand;

/**
 * Represents a layer that can be added, and that can be set with a filter.
 * * An unlimited amount of images can also be added to this layer. Layers make up a project.
 */
public interface Layer {

  RGBPixel[][] getOgPixelsCopy();

  boolean hasImage();

  /**
   * Sets the layer to the given filter.
   *
   * @param c represents a filter that the user decides to set this layer to
   */
  void setFilter(FilterCommand c);

  /**
   * Adds an image with the given name to the layer with the given name.
   *
   * @param imageName the name of the new image
   * @param x         the x position of the image
   * @param y         the y position of the image
   * @throws IllegalArgumentException if the layer to add to doesn't exist
   */
  void addImageToLayer(String imageName, int x, int y)
          throws IllegalArgumentException;

  /**
   * Returns this layer's name in the form of a string.
   */
  String getLayerName();

  /**
   * Returns this layer's filter name in the form of a string.
   */
  String getFilterName();

  /**
   * Returns the contents of the image of this layer by describing each pixel in the image with its
   * RGBA values. The contents are ordered as (0,0), (0,1), ... (0, width), (1, 0), etc.
   *
   * @return a String representing the contents of the layer
   */
  String getLayerContents();

  /**
   * Gets the pixels that make up this layer.
   *
   * @return the pixels field
   */
  IPixel[][] getPixels();

  /**
   * Gets the ogpixels field in this layer.
   *
   * @return the pixels field
   */
  IPixel[][] getOgPixels();

  /**
   * Sets the layer to the given input array.
   *
   * @param layer sets the layer field to the given 2d array of pixels
   */
  void setLayer(IPixel[][] layer);

}
