import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A class to test that the graphical user interface passes along the correct
 * inputs to the controller when actions occur. Each test method's name shows which controller
 * method called from the view is being test for passing the right inputs.
 */
public class GUITests {
  GUIMockCorrectInput gui = new GUIMockCorrectInput();

  @Test
  public void testCaseAddLayer() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("add layer");
    assertEquals("add layer: mock add layer", log.toString());
  }

  @Test
  public void testCaseNewProj() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("new project");
    assertEquals("new project with name: mock project name width: 19 height: 91",
            log.toString());
  }

  @Test
  public void testCaseLoad() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("load project");
    assertEquals("load project: mock path load", log.toString());
  }

  @Test
  public void testCaseSaveProject() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("save project");
    assertEquals("save project as: mock path save", log.toString());
  }

  @Test
  public void testCaseAddImageToLayer() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("add image to layer");
    assertEquals("add image to layer. layer: mock layer image to add: mock " +
            "image at 233, 39", log.toString());
  }

  @Test
  public void testCaseSaveImage() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("save image");
    assertEquals("save image as mock imagepath on mock layer", log.toString());
  }

  @Test
  public void testCaseNormal() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("normal");
    assertEquals("setting filter: normal on mock layer", log.toString());
  }

  @Test
  public void testCaseRed() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("red component");
    assertEquals("setting filter: red-component on mock layer", log.toString());
  }

  @Test
  public void testCaseGreen() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("green component");
    assertEquals("setting filter: green-component on mock layer", log.toString());
  }

  @Test
  public void testCaseBlue() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("blue component");
    assertEquals("setting filter: blue-component on mock layer", log.toString());
  }

  @Test
  public void testCaseBrightenVal() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("brighten value");
    assertEquals("setting filter: brighten-value on mock layer", log.toString());
  }

  @Test
  public void testCaseDarkenVal() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("darken value");
    assertEquals("setting filter: darken-value on mock layer", log.toString());
  }

  @Test
  public void testCaseBrightenIn() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("brighten intensity");
    assertEquals("setting filter: brighten-intensity on mock layer", log.toString());
  }

  @Test
  public void testCaseDarkenIn() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("darken intensity");
    assertEquals("setting filter: darken-intensity on mock layer", log.toString());
  }

  @Test
  public void testCaseBrightenLum() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("brighten luma");
    assertEquals("setting filter: brighten-luma on mock layer", log.toString());
  }

  @Test
  public void testCaseDarkenLum() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("darken luma");
    assertEquals("setting filter: darken-luma on mock layer", log.toString());
  }

  @Test
  public void testCaseDifference() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("difference");
    assertEquals("setting filter: difference on mock layer", log.toString());
  }

  @Test
  public void testCaseMult() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("multiply");
    assertEquals("setting filter: multiply on mock layer", log.toString());
  }

  @Test
  public void testCaseScreen() {
    StringBuilder log = new StringBuilder();
    gui.addFeatures(new GUIViewCorrectInputToCtrlMock(log));
    gui.actionProcess("screen");
    assertEquals("setting filter: screen on mock layer", log.toString());
  }

}
