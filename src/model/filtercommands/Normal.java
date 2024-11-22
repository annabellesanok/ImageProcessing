package model.filtercommands;

import model.IPixel;
import model.Layer;

/**
 * Normal filter that does nothing to the image.
 */
public class Normal implements FilterCommand {
  @Override
  public void execute(Layer l) {
    l.setLayer(l.getOgPixelsCopy());
    IPixel[][] pixels = l.getPixels();
    return;
  }

  /**
   * A string representing the name of this filter command.
   */
  public String toString() {
    return "normal";
  }
}
