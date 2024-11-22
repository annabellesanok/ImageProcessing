package controller.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ICommand;
import model.filtercommands.BlueComponent;
import model.filtercommands.BrightenIntensity;
import model.filtercommands.BrightenLuma;
import model.filtercommands.BrightenValue;
import model.CollageLayer;
import model.filtercommands.DarkenIntensity;
import model.filtercommands.DarkenLuma;
import model.filtercommands.DarkenValue;
import model.filtercommands.FilterCommand;
import model.filtercommands.GreenComponent;
import model.Layer;
import model.filtercommands.Normal;
import model.Project;
import model.RGBPixel;
import model.filtercommands.RedComponent;
import view.ImageView;

/**
 * Loads a project that has previously been saved as a text file in the specified collage format.
 * Implements the ICommand interface.
 */
public class LoadProjectCommand implements ICommand {
  private final Project model;
  private final ImageView view;
  private final String path;

  /**
   * Creates this command object in order to execute loading a project into the program.
   *
   * @param model the model running the program.
   * @param view  this program's view
   * @param path  the filepath of the project to load
   */
  public LoadProjectCommand(Project model, ImageView view, String path) {
    this.model = model;
    this.view = view;
    this.path = path;
  }

  @Override
  public void execute() {
    File file = new File(path);
    try {
      Scanner reader = new Scanner(file);
      // Read project data
      String name = reader.next();
      int width = reader.nextInt();
      int height = reader.nextInt();
      int maxValue = reader.nextInt();
      // Create list of layers
      List<Layer> layers = new ArrayList<>();
      FilterCommand setfilter = new Normal();
      // Read layer data
      while (reader.hasNext()) {
        String layerName = reader.next();
        String filter = reader.next();
        //makes a new class based on the read-in filter
        if (filter.equals("red-component")) {
          setfilter = new RedComponent();
        }
        if (filter.equals("green-component")) {
          setfilter = new GreenComponent();
        }
        if (filter.equals("blue-component")) {
          setfilter = new BlueComponent();
        }
        if (filter.equals("brighten-intensity")) {
          setfilter = new BrightenIntensity();
        }
        if (filter.equals("darken-intensity")) {
          setfilter = new DarkenIntensity();
        }
        if (filter.equals("brighten-value")) {
          setfilter = new BrightenValue();
        }
        if (filter.equals("darken-value")) {
          setfilter = new DarkenValue();
        }
        if (filter.equals("brighten-luma")) {
          setfilter = new BrightenLuma();
        }
        if (filter.equals("darken-luma")) {
          setfilter = new DarkenLuma();
        }
        // Read pixel values
        RGBPixel[][] pixels = new RGBPixel[height][width];
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            int r = reader.nextInt();
            int g = reader.nextInt();
            int b = reader.nextInt();
            RGBPixel pixel = new RGBPixel(r, g, b, 255);
            pixels[i][j] = pixel;
          }
        }
        // Create layer and add to list
        Layer layer = new CollageLayer(layerName, pixels);
        layer.setFilter(setfilter);
        layers.add(layer);
      }

    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File not found");
    }
    try {
      this.view.renderMessage("project loaded!");
      this.model.projectInfo();
    } catch (IOException ignored) {
    }
  }
}
