package model;

import java.io.IOException;
import java.util.List;

import model.filtercommands.FilterCommand;

/**
 * Represents a project that can be created, and that can hold multiple layers.
 * These layers consist of images and a filter.
 */
public interface Project {

  /**
   * Gets the name of this project.
   *
   * @return name of the project in form of a String
   */
  String getName();

  /**
   * Gets the width of this project.
   *
   * @return width of the project
   */
  int getWidth();

  /**
   * Gets the height of this project.
   *
   * @return height of the project
   */
  int getHeight();

  /**
   * Gets the max value of any pixel in this project.
   *
   * @return an int represent this maximum vale
   */
  int getMaxValue();

  /**
   * Gets the background.
   *
   * @return
   */
  IPixel[][] getBackground();

  /**
   * Gets returns all the layers in the project.
   *
   * @return list of layers in the project
   */
  List<Layer> getLayers();


  /**
   * Creates a new project in this model with the given name and dimensions.
   *
   * @param width  the width of the project
   * @param height the height of the project
   * @return a new project with a white background layer
   */
  Project newProject(String name, int width, int height);


  // citation: https://www.w3schools.com/java/java_files_create.asp

  /**
   * Loads a project to the program given the file path.
   *
   * @param name     the name of the project being loaded to the model
   * @param width    the desired width of the project being loaded to the model
   * @param height   the desired height of the project being loaded to the model
   * @param maxValue the desired maxValue of the project being loaded to the model
   * @param layers   the layers in the project being loaded to the model
   */
  void loadProject(String name, int width, int height, int maxValue, List<Layer> layers);


  /**
   * Information about the project as one String for saving purposes.
   *
   * @throws IOException if there is an error with writing the file.
   */
  String projectInfo();

  /**
   * Adds a layer with the given name to this project.
   *
   * @param layerName name of the layer to be added
   * @throws IllegalArgumentException if the given name already exists for another layer
   *                                  within the project.
   */
  void addLayer(String layerName) throws IllegalArgumentException;

  /**
   * Adds an image with the given name to the layer with the given name.
   *
   * @param layerName the name of the layer
   * @param imagePath the name of the new image
   * @param x         the x position of the image
   * @param y         the y position of the image
   * @throws IllegalArgumentException if the layer to add to doesn't exist
   */
  void addImageToLayer(String layerName, String imagePath, int x, int y)
          throws IllegalArgumentException;

  /**
   * Adds the given filter to the layer with the given name.
   *
   * @param layerName    the layer to add the filter to
   * @param filterOption the filter command to execute
   */
  void setFilter(String layerName, FilterCommand filterOption);

  /**
   * Gets the pixels below the given layer -- to help with the blend filters.
   */
  IPixel[][] getPixelsBelow(Layer l);

  /**
   * Gets the pixels below the given layer -- to help with the blend filters.
   */
  IPixel[][] getProjectPixels();

}
