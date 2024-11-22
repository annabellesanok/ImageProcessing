import java.util.List;
import generalcommandcallbacksgui.Features;
import model.Layer;
import model.RGBPixel;

/**
 * A mock class to check that the GUI sends the controller the correct inputs for
 * its commands.
 */
public class GUIViewCorrectInputToCtrlMock implements Features {
  final StringBuilder log;

  GUIViewCorrectInputToCtrlMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void makeNewProject(String name, int width, int height) {
    log.append("new project with name: " + name + " width: " + width + " height: " + height);
  }

  @Override
  public void loadProject(String filePath) {
    log.append("load project: " + filePath);
  }

  @Override
  public void addImageToLayer(String layerName, String imagePath, int x, int y) {
    log.append("add image to layer. layer: " + layerName + " image to add: " + imagePath + " at " +
            x + ", " + y);
  }

  @Override
  public void addLayer(String layerName) {
    log.append("add layer: " + layerName);
  }

  @Override
  public void setFilter(String layerName, String filterName) {
    log.append("setting filter: " + filterName + " on " + layerName);
  }

  @Override
  public void saveImage(String imagePath, String layerName) {
    log.append("save image as " + imagePath + " on " + layerName);
  }

  @Override
  public void saveProject(String filePath) {
    log.append("save project as: " + filePath);
  }

  @Override
  public RGBPixel[][] getLayerPixels(String layername) {
    log.append("get pixel array on: " + layername);
    return null;
  }

  @Override
  public List<Layer> getLayers() {
    return null;
  }

  @Override
  public RGBPixel[][] getProjectPixels() {
    return new RGBPixel[0][];
  }
}
