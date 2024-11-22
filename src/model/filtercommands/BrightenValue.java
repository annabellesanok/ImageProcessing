package model.filtercommands;

import model.IPixel;
import model.Layer;
import model.RGBPixel;

/**
 * This filter brightens the added layer by increasing each of the RGB components
 * by the highest value.
 */
public class BrightenValue implements FilterCommand {
  @Override
  public void execute(Layer l) {
    int height = l.getPixels()[0].length;
    int width = l.getPixels().length;
    l.setLayer(l.getOgPixelsCopy());
    IPixel[][] layer = l.getPixels();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        IPixel pixel = layer[x][y]; //gets the pixel at x, y
        int alp = pixel.getAlpha();
        if (alp > 0) {
          int max = Math.max(pixel.getRed(), Math.max(pixel.getGreen(), pixel.getBlue()));
          int newred = pixel.getRed() + max;
          int newgreen = pixel.getGreen() + max;
          int newblue = pixel.getBlue() + max;
          if (newred > 255) {
            newred = 255;
          }
          if (newgreen > 255) {
            newgreen = 255;
          }
          if (newblue > 255) {
            newblue = 255;
          }
          pixel = new RGBPixel(newred, newgreen, newblue, pixel.getAlpha());
          layer[x][y] = pixel;
        }
      }
    }
    l.setLayer(layer);
  }

  /**
   * A string representing the name of this filter command.
   */
  public String toString() {
    return "brighten-value";
  }
}
