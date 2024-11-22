package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read an image from file and simply print its contents.
 * Will be extended to support different types in the future by adding cases for the token,
 * indicating which type format the image has. Each supportable image format type will have its
 * own readFORMAT method to return a 2-dimension pixel array.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and convert it into a 2D array of pixels.
   *
   * @param filename the path of the file.
   */
  public static RGBPixel[][] readImage(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    return ImageUtil.readPPM(sc);
  }

  /**
   * A method to read a PPM file, called when the general readImage method sees that the token
   * indicates that the image has type PPM/.
   *
   * @param sc the scanner containing contents of the image file
   * @return a 2D array of pixels representing the image contents
   */
  public static RGBPixel[][] readPPM(Scanner sc) {
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    RGBPixel[][] image = new RGBPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        RGBPixel pixel = new RGBPixel(r, g, b, 255);
        image[i][j] = pixel;
      }
    }
    return image;
  }

  /**
   * Demo main function.
   *
   * @param args input to the main
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    ImageUtil.readImage(filename);
  }

  /**
   * A method to read a JPEG file and get the contents as a 2D array of pixels.
   *
   * @param filename the file path of the JPEG file to be read
   * @return a 2D array of pixels representing the image contents
   */
  public static IPixel[][] readJPEG(String filename) {
    try {
      // Read in the JPEG image
      BufferedImage image = ImageIO.read(new File(filename));

      // Get the dimensions of the image
      int width = image.getWidth();
      int height = image.getHeight();

      // Create a 2D array of RGBPixel objects
      RGBPixel[][] pixels = new RGBPixel[height][width];

      // Loop through each pixel in the image and create an RGBPixel object for each one
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int rgb = image.getRGB(x, y);
          int alpha = (rgb >> 24) & 0xFF;
          int red = (rgb >> 16) & 0xFF;
          int green = (rgb >> 8) & 0xFF;
          int blue = rgb & 0xFF;
          RGBPixel pixel = new RGBPixel(red, green, blue, alpha);
          pixels[y][x] = pixel;
        }
      }

      return pixels;

    } catch (Exception e) {
      return null;
    }
  }

  /**
   * A method to read a PNG file and get the contents as a 2D array of pixels.
   *
   * @param filename the file path of the PNG file to be read
   * @return a 2D array of pixels representing the image contents
   */
  public static IPixel[][] readPNG(String filename) {
    try {
      // Read in the PNG image
      BufferedImage image = ImageIO.read(new File(filename));

      // Get the dimensions of the image
      int width = image.getWidth();
      int height = image.getHeight();

      // Create a 2D array of RGBPixel objects
      RGBPixel[][] pixels = new RGBPixel[height][width];

      // Loop through each pixel in the image and create an RGBPixel object for each one
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int argb = image.getRGB(x, y);
          int alpha = (argb >> 24) & 0xFF;
          int red = (argb >> 16) & 0xFF;
          int green = (argb >> 8) & 0xFF;
          int blue = argb & 0xFF;
          RGBPixel pixel = new RGBPixel(red, green, blue, alpha);
          pixels[y][x] = pixel;
        }
      }

      return pixels;

    } catch (IOException e) {
      return null;
    }
  }
}
