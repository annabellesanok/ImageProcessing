import org.junit.Test;

import controller.commands.SaveImageCommand;
import model.filtercommands.DifferenceFilter;
import model.RGBPixel;
import model.filtercommands.MultiplyFilter;
import model.filtercommands.BlueComponent;
import model.filtercommands.BrightenIntensity;
import model.filtercommands.BrightenLuma;
import model.filtercommands.BrightenValue;
import model.CollageProject;
import model.filtercommands.DarkenIntensity;
import model.filtercommands.DarkenLuma;
import model.filtercommands.DarkenValue;
import model.filtercommands.GreenComponent;
import model.Layer;
import model.Project;
import model.filtercommands.RedComponent;
import model.filtercommands.ScreenFilter;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests the correct behavior of the filters.
 */

public class FilterTests {

  @Test
  public void testRedComponentFilter() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 3, 3);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new RedComponent());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals("red-component", layer1.getFilterName());
  }

  @Test
  public void testGreenComponentFilter() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 3, 3);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new GreenComponent());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals("green-component", layer1.getFilterName());
  }

  @Test
  public void testBlueComponentFilter() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 3, 3);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new BlueComponent());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals("blue-component", layer1.getFilterName());
  }

  @Test
  public void testBrightenIntensity() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 3, 3);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new BrightenIntensity());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals("brighten-intensity", layer1.getFilterName());
  }

  @Test
  public void testDarkenIntensityFilter() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 3, 3);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new DarkenIntensity());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals("darken-intensity", layer1.getFilterName());
  }

  @Test
  public void testBrightenValue() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 3, 3);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new BrightenValue());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals("brighten-value", layer1.getFilterName());
  }

  @Test
  public void testDarkenValue() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 3, 3);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new DarkenValue());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals("darken-value", layer1.getFilterName());
  }

  @Test
  public void testBrightenLumaFilter() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 3, 3);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new BrightenLuma());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals("brighten-luma", layer1.getFilterName());
  }

  @Test
  public void testDarkenLumaFilter() {
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 3, 3);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new DarkenLuma());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals("darken-luma", layer1.getFilterName());
  }

  @Test
  public void testBlueComponentChangesPixels() {
    RGBPixel[][] bluelayer = new RGBPixel[2][2];
    bluelayer[0][0] = new RGBPixel(0, 0, 255, 0);
    bluelayer[0][1] = new RGBPixel(0, 0, 255, 0);
    bluelayer[1][0] = new RGBPixel(0, 0, 255, 0);
    bluelayer[1][1] = new RGBPixel(0, 0, 255, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new BlueComponent());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals(bluelayer, layer1.getPixels());
  }

  @Test
  public void testRedComponentChangesPixels() {
    RGBPixel[][] redlayer = new RGBPixel[2][2];
    redlayer[0][0] = new RGBPixel(255, 0, 0, 0);
    redlayer[0][1] = new RGBPixel(255, 0, 0, 0);
    redlayer[1][0] = new RGBPixel(255, 0, 0, 0);
    redlayer[1][1] = new RGBPixel(255, 0, 0, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new RedComponent());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals(redlayer, layer1.getPixels());
  }

  @Test
  public void testGreenComponentChangesPixels() {
    RGBPixel[][] greenlayer = new RGBPixel[2][2];
    greenlayer[0][0] = new RGBPixel(0, 255, 0, 0);
    greenlayer[0][1] = new RGBPixel(0, 255, 0, 0);
    greenlayer[1][0] = new RGBPixel(0, 255, 0, 0);
    greenlayer[1][1] = new RGBPixel(0, 255, 0, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new GreenComponent());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals(greenlayer, layer1.getPixels());
  }

  @Test
  public void testDarkenLumaChangesPixels() {
    RGBPixel[][] darken = new RGBPixel[2][2];
    darken[0][0] = new RGBPixel(1, 1, 1, 0);
    darken[0][1] = new RGBPixel(1, 1, 1, 0);
    darken[1][0] = new RGBPixel(1, 1, 1, 0);
    darken[1][1] = new RGBPixel(1, 1, 1, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new DarkenLuma());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals(darken, layer1.getPixels());
  }

  @Test
  public void testDarkenIntensityChangesPixels() {
    RGBPixel[][] darken = new RGBPixel[2][2];
    darken[0][0] = new RGBPixel(0, 0, 0, 0);
    darken[0][1] = new RGBPixel(0, 0, 0, 0);
    darken[1][0] = new RGBPixel(0, 0, 0, 0);
    darken[1][1] = new RGBPixel(0, 0, 0, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new DarkenIntensity());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals(darken, layer1.getPixels());
  }

  @Test
  public void testDarkenValueChangesPixels() {
    RGBPixel[][] darken = new RGBPixel[2][2];
    darken[0][0] = new RGBPixel(0, 0, 0, 0);
    darken[0][1] = new RGBPixel(0, 0, 0, 0);
    darken[1][0] = new RGBPixel(0, 0, 0, 0);
    darken[1][1] = new RGBPixel(0, 0, 0, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    p2.setFilter("layer1", new DarkenValue());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals(darken, layer1.getPixels());
  }

  @Test
  public void testBrightenValueChangesPixels() {
    RGBPixel[][] oglayer = new RGBPixel[2][2];
    oglayer[0][0] = new RGBPixel(30, 0, 30, 0);
    oglayer[0][1] = new RGBPixel(40, 30, 50, 0);
    oglayer[1][0] = new RGBPixel(70, 60, 190, 0);
    oglayer[1][1] = new RGBPixel(10, 20, 30, 0);
    RGBPixel[][] brighten = new RGBPixel[2][2];
    brighten[0][0] = new RGBPixel(60, 30, 60, 0);
    brighten[0][1] = new RGBPixel(90, 80, 100, 0);
    brighten[1][0] = new RGBPixel(255, 250, 255, 0);
    brighten[1][1] = new RGBPixel(40, 50, 60, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    p2.getLayers().get(0).setLayer(oglayer);
    p2.setFilter("layer1", new BrightenValue());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals(brighten, layer1.getPixels());
  }

  @Test
  public void testBrightenLumaChangesPixels() {
    RGBPixel[][] oglayer = new RGBPixel[2][2];
    oglayer[0][0] = new RGBPixel(30, 0, 30, 0);
    oglayer[0][1] = new RGBPixel(40, 30, 50, 0);
    oglayer[1][0] = new RGBPixel(70, 60, 190, 0);
    oglayer[1][1] = new RGBPixel(10, 20, 30, 0);
    RGBPixel[][] brighten = new RGBPixel[2][2];
    brighten[0][0] = new RGBPixel(38, 8, 38, 0);
    brighten[0][1] = new RGBPixel(72, 62, 82, 0);
    brighten[1][0] = new RGBPixel(139, 129, 255, 0);
    brighten[1][1] = new RGBPixel(28, 38, 48, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    p2.getLayers().get(0).setLayer(oglayer);
    p2.setFilter("layer1", new BrightenLuma());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals(brighten, layer1.getPixels());
  }

  @Test
  public void test2filters() {
    RGBPixel[][] oglayer = new RGBPixel[2][2];
    oglayer[0][0] = new RGBPixel(30, 0, 30, 0);
    oglayer[0][1] = new RGBPixel(40, 30, 50, 0);
    oglayer[1][0] = new RGBPixel(70, 60, 190, 0);
    oglayer[1][1] = new RGBPixel(10, 20, 30, 0);
    RGBPixel[][] brighten = new RGBPixel[2][2];
    brighten[0][0] = new RGBPixel(38, 8, 38, 0);
    brighten[0][1] = new RGBPixel(72, 62, 82, 0);
    brighten[1][0] = new RGBPixel(139, 129, 255, 0);
    brighten[1][1] = new RGBPixel(28, 38, 48, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    p2.getLayers().get(0).setLayer(oglayer);
    p2.setFilter("layer1", new BrightenLuma());
    Layer layer1 = p2.getLayers().get(0);
    assertEquals(brighten, layer1.getPixels());
  }

  @Test
  public void testDifferenceFilterChangesPixels() {
    RGBPixel[][] background = new RGBPixel[2][2];
    background[0][0] = new RGBPixel(100, 40, 100, 0);
    background[0][1] = new RGBPixel(100, 40, 100, 0);
    background[1][0] = new RGBPixel(100, 40, 100, 0);
    background[1][1] = new RGBPixel(100, 40, 100, 0);
    RGBPixel[][] darken = new RGBPixel[2][2];
    darken[0][0] = new RGBPixel(15, 40, 20, 0);
    darken[0][1] = new RGBPixel(60, 20, 100, 0);
    darken[1][0] = new RGBPixel(100, 200, 100, 0);
    darken[1][1] = new RGBPixel(40, 70, 80, 0);
    RGBPixel[][] difference = new RGBPixel[2][2];
    difference[0][0] = new RGBPixel(85, 0, 80, 255);
    difference[0][1] = new RGBPixel(40, 20, 0, 255);
    difference[1][0] = new RGBPixel(0, 160, 0, 255);
    difference[1][1] = new RGBPixel(60, 30, 20, 255);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    Layer layer1 = p2.getLayers().get(0);
    layer1.setLayer(background);
    p2.addLayer("layer2");
    Layer layer2 = p2.getLayers().get(1);
    layer2.setLayer(darken);
    p2.setFilter("layer2", new DifferenceFilter(p2));
    new SaveImageCommand(p2, new ImageViewImpl(p2, System.out),
            "difference.ppm", "layer2");
    assertEquals(difference, layer2.getPixels());
  }

  @Test
  public void testMultiplyFilterChangesPixels() {
    RGBPixel[][] background = new RGBPixel[2][2];
    background[0][0] = new RGBPixel(255, 0, 255, 0);
    background[0][1] = new RGBPixel(0, 0, 0, 0);
    background[1][0] = new RGBPixel(0, 0, 0, 0);
    background[1][1] = new RGBPixel(255, 255, 255, 0);
    RGBPixel[][] multiply = new RGBPixel[2][2];
    multiply[0][0] = new RGBPixel(15, 40, 20, 0);
    multiply[0][1] = new RGBPixel(60, 20, 100, 0);
    multiply[1][0] = new RGBPixel(100, 200, 100, 0);
    multiply[1][1] = new RGBPixel(40, 70, 80, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    Layer layer1 = p2.getLayers().get(0);
    layer1.setLayer(background);
    p2.addLayer("layer2");
    Layer layer2 = p2.getLayers().get(1);
    p2.setFilter("layer2", new MultiplyFilter(p2));
    new SaveImageCommand(p2, new ImageViewImpl(p2, System.out),
            "difference.ppm", "layer2");
    //  p2.saveImage("difference.ppm", "layer2" );
    assertEquals(multiply, layer2.getPixels());
  }

  @Test
  public void testScreenFilterChangesPixels() {
    RGBPixel[][] background = new RGBPixel[2][2];
    background[0][0] = new RGBPixel(100, 40, 100, 0);
    background[0][1] = new RGBPixel(100, 40, 100, 0);
    background[1][0] = new RGBPixel(100, 40, 100, 0);
    background[1][1] = new RGBPixel(100, 40, 100, 0);
    RGBPixel[][] darken = new RGBPixel[2][2];
    darken[0][0] = new RGBPixel(15, 40, 20, 0);
    darken[0][1] = new RGBPixel(60, 20, 100, 0);
    darken[1][0] = new RGBPixel(100, 200, 100, 0);
    darken[1][1] = new RGBPixel(40, 70, 80, 0);
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 2, 2);
    p2.addLayer("layer1");
    Layer layer1 = p2.getLayers().get(0);
    layer1.setLayer(background);
    p2.addLayer("layer2");
    Layer layer2 = p2.getLayers().get(1);
    layer2.setLayer(darken);
    p2.setFilter("layer2", new DifferenceFilter(p2));
    new SaveImageCommand(p2, new ImageViewImpl(p2, System.out),
            "difference.ppm", "layer2");
    //  p2.saveImage("difference.ppm", "layer2" );
    assertEquals(darken, layer2.getPixels());
  }

  @Test
  public void testDifferenceFilterChangesImage() {
    RGBPixel[][] background = new RGBPixel[2][2];
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 92, 200);
    p2.addLayer("layer1");
    p2.addImageToLayer("layer1", "res/tako.ppm", 0, 0);
    p2.setFilter("layer1", new GreenComponent());
    p2.addLayer("layer2");
    p2.addImageToLayer("layer2", "res/tako.ppm", 0, 80);
    p2.setFilter("layer2", new DifferenceFilter(p2));
    new SaveImageCommand(p2, new ImageViewImpl(p2, System.out),
            "differencetako.ppm", "layer2");
    assertEquals(background, p2.getBackground());
    //p2.saveImage("differencetako.ppm", "layer2" );
  }

  @Test
  public void testMultiplyFilterSave() {
    int width = 100;
    int height = 133;
    int stripeSize = height / 3;
    RGBPixel[][] background = new RGBPixel[width][height];
    for (int y = 0; y < height; y++) {
      int stripe = y / stripeSize;
      for (int x = 0; x < width; x++) {
        RGBPixel pixel;
        if (stripe == 0) {
          pixel = new RGBPixel(255, 255, 255, 255); // white
        } else if (stripe == 1) {
          pixel = new RGBPixel(0, 0, 0, 255); // black
        } else {
          pixel = new RGBPixel(255, 0, 255, 255); // purple
        }
        background[x][y] = pixel;
      }
    }
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 100, 133);
    p2.addLayer("layer1");
    p2.getLayers().get(0).setLayer(background);
    p2.addLayer("layer2");
    p2.addImageToLayer("layer2", "res/tako.ppm", 0, 0);
    p2.setFilter("layer2", new MultiplyFilter(p2));
    new SaveImageCommand(p2, new ImageViewImpl(p2, System.out),
            "multiply2.ppm", "layer2");
    assertEquals(background, p2.getProjectPixels());
    //p2.saveImage("multiply2.ppm", "layer2" );
  }


  @Test
  public void testScreenFilterSave() {
    int width = 100;
    int height = 133;
    int stripeSize = height / 3;
    RGBPixel[][] background = new RGBPixel[width][height];
    for (int y = 0; y < height; y++) {
      int stripe = y / stripeSize;
      for (int x = 0; x < width; x++) {
        RGBPixel pixel;
        if (stripe == 0) {
          pixel = new RGBPixel(255, 255, 255, 255); // white
        } else if (stripe == 1) {
          pixel = new RGBPixel(0, 0, 0, 255); // black
        } else {
          pixel = new RGBPixel(255, 0, 255, 255); // purple
        }
        background[x][y] = pixel;
      }
    }
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("project 1", 100, 133);
    p2.addLayer("layer1");
    p2.getLayers().get(0).setLayer(background);
    p2.addLayer("layer2");
    p2.addImageToLayer("layer2", "res/tako.ppm", 0, 0);
    p2.setFilter("layer2", new ScreenFilter(p2));
    new SaveImageCommand(p2, new ImageViewImpl(p2, System.out),
            "screen2.ppm", "layer2");
    assertEquals(background, p2.getProjectPixels());
    //p2.saveImage("screen2.ppm", "layer2" );
  }

  //this is just what i ran to new photos with the filters on them
  @Test
  public void testred() {
    RGBPixel[][] blackarea = new RGBPixel[30][30];
    Project p1 = new CollageProject();
    Project p2 = p1.newProject("p2", 100, 140);
    p2.addLayer("layer1");
    p2.addImageToLayer("layer1", "res/tako.ppm", 0, 0);
    p2.setFilter("layer1", new BlueComponent());
    p2.setFilter("layer1", new DarkenValue());
    new SaveImageCommand(p2, new ImageViewImpl(p2, System.out),
            "tako11.ppm", "layer1");
    assertEquals(blackarea, p2.getProjectPixels());
  }


}