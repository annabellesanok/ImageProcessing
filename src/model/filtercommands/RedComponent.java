package model.filtercommands;

import model.IPixel;
import model.Layer;
import model.RGBPixel;
import model.filtercommands.FilterCommand;

/**
 * Filters the red component of a given layer.
 */
public class RedComponent implements FilterCommand {
  @Override
  public void execute(Layer l) {
    int height = l.getPixels()[0].length;
    int width = l.getPixels().length;
    l.setLayer(l.getOgPixelsCopy());
    IPixel[][] layer = l.getPixels();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        IPixel pixel = layer[x][y]; //gets the pixel at x, y
        //replaces it with a pixel w only red component
        int alp = pixel.getAlpha();
        if (alp > 0) {
          pixel = new RGBPixel(pixel.getRed(), 0, 0, pixel.getAlpha());
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
    return "red-component";
  }
}
