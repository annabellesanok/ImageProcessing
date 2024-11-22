package generalcommandcallbacksgui;

import java.util.List;

import controller.commands.AddImageToLayerCommand;
import controller.commands.AddLayerCommand;
import controller.commands.LoadProjectCommand;
import controller.commands.NewProjectCommand;
import controller.commands.SaveImageCommand;
import controller.commands.SaveProjectCommand;
import controller.commands.SetFilterCommand;
import model.IPixel;
import model.Layer;
import model.Project;
import view.ImageView;

/**
 * A new controller that implements all desired behaviors to handle user input in the image
 * processing program. Connects to a GUI view.
 */
public class Controller implements Features {
  private final Project model;
  private ImageView view;

  public Controller(Project m) {
    model = m;
  }

  public void setView(ImageView v) {
    view = v;
    view.addFeatures(this);
  }

  @Override
  public IPixel[][] getLayerPixels(String layername) {
    for (int i = 0; i < this.model.getLayers().size(); i++) {
      if (this.model.getLayers().get(i).getLayerName().equals(layername)) {
        return this.model.getLayers().get(i).getPixels();
      }
    }
    throw new RuntimeException("layer not found");
  }

  @Override
  public List<Layer> getLayers() {
    return this.model.getLayers();
  }

  @Override
  public IPixel[][] getProjectPixels() {
    return this.model.getProjectPixels();
  }

  @Override
  public void makeNewProject(String name, int width, int height) {
    new NewProjectCommand(this.model, this.view, name, height, width).execute();
  }

  @Override
  public void loadProject(String filePath) {
    new LoadProjectCommand(this.model, this.view, filePath).execute();
  }

  @Override
  public void addImageToLayer(String layerName, String imagePath, int x, int y) {
    new AddImageToLayerCommand(this.model, this.view, layerName, imagePath, x, y).execute();
  }

  @Override
  public void addLayer(String layerName) {
    new AddLayerCommand(this.model, this.view, layerName).execute();
  }

  @Override
  public void setFilter(String layerName, String filterName) {
    new SetFilterCommand(this.model, this.view, layerName, filterName).execute();
  }

  @Override
  public void saveImage(String imagePath, String layerName) {
    new SaveImageCommand(this.model, this.view, imagePath, layerName).execute();
  }

  @Override
  public void saveProject(String filePath) {
    new SaveProjectCommand(this.model, this.view, filePath).execute();
  }

}
