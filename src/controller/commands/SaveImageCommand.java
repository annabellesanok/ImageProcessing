package controller.commands;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import controller.ICommand;
import model.Project;
import view.ImageView;

/**
 * Saves an image on a certain layer to the user's machine. Creates a file to save the image
 * contents to.
 */
public class SaveImageCommand implements ICommand {
  private final String imageName;
  private final String layerName;
  private final Project model;
  private final ImageView view;

  /**
   * Creates this command object in order to save the image with the given name by the user.
   *
   * @param model     the model running the program.
   * @param layerName scan with user input
   * @param view      this program's view
   */
  public SaveImageCommand(Project model, ImageView view, String imageName, String layerName) {
    this.model = model;
    this.view = view;
    this.imageName = imageName;
    this.layerName = layerName;
  }

  @Override
  public void execute() {
    try {
      // ppm header section
      BufferedWriter writer = new BufferedWriter(new FileWriter(this.imageName));
      writer.append("P3").append(System.lineSeparator());
      writer.append(String.valueOf(this.model.getWidth())).append(' ').append(
              String.valueOf(this.model.getHeight())).append(
              System.lineSeparator());
      writer.append(String.valueOf(255)).append(System.lineSeparator());
      //get image contents
      String layerContents = "";
      for (int i = 0; i < this.model.getLayers().size(); i++) {
        if (this.model.getLayers().get(i).getLayerName().equals(this.layerName)) {
          layerContents = this.model.getLayers().get(i).getLayerContents();
        }
      }
      writer.append(layerContents);
      writer.close();
    } catch (IOException e) {
      throw new IllegalStateException("error saving image");
    }
    try {
      this.view.renderMessage("image saved!");
    } catch (IOException ignored) {
    }
  }
}
