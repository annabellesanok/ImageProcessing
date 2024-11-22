package generalcommandcallbacksgui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.CollageProject;
import model.Project;
import view.ImageView;
import view.ImageViewImpl;

/**
 * The main method to run the program. Can run as a GUI, as a text view, or using a script. Use
 * command line arguments to pick the way to run the program.
 */
public class Main {
  /**
   * A main to run the program based on the given argument, deciding what version to run.
   *
   * @param args command line arguments to pick how to run
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      if (args[0].equals("-text")) {
        Readable input = new InputStreamReader(System.in);
        Project model;
        model = new CollageProject();
        ImageView view = new ImageViewImpl(model, System.out);
        ImageController ctrl = new ImageControllerImpl(model, view, input);
        ctrl.process();
      } else if (args[0].equals("-file")) {
        String path = "";
        if (args.length > 1) {
          path = args[1];
        }
        File script;
        InputStream stream;
        try {
          script = new File(path);
          stream = new FileInputStream(script);
        } catch (IOException e) {
          throw new IllegalStateException("Could not read script file.");
        }
        Readable input = new InputStreamReader(stream);
        Project model;
        model = new CollageProject();
        ImageView view = new ImageViewImpl(model, System.out);
        ImageController ctrl = new ImageControllerImpl(model, view, input);
        ctrl.process();
      } else {
        System.out.println("Invalid arguments. Please use: -file path or -text");
      }
    } else {
      Project model = new CollageProject();
      Controller controller = new Controller(model);
      ImageView view = new JFrameView("Image Processer");//, controller);
      controller.setView(view);
    }
  }
}