package view;

import java.io.IOException;

import generalcommandcallbacksgui.Features;
import model.Project;

/**
 * View implementation for image collage project. This simple view implementation renders
 * messages to the reader as commands are called and executed.
 */
public class ImageViewImpl implements ImageView {
  private final Project model;
  private final Appendable ap;

  /**
   * A constructor with a given appendable that this view will use at its destination.
   *
   * @param model the model class that this game is showing.
   * @param log   the appendable destination.
   * @throws IllegalArgumentException if the given model class or appendable are null.
   */
  public ImageViewImpl(Project model, Appendable log) throws IllegalArgumentException {
    if (model == null || log == null) {
      throw new IllegalArgumentException("Model and/or log can't be null.");
    } else {
      this.model = model;
      this.ap = log;
    }
  }

  /**
   * Displays the current appendable log as a string.
   *
   * @return the string representing the view messages up to now
   */
  public String toString() {
    return this.ap.toString();
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.ap.append(message + "\n");
  }

  @Override
  public void addFeatures(Features controller) {
    // using old controller
  }
}
