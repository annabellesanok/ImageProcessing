import org.junit.Test;

import java.io.IOException;

import model.CollageProject;
import model.Project;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests the methods in the original text view class.
 */
public class ViewTests {
  ImageView view;

  @Test
  public void testToString() {
    Project p1 = new CollageProject();
    StringBuilder ap1 = new StringBuilder();
    view = new ImageViewImpl(p1, ap1);
    assertEquals("", view.toString());
  }

  @Test
  public void testRenderMessage() throws IOException {
    Project p1 = new CollageProject();
    StringBuilder ap1 = new StringBuilder();
    view = new ImageViewImpl(p1, ap1);
    view.renderMessage("hello");
    assertEquals("hello", ap1.toString());
  }

  @Test
  public void testInvalidConstruction() throws IOException {
    Project p1 = new CollageProject();
    StringBuilder ap1 = new StringBuilder();
    view = new ImageViewImpl(p1, ap1);
    view.renderMessage("hello");
    assertEquals("hello", ap1.toString());
    try {
      view = new ImageViewImpl(null, ap1);
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  @Test
  public void testInvalidConstruction2() throws IOException {
    Project p1 = new CollageProject();
    StringBuilder ap1 = new StringBuilder();
    view = new ImageViewImpl(p1, ap1);
    view.renderMessage("hello");
    assertEquals("hello", ap1.toString());
    try {
      view = new ImageViewImpl(p1, null);
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }


}
