package controller;

/**
 * Interface representing different types of commands that can be executed on a project.
 */
public interface ICommand {

  /**
   * Executes the command called by the user via the controller.
   */
  void execute();
}
