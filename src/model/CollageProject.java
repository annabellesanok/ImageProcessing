package model;

import java.util.ArrayList;
import java.util.List;

import model.filtercommands.FilterCommand;
import model.filtercommands.Normal;

/**
 * Represents a project that layers can be added to. Each has a name and properties for size and
 * maximum color value. Projects are the model for this program.
 */
public class CollageProject implements Project {
  private String name;
  private int width;
  private int height;
  private int maxValue;
  private List<Layer> layers;
  private IPixel[][] background;

  /**
   * A project that layers can be added to.
   *
   * @param name       name of the project
   * @param width      width of the project
   * @param height     height of the project
   * @param layers     layers currently in the project
   * @param background background of the project
   */
  public CollageProject(String name, int width, int height, List<Layer> layers,
                        RGBPixel[][] background) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height cannot be negative");
    }
    this.name = name;
    this.width = width;
    this.height = height;
    this.layers = layers;
    this.background = background;
  }


  public CollageProject(String name, int width, int height) {
    this.newProject(name, width, height);
  }

  //empty constructor, which becomes initialized when newProject method is called
  public CollageProject() {
    //explanation above
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public IPixel[][] getBackground() {
    return this.background;
  }

  @Override
  public List<Layer> getLayers() {
    return this.layers;
  }

  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public Project newProject(String name, int width, int height) {
    //handles making the white transparent image as a new default layer
    RGBPixel[][] emptybackground = new RGBPixel[width][height];
    this.width = width;
    this.height = height;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGBPixel pixel = new RGBPixel(255, 255, 255, 255); //White w alpha 255
        emptybackground[x][y] = pixel;
      }
    }
    Project newProject = new CollageProject(name, width, height,
            new ArrayList<Layer>(), emptybackground);
    this.name = name;
    this.width = width;
    this.height = height;
    this.layers = new ArrayList<>();
    this.background = emptybackground;
    return newProject;
  }

  // citation: https://www.w3schools.com/java/java_files_create.asp
  @Override
  public void loadProject(String name, int width, int height, int maxValue, List<Layer> layers) {
    // Set project fields
    this.name = name;
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.layers = layers;
    // Create background image from first layer
    this.background = layers.get(layers.size() - 1).getPixels();
  }

  @Override
  public String projectInfo() {
    String layersContents = "";
    for (int i = 0; i < this.layers.size(); i++) {
      layersContents = layersContents + "\n" +
              this.layers.get(i).getLayerName() + " " + this.layers.get(i).getFilterName() + "\n" +
              this.layers.get(i).getLayerContents();
    }
    String saveInfo = this.name + "\n" + this.getWidth() + " " + this.getHeight() + "\n" +
            "255" + "\n" + layersContents;
    return saveInfo;
  }

  @Override
  public void addLayer(String layerName) throws IllegalArgumentException {
    int width = this.width;
    int height = this.height;
    //handles making the white transparent image as a new default layer
    RGBPixel[][] layer = new RGBPixel[width][height];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGBPixel pixel = new RGBPixel(255, 255, 255, 0); // White w alpha 0
        layer[x][y] = pixel;
      }
    }
    Layer newlayer = new CollageLayer(layerName, layer);
    //puts the normal filter on this layer
    FilterCommand normal = new Normal();
    normal.execute(newlayer);
    //adds the layer to this project
    this.layers.add(newlayer);
  }

  @Override
  public void addImageToLayer(String layerName, String imageName, int x, int y)
          throws IllegalArgumentException {
    for (int i = 0; i < this.layers.size(); i++) {
      if (this.layers.get(i).getLayerName().equals(layerName)) {
        this.layers.get(i).addImageToLayer(imageName, x, y);
      }
    }
  }

  @Override
  public void setFilter(String layerName, FilterCommand filterOption) {
    for (int i = 0; i < this.layers.size(); i++) {
      if (this.layers.get(i).getLayerName().equals(layerName)) {
        this.layers.get(i).setFilter(filterOption);
      }
    }
  }

  @Override
  public IPixel[][] getPixelsBelow(Layer l) {
    int thisindex = this.layers.indexOf(l);
    if (thisindex == 0) {
      return this.getBackground();
    }
    Layer layerbelow = this.layers.get(thisindex - 1);
    return layerbelow.getPixels();
  }

  //@Override
  /*
  public IPixel[][] getProjectPixels() {
    int width = this.width;
    int height = this.height;
    RGBPixel[][] prevLayerPixels = null;
    for (int i = 0; i < layers.size(); i++) {
      IPixel[][] currentLayerPixels = this.layers.get(i).getPixels();
      RGBPixel[][] blendedPixels = new RGBPixel[height][width];
      for (int j = 0; j < height; j++) {
        for (int k = 0; k < width; k++) {
          // blend the pixel on the existing layer with the blended pixels from the previous layers
          IPixel existingPixel = currentLayerPixels[j][k];
          RGBPixel blendedPixel = prevLayerPixels != null ? prevLayerPixels[j][k] :
                  new RGBPixel(0, 0, 0, 0);
          int alpha = existingPixel.getAlpha();
          int newRed = (alpha * existingPixel.getRed() + (255 - alpha)
                  * blendedPixel.getRed()) / 255;
          int newGreen = (alpha * existingPixel.getGreen() + (255 - alpha)
                  * blendedPixel.getGreen()) / 255;
          int newBlue = (alpha * existingPixel.getBlue() + (255 - alpha)
                  * blendedPixel.getBlue()) / 255;
          int newAlpha = (existingPixel.getAlpha() + (255 - existingPixel.getAlpha())
                  * blendedPixel.getAlpha()) / 255;
          blendedPixels[j][k] = new RGBPixel(newRed, newGreen, newBlue, newAlpha);
        }
      }
      prevLayerPixels = blendedPixels;
    }
    return prevLayerPixels;
  }
   */

  @Override
  public IPixel[][] getProjectPixels() {
    RGBPixel[][] prevLayerPixels = null;
    for (int i = 0; i < layers.size(); i++) {
      IPixel[][] currentLayerPixels = this.layers.get(i).getPixels();
      int height = currentLayerPixels.length;
      int width = currentLayerPixels[0].length;
      RGBPixel[][] blendedPixels = new RGBPixel[height][width];
      for (int j = 0; j < height; j++) {
        for (int k = 0; k < width; k++) {
          IPixel existingPixel = currentLayerPixels[j][k];
          RGBPixel blendedPixel = prevLayerPixels != null ? prevLayerPixels[j][k] :
                  new RGBPixel(0, 0, 0, 0);
          int alpha = existingPixel.getAlpha();
          int newRed = (alpha * existingPixel.getRed() + (255 - alpha)
                  * blendedPixel.getRed()) / 255;
          int newGreen = (alpha * existingPixel.getGreen() + (255 - alpha)
                  * blendedPixel.getGreen()) / 255;
          int newBlue = (alpha * existingPixel.getBlue() + (255 - alpha)
                  * blendedPixel.getBlue()) / 255;
          int newAlpha = (existingPixel.getAlpha() + (255 - existingPixel.getAlpha())
                  * blendedPixel.getAlpha()) / 255;
          blendedPixels[j][k] = new RGBPixel(newRed, newGreen, newBlue, newAlpha);
        }
      }
      prevLayerPixels = blendedPixels;
    }
    return prevLayerPixels;
  }

}
