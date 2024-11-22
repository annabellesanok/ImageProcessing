package view;

import java.io.IOException;

import generalcommandcallbacksgui.Features;

/**
 * Represents a view interface with the abilities of rendering messages to a user and adding
 * features to a view by linking it to a given controller.
 */
public interface ImageView {
  /**
   * A way to represent this view as a single String.
   *
   * @return the string for the view
   */
  String toString();

  /**
   * A method to render the given message in order for the user to see it on the view.
   *
   * @param message the message to display
   * @throws IOException if reading or writing the message cannot be executed
   */
  void renderMessage(String message) throws IOException;

  /**
   * Adds a controller to link to this view.
   *
   * @param controller the controller implementing features to add to this view
   */
  void addFeatures(Features controller);
}
