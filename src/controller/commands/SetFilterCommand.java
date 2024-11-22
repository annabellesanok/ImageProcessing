package controller.commands;

import java.io.IOException;

import controller.ICommand;
import model.filtercommands.BlueComponent;
import model.filtercommands.BrightenIntensity;
import model.filtercommands.BrightenLuma;
import model.filtercommands.BrightenValue;
import model.filtercommands.DarkenIntensity;
import model.filtercommands.DarkenLuma;
import model.filtercommands.DarkenValue;
import model.filtercommands.DifferenceFilter;
import model.filtercommands.FilterCommand;
import model.filtercommands.GreenComponent;
import model.filtercommands.MultiplyFilter;
import model.filtercommands.Normal;
import model.Project;
import model.filtercommands.RedComponent;
import model.filtercommands.ScreenFilter;
import view.ImageView;

/**
 * Sets a layer to a filter. Changes the 2D array of pixels that is the shown image for this layer.
 */
public class SetFilterCommand implements ICommand {
  private final Project model;
  private final ImageView view;
  private final String layerName;
  private final String filterOpt;

  /**
   * Creates this command object in order to execute putting a filter on the layer with the
   * given name.
   *
   * @param model     the model running the program.
   * @param view      this program's view
   * @param layerName the layer name of the layer to put the filter on
   * @param filterOpt the type of filter to add
   */
  public SetFilterCommand(Project model, ImageView view, String layerName, String filterOpt) {
    this.model = model;
    this.view = view;
    this.layerName = layerName;
    this.filterOpt = filterOpt;
  }

  @Override
  public void execute() {
    FilterCommand filter = new Normal();
    switch (this.filterOpt) {
      case "normal":
        filter = new Normal();
        break;
      case "red-component":
        filter = new RedComponent();
        break;
      case "green-component":
        filter = new GreenComponent();
        break;
      case "blue-component":
        filter = new BlueComponent();
        break;
      case "brighten-value":
        filter = new BrightenValue();
        break;
      case "brighten-intensity":
        filter = new BrightenIntensity();
        break;
      case "brighten-luma":
        filter = new BrightenLuma();
        break;
      case "darken-value":
        filter = new DarkenValue();
        break;
      case "darken-intensity":
        filter = new DarkenIntensity();
        break;
      case "darken-luma":
        filter = new DarkenLuma();
        break;
      case "difference":
        filter = new DifferenceFilter(model);
        break;
      case "multiply":
        filter = new MultiplyFilter(model);
        break;
      case "screen":
        filter = new ScreenFilter(model);
        break;
      default:
        filter = new Normal();
    }
    this.model.setFilter(this.layerName, filter);
    try {
      this.view.renderMessage("filter set!");
    } catch (IOException ignored) {
    }
  }
}
