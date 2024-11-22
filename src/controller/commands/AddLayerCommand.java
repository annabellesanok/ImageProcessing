package controller.commands;

import java.io.IOException;

import controller.ICommand;
import model.Project;
import view.ImageView;

/**
 * Implements the IComnand interface. Command that adds a layer to a project.
 */
public class AddLayerCommand implements ICommand {
  private final Project model;
  private final ImageView view;
  private final String layerName;

  /**
   * Creates this command object in order to execute adding a layer to the project.
   *
   * @param model     the model running the program.
   * @param view      this program's view
   * @param layerName the name of the new layer
   */
  public AddLayerCommand(Project model, ImageView view, String layerName) {
    this.model = model;
    this.view = view;
    this.layerName = layerName;
  }

  @Override
  public void execute() {
    this.model.addLayer(this.layerName);
    try {
      this.view.renderMessage("layer added!");
    } catch (IOException ignored) {
    }
  }
}
