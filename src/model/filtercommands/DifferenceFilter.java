package model.filtercommands;

import model.IPixel;
import model.Layer;
import model.Project;
import model.RGBPixel;

/**
 * This blending filter inverts the colors of an image on the current layer, by
 * taking two pixels RGB components and subtracts them component-wise.
 */
public class DifferenceFilter implements FilterCommand {
  private final Project project;

  public DifferenceFilter(Project project) {
    this.project = project;
  }

  @Override
  public void execute(Layer l) {
    IPixel[][] pixelsbelow = project.getPixelsBelow(l);
    l.setLayer(l.getOgPixelsCopy());
    IPixel[][] pixels = l.getPixels();

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        IPixel pixel = pixels[i][j];
        int alp = pixel.getAlpha();
        if (alp > 0) {
          IPixel pixelbelow = pixelsbelow[i][j];
          int r = Math.abs(pixel.getRed() - pixelbelow.getRed());
          int g = Math.abs(pixel.getGreen() - pixelbelow.getGreen());
          int b = Math.abs(pixel.getBlue() - pixelbelow.getBlue());
          pixels[i][j] = new RGBPixel(r, g, b, 255);
        }
      }
    }
    l.setLayer(pixels);
  }
}
