import java.io.InputStreamReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.CollageProject;
import model.Project;
import view.ImageView;
import view.ImageViewImpl;

/**
 * Main method.
 */
public class Main {
  /**
   * A main method to run to play the game.
   *
   * @param args the user input as an array of Strings
   */
  public static void main(String[] args) {
    Readable input = new InputStreamReader(System.in);
    Project model;
    model = new CollageProject();
    ImageView view = new ImageViewImpl(model, System.out);
    ImageController ctrl = new ImageControllerImpl(model, view, input);
    ctrl.process();
  }
}
