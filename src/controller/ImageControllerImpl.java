package controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.commands.AddImageToLayerCommand;
import controller.commands.AddLayerCommand;
import controller.commands.LoadProjectCommand;
import controller.commands.NewProjectCommand;
import controller.commands.SaveImageCommand;
import controller.commands.SaveProjectCommand;
import controller.commands.SetFilterCommand;
import model.Project;
import view.ImageView;

/**
 * Controller for our collage image project purposes that has different messages to be transmitted
 * to the view, and methods to process commands.
 */
public class ImageControllerImpl implements ImageController {
  private final Project model;
  private final ImageView view;
  private final Readable in;

  /**
   * Creates an instance of a controller for image processing with user input.
   *
   * @param model the model to run the program and alter the layers/project
   * @param view  the view to show the user important information
   * @param in    the user input
   */
  public ImageControllerImpl(Project model, ImageView view, Readable in) {
    this.model = model;
    this.view = view;
    this.in = in;
  }

  @Override
  public void process() throws IllegalStateException {
    Scanner sc = new Scanner(in);
    boolean quit = false;

    //print the start message
    this.startMessage();

    while (!quit && sc.hasNext()) { //continue until the user quits
      String userInstruction = sc.next(); //take an instruction name
      if (userInstruction.equals("quit") || userInstruction.equals("q")) {
        quit = true;
      } else {
        this.processCommand(userInstruction, sc);
        writeMessage("Input command: "); //prompt for the instruction name
      }
    }

    //after the user has quit, print farewell message
    this.quitMessage();
  }

  protected void processCommand(String userInstruction, Scanner sc) {
    ICommand command = null;
    switch (userInstruction) {
      case "new-project": //create a new project in the model
        String projectName = sc.next();
        int projHeight = sc.nextInt();
        int projWidth = sc.nextInt();
        command = new NewProjectCommand(this.model, this.view, projectName, projHeight, projWidth);
        break;
      case "load-project": //loads the project from the given path
        String path0 = sc.next();
        command = new LoadProjectCommand(this.model, this.view, path0);
        break;
      case "save-project": //save the project in the model
        String path = sc.next();
        command = new SaveProjectCommand(this.model, this.view, path);
        break;
      case "add-layer": //adds a new layer with the given name to the project
        String layerName0 = sc.next();
        command = new AddLayerCommand(this.model, this.view, layerName0);
        break;
      case "add-image-to-layer": //adds an image w/ the given name to the layer w/given layer name
        String layerName = sc.next();
        String imageName = sc.next();
        int x = sc.nextInt();
        int y = sc.nextInt();
        command = new AddImageToLayerCommand(this.model, this.view, layerName, imageName, x, y);
        break;
      case "set-filter": //sets given filter on layer with given layer name
        String layerName1 = sc.next();
        String filterOp = sc.next();
        command = new SetFilterCommand(this.model, this.view, layerName1, filterOp);
        break;
      case "save-image": //model saves result of applying all filters on the image
        String imageName1 = sc.next();
        String layerName2 = sc.next();
        command = new SaveImageCommand(this.model, this.view, imageName1, layerName2);
        break;
      default: //error due to unrecognized instruction
        this.writeMessage("Undefined command: " + userInstruction + System.lineSeparator());
        return;
    }
    try {
      command.execute();
    } catch (NoSuchElementException e) {
      this.errorMessage(e);
    } catch (IllegalStateException e) {
      this.errorMessage(e);
    }
  }

  protected void writeMessage(String message) throws IllegalStateException {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Transits the error message to the user via the view.
   *
   * @param error the exception thrown by the model
   */
  protected void errorMessage(Exception error) {
    this.writeMessage("Error: " + error.getMessage() + System.lineSeparator());
  }

  /**
   * Transmits the initial welcome message to the user via the view.
   */
  protected void startMessage() {
    writeMessage("Welcome to our image processor program! Input command: "
            + System.lineSeparator());
  }

  /**
   * Transmits the last message to the user via the view after they quit.
   */
  protected void quitMessage() {
    writeMessage("You have quit. bye <3");
  }
}

