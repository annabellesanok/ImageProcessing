package controller.commands;

import java.io.FileWriter;
import java.io.IOException;

import controller.ICommand;
import model.Project;
import view.ImageView;

/**
 * Saves a project in the specified collage format in a text file. Implements ICommand.
 */
public class SaveProjectCommand implements ICommand {
  private final Project model;
  private final ImageView view;
  private final String path;

  /**
   * Creates this command object in order to execute saving the project.
   *
   * @param model the model running the program.
   * @param view  this program's view
   * @param path  the path to save this file to
   */
  public SaveProjectCommand(Project model, ImageView view, String path) {
    this.model = model;
    this.view = view;
    this.path = path;
  }

  @Override
  public void execute() {
    try {
      FileWriter writer = new FileWriter(path); // name comes from user input
      String saveInfo = this.model.projectInfo();
      writer.write(saveInfo);
      writer.close();
      try {
        this.view.renderMessage("project saved!");
      } catch (IOException ignored) {
      }
    } catch (IOException e) {
      try {
        this.view.renderMessage("Could not save file properly.");
      } catch (IOException ignored) {
        // caught exception
      }
    }
  }
}
