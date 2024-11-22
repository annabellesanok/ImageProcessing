package model.filtercommands;

import model.HSLPixel;
import model.IPixel;
import model.Layer;
import model.Project;
import model.RGBPixel;

/**
 * A filter that multiplies color values together to darken an image.
 */
public class MultiplyFilter implements FilterCommand {
  private final Project project;

  public MultiplyFilter(Project project) {
    this.project = project;
  }

  @Override
  public void execute(Layer l) {
    IPixel[][] pixelsbelow = project.getPixelsBelow(l);
    l.setLayer(l.getOgPixelsCopy());
    IPixel[][] pixels = (RGBPixel[][]) l.getPixels();

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        IPixel pixel = pixels[i][j];
        int alp = pixel.getAlpha();
        if (alp > 0) {
          IPixel hsl = pixel.convertPixel();
          IPixel pixelbelow = pixelsbelow[i][j];
          IPixel hslbelow = pixelbelow.convertPixel();
          double hue = hsl.getHue();
          double saturation = hsl.getSaturation();
          double lightness = hsl.getLightness() * hslbelow.getLightness();
          pixels[i][j] = new HSLPixel(hue, saturation, lightness).convertHSLtoRGB();
        }
      }
    }
    l.setLayer(pixels);
  }
}
