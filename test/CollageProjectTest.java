import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import controller.commands.LoadProjectCommand;
import controller.commands.SaveProjectCommand;
import model.CollageLayer;
import model.CollageProject;
import model.IPixel;
import model.Layer;
import model.RGBPixel;
import model.Project;
import model.filtercommands.RedComponent;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests the project methods for the model. Testing observers, setting filters, etc.
 */
public class CollageProjectTest {

  @Test
  public void testNewProject() {
    Project p1 = new CollageProject();
    RGBPixel[][] emptybackground = new RGBPixel[3][3];
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 3; x++) {
        RGBPixel pixel = new RGBPixel(255, 255, 255, 255); // White w alpha 255
        emptybackground[x][y] = pixel;
      }
    }
    IPixel[][] actualbackground = p1.newProject("name", 3, 3).getBackground();
    assertEquals(emptybackground, actualbackground);
  }

  @Test
  public void testAddLayer() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("p2", 2, 2);
    p2.addLayer("layer1");
    assertEquals(1, p2.getLayers().size());
    p2.addLayer("layer2");
    assertEquals(2, p2.getLayers().size());
  }

  @Test
  public void testgetName() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("p2", 2, 2);
    assertEquals("p2", p2.getName());
  }

  @Test
  public void testgetWidth() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("p2", 2, 2);
    assertEquals(2, p2.getWidth());
  }

  @Test
  public void testgetHeight() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("p2", 2, 2);
    assertEquals(2, p2.getHeight());
  }

  @Test
  public void testgetBackground() {
    Project p1 = new CollageProject();
    RGBPixel[][] emptybackground = new RGBPixel[3][3];
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 3; x++) {
        RGBPixel pixel = new RGBPixel(255, 255, 255, 255); // White w alpha 255
        emptybackground[x][y] = pixel;
      }
    }
    IPixel[][] actualbackground = p1.newProject("name", 3, 3).getBackground();
    assertEquals(emptybackground, actualbackground);
  }

  @Test
  public void testgetLayers() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("p2", 2, 2);
    p2.addLayer("layer1");
    assertEquals("layer1", p2.getLayers().get(0).getLayerName());
  }

  @Test
  public void testprojectInfo() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("p2", 2, 2);
    p2.addLayer("layer1");
    assertEquals("", p2.projectInfo());
  }

  @Test
  public void testaddImageToLayer() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("p2", 2, 2);
    p2.addLayer("layer1");
    p2.addImageToLayer("layer1", "tako.ppm", 0, 0);
    RGBPixel[][] imagelayer = new RGBPixel[2][2];
    imagelayer[0][0] = new RGBPixel(173, 179, 151, 255);
    imagelayer[0][1] = new RGBPixel(173, 179, 151, 255);
    imagelayer[1][0] = new RGBPixel(173, 179, 151, 255);
    imagelayer[1][1] = new RGBPixel(174, 180, 152, 255);
    assertEquals(imagelayer, p2.getLayers().get(0).getPixels());
    p2.setFilter("layer1", new RedComponent());
    RGBPixel[][] redfilter = new RGBPixel[2][2];
    redfilter[0][0] = new RGBPixel(173, 0, 0, 255);
    redfilter[0][1] = new RGBPixel(173, 0, 0, 255);
    redfilter[1][0] = new RGBPixel(173, 0, 0, 255);
    redfilter[1][1] = new RGBPixel(174, 0, 0, 255);
    //p2.saveImage("tako.ppm", "layer1");
    assertEquals(redfilter, p2.getLayers().get(0).getPixels());
  }

  @Test
  public void testsetFilter() {
    RGBPixel[][] newlayer = new RGBPixel[2][2];
    newlayer[0][0] = new RGBPixel(255, 255, 255, 0);
    newlayer[0][1] = new RGBPixel(255, 255, 255, 0);
    newlayer[1][0] = new RGBPixel(255, 255, 255, 0);
    newlayer[1][1] = new RGBPixel(255, 255, 255, 0);
    Layer l1 = new CollageLayer("l1", newlayer);
    RGBPixel[][] background = new RGBPixel[2][2];
    background[0][0] = new RGBPixel(255, 255, 255, 255);
    background[0][1] = new RGBPixel(255, 255, 255, 255);
    background[1][0] = new RGBPixel(255, 255, 255, 255);
    background[1][1] = new RGBPixel(255, 255, 255, 255);
    ArrayList<Layer> projectLayers = new ArrayList<Layer>(Arrays.asList(l1));
    Project p1 = new CollageProject("p1", 2, 2, projectLayers, background);
    p1.setFilter("l1", new RedComponent());
    assertEquals("red-component", l1.getFilterName());
  }

  @Test
  public void testprojectPixels() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("p1", 4, 4);
    RGBPixel[][] background = new RGBPixel[2][2];
    p2.addLayer("l1");
    p2.addImageToLayer("l1", "tako.ppm", 0, 0);
    p2.addLayer("l2");
    p2.addImageToLayer("l2", "landscape.ppm", 2, 2);
    Appendable ap = new StringBuilder();
    ImageView view = new ImageViewImpl(p2, ap);
    new SaveProjectCommand(p2, view, "p2").execute();
    new LoadProjectCommand(p2, view, "p2").execute();
    p2.addLayer("l3");
    assertEquals(background, p2.getProjectPixels());
  }

}
