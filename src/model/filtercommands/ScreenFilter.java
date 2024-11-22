package model.filtercommands;

import model.HSLPixel;
import model.IPixel;
import model.Layer;
import model.Project;
import model.RGBPixel;

/**
 * A filter that uses the lightness value of the composite image's pixels below this image
 * to lighten the image.
 */
public class ScreenFilter implements FilterCommand {
  private final Project project;

  public ScreenFilter(Project project) {
    this.project = project;
  }

  @Override
  public void execute(Layer l) {
    IPixel[][] pixelsbelow = (RGBPixel[][]) project.getPixelsBelow(l);
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
          double lightness = (1.0 - ((1.0 - hsl.getLightness()) * (1.0 - hslbelow.getLightness())));
          pixels[i][j] = new HSLPixel(hue, saturation, lightness).convertHSLtoRGB();
        }
      }
    }
    l.setLayer(pixels);
  }
}
