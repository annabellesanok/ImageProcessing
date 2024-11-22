package model.filtercommands;

import model.IPixel;
import model.Layer;
import model.RGBPixel;

/**
 * This filter darkens the added layer by increasing each of the RGB components by the luma.
 */
public class DarkenLuma implements FilterCommand {
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
          int luma = (int) (pixel.getRed() * .2126) + (int) (pixel.getGreen() * .7152) +
                  (int) (pixel.getBlue() * .0722);
          int newred = pixel.getRed() - luma;
          int newgreen = pixel.getGreen() - luma;
          int newblue = pixel.getBlue() - luma;
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
    return "darken-luma";
  }
}
