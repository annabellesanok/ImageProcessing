import java.io.IOException;

import javax.swing.JFrame;

import generalcommandcallbacksgui.Features;
import view.ImageView;

/**
 * A mock of the view that checks that the controller sends the correct messages.
 */
public class GUIMockCorrectInput extends JFrame implements ImageView {
  private Features ctrl;

  /**
   * method to test that methods called from the GUI to the view send the correct inputs.
   */
  public void actionProcess(String instr) {
    switch (instr) {
      case "new project":
        ctrl.makeNewProject("mock project name", 19, 91);
        break;
      case "add layer":
        ctrl.addLayer("mock add layer");
        break;
      case "exit":
        this.dispose();
        break;
      case "load project":
        this.ctrl.loadProject("mock path load");
        break;
      case "save project":
        this.ctrl.saveProject("mock path save");
        break;
      case "add image to layer":
        this.ctrl.addImageToLayer("mock layer", "mock image", 233, 39);
        break;
      case "save image":
        this.ctrl.saveImage("mock imagepath", "mock layer");
        break;
      case "normal":
        ctrl.setFilter(this.getLayer(), "normal");
        break;
      case "red component":
        ctrl.setFilter(this.getLayer(), "red-component");
        break;
      case "green component":
        ctrl.setFilter(this.getLayer(), "green-component");
        break;
      case "blue component":
        ctrl.setFilter(this.getLayer(), "blue-component");
        break;
      case "brighten value":
        ctrl.setFilter(this.getLayer(), "brighten-value");
        break;
      case "darken value":
        ctrl.setFilter(this.getLayer(), "darken-value");
        break;
      case "brighten intensity":
        ctrl.setFilter(this.getLayer(), "brighten-intensity");
        break;
      case "darken intensity":
        ctrl.setFilter(this.getLayer(), "darken-intensity");
        break;
      case "brighten luma":
        ctrl.setFilter(this.getLayer(), "brighten-luma");
        break;
      case "darken luma":
        ctrl.setFilter(this.getLayer(), "darken-luma");
        break;
      case "difference":
        ctrl.setFilter(this.getLayer(), "difference");
        break;
      case "multiply":
        ctrl.setFilter(this.getLayer(), "multiply");
        break;
      case "screen":
        ctrl.setFilter(this.getLayer(), "screen");
        break;
      default:
        // nothing!
    }
  }

  private String getLayer() {
    return "mock layer";
  }

  @Override
  public void renderMessage(String message) throws IOException {
    // do nothing for mock
  }

  @Override
  public void addFeatures(Features controller) {
    this.ctrl = controller;
  }
}
