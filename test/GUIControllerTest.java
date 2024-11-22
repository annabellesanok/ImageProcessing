import org.junit.Test;

import java.util.List;

import generalcommandcallbacksgui.Controller;
import generalcommandcallbacksgui.Features;
import model.CollageProject;
import model.filtercommands.FilterCommand;
import model.Layer;
import model.Project;
import model.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * Checks that the new controller used with the GUI passes the correct inputs to the model
 * CollageProject class with a mock that appends the received inputs to a log.
 */
public class GUIControllerTest {

  Project model = new CollageProject();

  /**
   * Mock class that gives the capacity to check whether the arguments are being passed.
   * in correctly.
   */
  public class ConfirmInputsMock implements Project {
    final StringBuilder log;

    public ConfirmInputsMock(StringBuilder log) {
      this.log = log;
    }

    @Override
    public String getName() {
      return null;
    }

    @Override
    public int getWidth() throws IllegalStateException {
      return 0;
    }

    @Override
    public int getHeight() {
      return 0;
    }

    @Override
    public int getMaxValue() {
      return 0;
    }

    @Override
    public RGBPixel[][] getBackground() {
      return new RGBPixel[0][];
    }

    @Override
    public List<Layer> getLayers() {
      return null;
    }

    @Override
    public Project newProject(String name, int width, int height) {
      log.append("name: " + name + "width: " + width + "height: " + height);
      return null;
    }

    @Override
    public void loadProject(String name, int width, int height, int maxValue, List<Layer> layers) {
      log.append("name: " + name + "\n width: " + width + "\n height: " + height +
              "\n maxValue: " + maxValue);
    }


    @Override
    public String projectInfo() {
      return null;
    }

    @Override
    public void addLayer(String layerName) throws IllegalArgumentException {
      log.append("layername: " + layerName);
    }

    @Override
    public void addImageToLayer(String layerName, String imagePath, int x, int y)
            throws IllegalArgumentException {
      log.append("layername: " + layerName + "imagePath: " + imagePath + "x: " + x + "y: " + y);
    }

    @Override
    public void setFilter(String layerName, FilterCommand filterOption) {
      log.append("layername: " + layerName + "filtercommand: " + filterOption);
    }

    @Override
    public RGBPixel[][] getPixelsBelow(Layer l) {
      return new RGBPixel[0][];
    }

    @Override
    public RGBPixel[][] getProjectPixels() {
      return new RGBPixel[0][];
    }
  }

  @Test
  public void testCorrectInputsAddLayer() {
    StringBuilder ap1 = new StringBuilder();
    Project mock = new ConfirmInputsMock(ap1);
    Features controller = new Controller(mock);
    controller.addLayer("layer1");
    assertEquals("layername: layer1", ap1.toString());
  }

  @Test
  public void testCorrectInputsNewProject() {
    StringBuilder ap1 = new StringBuilder();
    Project mock = new ConfirmInputsMock(ap1);
    Features controller = new Controller(mock);
    controller.makeNewProject("p1", 20, 20);
    assertEquals("name: p1width: 20height: 20", ap1.toString());
  }

  @Test
  public void testCorrectInputsAddImageToLayer() {
    StringBuilder ap1 = new StringBuilder();
    Project mock = new ConfirmInputsMock(ap1);
    Features controller = new Controller(mock);
    controller.addImageToLayer("layer1", "tako.ppm", 0, 0);
    assertEquals("layername: layer1 imagePath: tako.ppm x: 0 y: 0", ap1.toString());
  }

  @Test
  public void testCorrectInputsSetFilter() {
    StringBuilder ap1 = new StringBuilder();
    Project mock = new ConfirmInputsMock(ap1);
    Features controller = new Controller(mock);
    controller.setFilter("layer1", "red-component");
    assertEquals("layername: layer 1filtercommand red-component", ap1.toString());
  }

}


