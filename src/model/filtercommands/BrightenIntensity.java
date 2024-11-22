package model.filtercommands;

import model.IPixel;
import model.Layer;
import model.RGBPixel;

/**
 * This filter brightens the added layer by increasing each of the RGB components by the average.
 */
public class BrightenIntensity implements FilterCommand {
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
          int avg = ((pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3);
          int newred = pixel.getRed() + avg;
          int newgreen = pixel.getGreen() + avg;
          int newblue = pixel.getBlue() + avg;
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
    return "brighten-intensity";
  }
}
