package generalcommandcallbacksgui;

import java.util.List;

import model.IPixel;
import model.Layer;

/**
 * The desired features interface for a controller for the image processor. Handles user
 * instruction to save/load, add layers/images/filters, etc.
 */
public interface Features {
  /**
   * Creates a new collage project to process and edit layers of images.
   *
   * @param name   the name of the new project
   * @param width  the width of the new project
   * @param height the height of the new project
   */
  void makeNewProject(String name, int width, int height);

  /**
   * Loads a project from a saved collage text file into the program.
   *
   * @param filePath the file path of the text file with the saved project
   */
  void loadProject(String filePath);

  /**
   * Sends the model the image and layer name to add that image to, along with the position
   * in the form of x and y coords.
   *
   * @param layerName the name of the layer to add the image to
   * @param imagePath the path of the image file
   * @param x         the x coord of the posn
   * @param y         the y coord of the posn
   */
  void addImageToLayer(String layerName, String imagePath, int x, int y);

  /**
   * Adds a layer with the name given by the user to the program's project.
   *
   * @param layerName the name of the new layer
   */
  void addLayer(String layerName);

  /**
   * Sets a filter onto the layer with the given name.
   *
   * @param layerName  the layer to add the filter to
   * @param filterName the name of the filter to add
   */
  void setFilter(String layerName, String filterName);

  /**
   * Saves an image from one layer to the device.
   *
   * @param imagePath the path to save the image to
   * @param layerName the name of the layer whose image will be saved
   */
  void saveImage(String imagePath, String layerName);

  /**
   * Saves the project in the form of a "collage" text file.
   *
   * @param filePath the file path to save to
   */
  void saveProject(String filePath);

  /**
   * Get the pixels on the layer with the given name.
   *
   * @param layername the name of the layer
   * @return the 2D array of pixels showing the image on that layer
   */
  IPixel[][] getLayerPixels(String layername);

  /**
   * Gets the layers of this project as a list.
   *
   * @return the list of layers that make up this collage project
   */
  List<Layer> getLayers();

  /**
   * Get the 2D array of pixels that make up the composite image of the project.
   *
   * @return a 2D array of pixels showing the collage
   */
  IPixel[][] getProjectPixels();

}
