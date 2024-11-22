package controller.commands;

import java.io.IOException;

import controller.ICommand;
import model.Project;
import view.ImageView;

/**
 * Implements the ICommand interface. Command that adds an image to a layer in a project.
 */
public class AddImageToLayerCommand implements ICommand {
  private final Project model;
  private final ImageView view;
  private final String layerName;
  private final String imageName;
  private final int xPos;
  private final int yPos;

  /**
   * Creates this command object in order to execute adding an image to the layer with the
   * name given by the user.
   *
   * @param model     the model running the program.
   * @param view      this program's view
   * @param layerName the name of the layer to add the image to
   * @param imageName the filepath of the image
   */
  public AddImageToLayerCommand(Project model, ImageView view, String layerName, String imageName,
                                int xPos, int yPos) {
    this.model = model;
    this.view = view;
    this.layerName = layerName;
    this.imageName = imageName;
    this.xPos = xPos;
    this.yPos = yPos;
  }

  @Override
  public void execute() {
    this.model.addImageToLayer(this.layerName, this.imageName, this.xPos, this.yPos);
    try {
      this.view.renderMessage("image added!");
    } catch (IOException ignored) {
    }
  }
}
