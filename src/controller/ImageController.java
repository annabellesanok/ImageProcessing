package controller;


/**
 * The image processor controller interface. Facilitates proper communication
 * and interactions between the model and view.
 */
public interface ImageController {

  /**
   * Adds filters to an image according to user input.
   *
   * @throws IllegalStateException if the controller is unable to successfully read input
   *                               or transmit output.
   */
  void process() throws IllegalStateException;
}
