import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.CollageProject;
import model.filtercommands.FilterCommand;
import model.Layer;
import model.RGBPixel;
import model.Project;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to test the controller with mocks. Checks how the view reacts to IOExceptions
 * from the controller and that the model receives the correct inputs from ctrl.
 */
public class ControllerTests {
  Project model = new CollageProject();

  class AppendableAlwaysThrowsExceptionMock implements Appendable {

    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException();
    }
  }


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
    Readable in = new StringReader("new-project p1 20 20 add-layer layer1");
    StringBuilder ap1 = new StringBuilder();
    Project mock = new ConfirmInputsMock(ap1);
    ImageView view = new ImageViewImpl(mock, ap1);
    ImageController controller = new ImageControllerImpl(mock, view, in);
    controller.process();
    assertEquals("layername: layer1", ap1.toString());
  }

  @Test
  public void testCorrectInputsNewProject() {
    Readable in = new StringReader("new-project p1 20 20");
    StringBuilder ap1 = new StringBuilder();
    Project mock = new ConfirmInputsMock(ap1);
    ImageView view = new ImageViewImpl(mock, ap1);
    ImageController controller = new ImageControllerImpl(mock, view, in);
    controller.process();
    assertEquals("name: p1width: 20height: 20", ap1.toString());
  }

  @Test
  public void testCorrectInputsAddImageToLayer() {
    Readable in = new StringReader("new-project p1 20 20 add-layer layer1 0 0" +
            "add-image-to-layer tako.ppm");
    StringBuilder ap1 = new StringBuilder();
    Project mock = new ConfirmInputsMock(ap1);
    ImageView view = new ImageViewImpl(mock, ap1);
    ImageController controller = new ImageControllerImpl(mock, view, in);
    controller.process();
    assertEquals("layername: layer1 imagePath: tako.ppm x: 0 y: 0", ap1.toString());
  }

  @Test
  public void testCorrectInputsSetFilter() {
    Readable in = new StringReader("new-project p1 20 20 add-layer layer1 0 0" +
            "add-image-to-layer tako.ppm set-filter red-component");
    StringBuilder ap1 = new StringBuilder();
    Project mock = new ConfirmInputsMock(ap1);
    ImageView view = new ImageViewImpl(mock, ap1);
    ImageController controller = new ImageControllerImpl(mock, view, in);
    controller.process();
    assertEquals("layername: layer 1filtercommand red-component", ap1.toString());
  }


  @Test
  public void testReactionToIOExceptionRenderGrid() {
    Readable in = new StringReader("3 3 q");
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(model, new AppendableAlwaysThrowsExceptionMock());
    ImageController controller =
            new ImageControllerImpl(model, view, in);
    try {
      view.renderMessage("hello");
      fail("Expected IOException");
    } catch (IOException ignore) {
      //do nothing b/c you passed
    }
  }

  @Test
  public void testNewProjectCommand() {
    Readable in = new StringReader("new-project 50 50");
    Appendable out = new StringBuilder();
    Project p1 = new CollageProject();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(p1, view, in);
    controller.process();
    assertEquals("Welcome to our image processor program!\nproject created!"
            , out.toString());
  }

  @Test
  public void testAddImageToLayerCommand() {
    Readable in = new StringReader("new-project 50 50 add-layer layer1 add-image-to-layer " +
            "tako.ppm 0 0");
    Appendable out = new StringBuilder();
    Project p1 = new CollageProject();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(p1, view, in);
    controller.process();
    assertEquals("Welcome to our image processor program!\nlayer added!\nimage added!"
            , out.toString());
  }

  @Test
  public void testAddLayerCommand() {
    Readable in = new StringReader("new-project 50 50 add-layer layer1");
    Appendable out = new StringBuilder();
    Project p1 = new CollageProject();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(p1, view, in);
    controller.process();
    assertEquals("Welcome to our image processor program!\nlayer added!"
            , out.toString());
  }

  @Test
  public void testLoadProjectCommand() {
    Readable in = new StringReader("load-project");
    Appendable out = new StringBuilder();
    Project p1 = new CollageProject();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(p1, view, in);
    controller.process();
    assertEquals("Welcome to our image processor program!\nproject loaded!"
            , out.toString());
  }

  @Test
  public void testSaveImageCommand() {
    Readable in = new StringReader("new-project project1 100 140\n" +
            "add-layer layer1\n" +
            "add-image-to-layer layer1 tako.ppm 0 0 \n" +
            "set-filter layer1 red-component\n" +
            "save-image tako11 layer1");
    Appendable out = new StringBuilder();
    Project p1 = new CollageProject();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(p1, view, in);
    controller.process();
    assertEquals("Welcome to our image processor program!\nimage saved!"
            , out.toString());
  }

  @Test
  public void testSaveProjectCommand() {
    Readable in = new StringReader("save-project");
    Appendable out = new StringBuilder();
    Project p1 = new CollageProject();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(p1, view, in);
    controller.process();
    assertEquals("Welcome to our image processor program!\nproject saved!"
            , out.toString());
  }

  @Test
  public void testSetFilterCommand() {
    Readable in = new StringReader("new-project 50 50 add-layer layer1 add-image-to-layer " +
            "tako.ppm 0 0 set-filter red-component");
    Appendable out = new StringBuilder();
    Project p1 = new CollageProject();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(p1, view, in);
    controller.process();
    assertEquals("Welcome to our image processor program!\nlayer added!\nimage added!" +
                    "\nfilter set!"
            , out.toString());
  }

  @Test
  public void testundefinedCommand() {
    Readable in = new StringReader("new-projects");
    Appendable out = new StringBuilder();
    Project p1 = new CollageProject();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(p1, view, in);
    controller.process();
    assertEquals("Welcome to our image processor program!\n" +
                    "Undefined command: new-projects"
            , out.toString());
  }

  @Test
  public void testQuit() {
    Readable in = new StringReader("quit");
    Appendable out = new StringBuilder();
    Project p1 = new CollageProject();
    ImageView view = new ImageViewImpl(model, out);
    ImageController controller = new ImageControllerImpl(p1, view, in);
    controller.process();
    assertEquals("Welcome to our image processor program!\n" +
                    "Input command: You have quit. bye <3"
            , out.toString());
  }
}
