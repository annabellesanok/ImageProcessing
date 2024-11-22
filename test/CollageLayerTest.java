import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import model.CollageLayer;
import model.CollageProject;
import model.Layer;
import model.RGBPixel;
import model.Project;
import model.filtercommands.RedComponent;
import model.filtercommands.DarkenLuma;

import static org.junit.Assert.assertEquals;

/**
 * Tests the layer methods.
 */
public class CollageLayerTest {
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
    l1.setFilter(new RedComponent());
    assertEquals("red-component", l1.getFilterName());
  }

  @Test
  public void testGetFilterName() {
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
    l1.setFilter(new DarkenLuma());
    assertEquals("darken-luma", l1.getFilterName());
  }

  @Test
  public void testgetLayerName() {
    RGBPixel[][] newlayer = new RGBPixel[2][2];
    newlayer[0][0] = new RGBPixel(255, 255, 255, 0);
    newlayer[0][1] = new RGBPixel(255, 255, 255, 0);
    newlayer[1][0] = new RGBPixel(255, 255, 255, 0);
    newlayer[1][1] = new RGBPixel(255, 255, 255, 0);
    Layer l1 = new CollageLayer("l1", newlayer);
    assertEquals("l1", l1.getLayerName());
  }

  @Test
  public void testgetPixels() {
    RGBPixel[][] newlayer = new RGBPixel[2][2];
    newlayer[0][0] = new RGBPixel(255, 255, 255, 0);
    newlayer[0][1] = new RGBPixel(255, 255, 255, 0);
    newlayer[1][0] = new RGBPixel(255, 255, 255, 0);
    newlayer[1][1] = new RGBPixel(255, 255, 255, 0);
    Layer l1 = new CollageLayer("l1", newlayer);
    assertEquals(newlayer, l1.getPixels());
  }

  @Test
  public void testAddImageToLayerppm() {
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
    // p2.saveImage("tako.ppm", "layer1");
    assertEquals(redfilter, p2.getLayers().get(0).getPixels());
  }

  @Test
  public void testAddImageToLayerjpg() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("p2", 2, 2);
    p2.addLayer("layer1");
    p2.addImageToLayer("layer1", "res/mountains.jpg", 0, 0);
    RGBPixel[][] imagelayer = new RGBPixel[2][2];
    imagelayer[0][0] = new RGBPixel(176, 131, 174, 255);
    imagelayer[0][1] = new RGBPixel(172, 129, 174, 255);
    imagelayer[1][0] = new RGBPixel(178, 133, 176, 255);
    imagelayer[1][1] = new RGBPixel(173, 130, 175, 255);
    RGBPixel[][] pixels = (RGBPixel[][]) p2.getLayers().get(0).getPixels();
    //get the first 4 new pixels, which should be set to the image pixels
    RGBPixel pixel00 = pixels[0][0];
    RGBPixel pixel01 = pixels[0][1];
    RGBPixel pixel10 = pixels[1][0];
    RGBPixel pixel11 = pixels[1][1];
    //put them into an array to check against the expected pixel values
    RGBPixel[][] arrayof4 = new RGBPixel[2][2];
    arrayof4[0][0] = pixel00;
    arrayof4[0][1] = pixel01;
    arrayof4[1][0] = pixel10;
    arrayof4[1][1] = pixel11;
    assertEquals(imagelayer, arrayof4);
  }

  @Test
  public void testAddImageToLayerpng() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("p2", 2, 2);
    p2.addLayer("layer1");
    p2.addImageToLayer("layer1", "res/flowers.png", 0, 0);
    RGBPixel[][] imagelayer = new RGBPixel[2][2];
    imagelayer[0][0] = new RGBPixel(192, 173, 175, 255);
    imagelayer[0][1] = new RGBPixel(194, 176, 176, 255);
    imagelayer[1][0] = new RGBPixel(199, 181, 179, 255);
    imagelayer[1][1] = new RGBPixel(201, 183, 179, 255);
    RGBPixel[][] pixels = (RGBPixel[][]) p2.getLayers().get(0).getPixels();
    //get the first 4 new pixels, which should be set to the image pixels
    RGBPixel pixel00 = pixels[0][0];
    RGBPixel pixel01 = pixels[0][1];
    RGBPixel pixel10 = pixels[1][0];
    RGBPixel pixel11 = pixels[1][1];
    //put them into an array to check against the expected pixel values
    RGBPixel[][] arrayof4 = new RGBPixel[2][2];
    arrayof4[0][0] = pixel00;
    arrayof4[0][1] = pixel01;
    arrayof4[1][0] = pixel10;
    arrayof4[1][1] = pixel11;
    assertEquals(imagelayer, arrayof4);

  }


  @Test
  public void testgetLayerContents() {
    RGBPixel[][] newlayer = new RGBPixel[2][2];
    newlayer[0][0] = new RGBPixel(255, 255, 255, 0);
    newlayer[0][1] = new RGBPixel(255, 255, 255, 0);
    newlayer[1][0] = new RGBPixel(255, 255, 255, 0);
    newlayer[1][1] = new RGBPixel(255, 255, 255, 0);
    Layer l1 = new CollageLayer("l1", newlayer);
    assertEquals("255 255 255 0\n255 255 255 0\n255 255 255 0\n255 255 255 0\n",
            l1.getLayerContents());
  }

  @Test
  public void testSetLayer() {
    RGBPixel[][] layer = new RGBPixel[2][2];
    layer[0][0] = new RGBPixel(30, 0, 30, 0);
    layer[0][1] = new RGBPixel(40, 30, 50, 0);
    layer[1][0] = new RGBPixel(200, 100, 200, 0);
    layer[1][1] = new RGBPixel(10, 20, 30, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    p2.getLayers().get(0).setLayer(layer);
    assertEquals(layer, p2.getLayers().get(0).getPixels());
  }

}
