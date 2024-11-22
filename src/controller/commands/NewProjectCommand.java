package controller.commands;

import java.io.IOException;

import controller.ICommand;
import model.Project;
import view.ImageView;

/**
 * Creates a new collage project in the model. Implements the ICommand interface.
 */
public class NewProjectCommand implements ICommand {
  private final Project model;
  private final ImageView view;
  private final String projName;
  private final int projHeight;
  private final int projWidth;


  /**
   * Creates this command object in order to execute creating a new project.
   *
   * @param model  the model running the program.
   * @param view   this program's view
   * @param name   the name of the new project
   * @param height the height of the new project in pixels
   * @param width  the width of the new project in pixels
   */
  public NewProjectCommand(Project model, ImageView view, String name, int height, int width) {
    this.model = model;
    this.view = view;
    this.projName = name;
    this.projHeight = height;
    this.projWidth = width;
  }

  @Override
  public void execute() {
    this.model.newProject(this.projName, this.projHeight, this.projWidth);
    try {
      this.view.renderMessage("project created!");
    } catch (IOException ignored) {
    }
  }
}
