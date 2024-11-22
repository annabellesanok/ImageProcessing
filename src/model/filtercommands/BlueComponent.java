package model.filtercommands;

import model.IPixel;
import model.Layer;
import model.RGBPixel;

/**
 * Filters the blue component of a given layer.
 */
public class BlueComponent implements FilterCommand {
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
        //replaces it with a pixel w only blue component
        if (alp > 0) {
          pixel = new RGBPixel(0, 0, pixel.getBlue(), pixel.getAlpha());
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
    return "blue-component";
  }
}
