package model.filtercommands;

import model.IPixel;
import model.Layer;
import model.RGBPixel;

/**
 * This filter darkens the added layer by increasing each of the RGB components by
 * the highest value.
 */
public class DarkenValue implements FilterCommand {
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
          int newred = pixel.getRed() - max;
          int newgreen = pixel.getGreen() - max;
          int newblue = pixel.getBlue() - max;
          if (newred < 0) {
            newred = 0;
          }
          if (newgreen < 0) {
            newgreen = 0;
          }
          if (newblue < 0) {
            newblue = 0;
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
    return "darken-value";
  }
}
