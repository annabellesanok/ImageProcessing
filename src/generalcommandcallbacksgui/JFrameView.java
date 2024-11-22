package generalcommandcallbacksgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.IPixel;
import model.ImageUtil;
import view.ImageView;

/**
 * A class to create a graphical user interface for our image processing program. The GUI
 * allows to use to create projects, add layers/images, and execute saving and loading with
 * buttons. The GUI also allows the user to add filters to the layers and select which layer to
 * edit. This class has a Features object which is the MVC's controller.
 */
public class JFrameView extends JFrame implements ActionListener, ImageView {
  private Features ctrl;
  private final JPanel layersPanel;
  private final JLabel imageLabel;

  private final ArrayList<JRadioButton> layerButtons;
  private final ButtonGroup layerButtonGroup = new ButtonGroup();

  private String prjName;
  private int prjHeight;
  private int prjWidth;

  private boolean projectLoaded;
  private boolean imageLoaded;

  /**
   * Construction of the GUI and all its parts with the given caption.
   *
   * @param caption the GUI's title
   */
  public JFrameView(String caption) {
    // setting a title for our GUI
    super(caption);

    prjName = "";
    prjHeight = 0;
    prjWidth = 0;

    projectLoaded = false;
    imageLoaded = false;

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(800, 800));
    setLocation(400, 40);

    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BorderLayout());
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);


    layersPanel = new JPanel();
    layersPanel.setBorder(BorderFactory.createTitledBorder("Layers"));

    JButton addlayer = new JButton("Add Layer");
    layersPanel.add(addlayer);
    addlayer.addActionListener(this);
    addlayer.setActionCommand("add layer");

    layerButtons = new ArrayList<>();
    ButtonGroup layerGroup = new ButtonGroup();
    // automatically add first layer


    layersPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
    layersPanel.setBounds(0, 0, 200, 400);
    layersPanel.setLayout(new BoxLayout(layersPanel, BoxLayout.PAGE_AXIS));
    layersPanel.setBackground(Color.GRAY);
    mainPanel.add(layersPanel, BorderLayout.WEST);

    JLabel display = new JLabel("Layers");
    layersPanel.add(display);

    JScrollPane layerScrollPane = new JScrollPane(this.layersPanel);
    layerScrollPane.setBounds(0, 0, 200, 300);
    mainPanel.add(layerScrollPane, BorderLayout.WEST);

    JPanel functionButtonsPanel = new JPanel();
    functionButtonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30,
            10));
    functionButtonsPanel.setBounds(200, 0, 300, 140);
    functionButtonsPanel.setBackground(Color.LIGHT_GRAY);

    //newproject, loadproject, addimagetolayer, saveimage, saveproject, addlayer
    // in the panel: functions button panel
    JButton newproject = new JButton("New Project");
    newproject.addActionListener(this);
    newproject.setActionCommand("new project");
    functionButtonsPanel.add(newproject);

    JButton loadproject = new JButton("Load Project");
    loadproject.addActionListener(this);
    loadproject.setActionCommand("load project");
    functionButtonsPanel.add(loadproject);

    JButton addimagetolayer = new JButton("Add Image to Layer");
    addimagetolayer.addActionListener(this);
    addimagetolayer.setActionCommand("add image to layer");
    String layerNameAITL = ""; // get this from button?
    functionButtonsPanel.add(addimagetolayer);

    JButton saveimage = new JButton("Save Image");
    saveimage.addActionListener(this);
    saveimage.setActionCommand("save image");
    functionButtonsPanel.add(saveimage);

    JButton saveproject = new JButton("Save Project");
    saveproject.addActionListener(this);
    saveproject.setActionCommand("save project");
    functionButtonsPanel.add(saveproject);

    //exit button
    JButton exitButton = new JButton("Exit");
    functionButtonsPanel.add(exitButton);
    exitButton.addActionListener(this);
    exitButton.setActionCommand("exit");
    mainPanel.add(functionButtonsPanel, BorderLayout.NORTH);

    JPanel filtersPanel = new JPanel();
    filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.PAGE_AXIS));


    //buttons groups are used to combine radio buttons. Only one radio
    // button in each group can be selected


    filtersPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
    filtersPanel.setBounds(0, 300, 200, 500);
    filtersPanel.setBackground(Color.LIGHT_GRAY);

    //filters with radio buttons
    JLabel setfilter = new JLabel("Choose A Filter");
    filtersPanel.add(setfilter);

    // in the filters panel
    ArrayList<JRadioButton> filterButtons = new ArrayList<JRadioButton>();

    JRadioButton normal = new JRadioButton();
    normal.setText("normal");
    normal.addActionListener(this);
    normal.setActionCommand("normal");
    ButtonGroup filterGroup = new ButtonGroup();
    filterGroup.add(normal);
    filterButtons.add(normal);
    filtersPanel.add(normal);


    JRadioButton redcomponent = new JRadioButton();
    redcomponent.setText("red-component");
    redcomponent.addActionListener(this);
    redcomponent.setActionCommand("red component");
    filterGroup.add(redcomponent);
    filterButtons.add(redcomponent);
    filtersPanel.add(redcomponent);

    JRadioButton greencomponent = new JRadioButton();
    greencomponent.setText("green-component");
    greencomponent.addActionListener(this);
    greencomponent.setActionCommand("green component");
    filterGroup.add(greencomponent);
    filterButtons.add(greencomponent);
    filtersPanel.add(greencomponent);

    JRadioButton bluecomponent = new JRadioButton();
    bluecomponent.setText("blue-component");
    bluecomponent.addActionListener(this);
    bluecomponent.setActionCommand("blue component");
    filterButtons.add(bluecomponent);
    filterGroup.add(bluecomponent);
    filtersPanel.add(bluecomponent);

    JRadioButton brightenvalue = new JRadioButton();
    brightenvalue.setText("brighten-value");
    brightenvalue.addActionListener(this);
    brightenvalue.setActionCommand("brighten value");
    filterButtons.add(brightenvalue);
    filterGroup.add(brightenvalue);
    filtersPanel.add(brightenvalue);

    JRadioButton darkenvalue = new JRadioButton();
    darkenvalue.setText("darken-value");
    darkenvalue.addActionListener(this);
    darkenvalue.setActionCommand("darken value");
    filterGroup.add(darkenvalue);
    filterButtons.add(darkenvalue);
    filtersPanel.add(darkenvalue);

    JRadioButton brightenintensity = new JRadioButton();
    brightenintensity.setText("brighten-intensity");
    brightenintensity.addActionListener(this);
    brightenintensity.setActionCommand("brighten intensity");
    filterGroup.add(brightenintensity);
    filterButtons.add(brightenintensity);
    filtersPanel.add(brightenintensity);

    JRadioButton darkenintensity = new JRadioButton();
    darkenintensity.setText("darken-intensity");
    darkenintensity.addActionListener(this);
    darkenintensity.setActionCommand("darken intensity");
    filterGroup.add(darkenintensity);
    filterButtons.add(darkenintensity);
    filtersPanel.add(darkenintensity);

    JRadioButton brightenluma = new JRadioButton();
    brightenluma.setText("brighten-luma");
    brightenluma.addActionListener(this);
    brightenluma.setActionCommand("brighten luma");
    filterGroup.add(brightenluma);
    filterButtons.add(brightenluma);
    filtersPanel.add(brightenluma);

    JRadioButton darkenluma = new JRadioButton();
    darkenluma.setText("darken-luma");
    darkenluma.addActionListener(this);
    darkenluma.setActionCommand("darken luma");
    filterGroup.add(darkenluma);
    filterButtons.add(darkenluma);
    filtersPanel.add(darkenluma);

    JRadioButton differencefilter = new JRadioButton();
    differencefilter.setText("difference");
    differencefilter.addActionListener(this);
    differencefilter.setActionCommand("difference");
    filterGroup.add(differencefilter);
    filterButtons.add(differencefilter);
    filtersPanel.add(differencefilter);

    JRadioButton multiplyfilter = new JRadioButton();
    multiplyfilter.setText("multiply");
    multiplyfilter.addActionListener(this);
    multiplyfilter.setActionCommand("multiply");
    filterGroup.add(multiplyfilter);
    filterButtons.add(multiplyfilter);
    filtersPanel.add(multiplyfilter);

    JRadioButton screenfilter = new JRadioButton();
    screenfilter.setText("screen");
    screenfilter.addActionListener(this);
    screenfilter.setActionCommand("screen");
    filterGroup.add(screenfilter);
    filterButtons.add(screenfilter);
    filtersPanel.add(screenfilter);

    JScrollPane filterScrollPane = new JScrollPane(filtersPanel);
    filterScrollPane.setBounds(0, 300, 200, 500);
    mainPanel.add(filterScrollPane, BorderLayout.EAST);

    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
    //imagePanel.setBounds(200, 600, 600, 600);
    imagePanel.setBackground(Color.WHITE);

    JScrollPane imageScrollPane = new JScrollPane(imagePanel);
    imageLabel = new JLabel("");
    imagePanel.add(imageLabel);
    mainPanel.add(imageScrollPane, BorderLayout.CENTER);

    pack();
    setVisible(true);
  }


  @Override
  public void renderMessage(String message) throws IOException {
    // gui renders in action performed
  }

  @Override
  public void addFeatures(Features controller) {
    this.ctrl = controller;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    switch (e.getActionCommand()) {
      case "new project":
        JLabel newProjectInput = new JLabel();
        newProjectInput.setText("New project name:");
        boolean validName = false;
        while (!validName) {
          try {
            prjName = JOptionPane.showInputDialog(newProjectInput);
            if (prjName.length() > 0) {
              validName = true;
            } else {
              showErrorPopup("Enter a valid name");
            }
          } catch (NullPointerException ex) {
            //ignore
          }
        }
        newProjectInput.setText("Enter height:");
        boolean validHeight = false;
        while (!validHeight) {
          try {
            prjHeight = Integer.parseInt(JOptionPane.showInputDialog(newProjectInput));
            validHeight = true;
          } catch (NumberFormatException exception) {
            showErrorPopup("Error: enter a number.");
          } catch (NullPointerException exception) {
            //ignore
          }
        }
        newProjectInput.setText("Enter width:");
        boolean validWidth = false;
        while (!validWidth) {
          try {
            prjWidth = Integer.parseInt(JOptionPane.showInputDialog(newProjectInput));
            validWidth = true;
          } catch (NumberFormatException exception) {
            showErrorPopup("Error: enter a number.");
          } catch (NullPointerException exception) {
            //ignore
          }
        }
        if ((validName) && (validWidth) && (validHeight)) {
          ctrl.makeNewProject(prjName, prjWidth, prjHeight);
          projectLoaded = true;
          JOptionPane.showMessageDialog(this, "Project Created!",
                  "", -1);
        }
        break;
      case "add layer":
        if (!projectLoaded) {
          showErrorPopup("Cannot add a layer with no project to work on. Add or " +
                  "load a project first.");
        } else {
          // add a button for the new layer
          JLabel newLayerName = new JLabel();
          newLayerName.setText("New layer name:");
          boolean validlayerName = false;
          String input = null;
          while (!validlayerName) {
            try {
              input = JOptionPane.showInputDialog(newLayerName);
              if (input.length() > 0) {
                validlayerName = true;
              } else {
                showErrorPopup("Enter a valid name");
              }
            } catch (NullPointerException ex) {
              //ignore
            }
          }
          JRadioButton newButton = new JRadioButton();
          newButton.setText(input);
          newButton.setSelected(true);
          layerButtonGroup.add(newButton);
          this.layerButtons.add(newButton);
          this.layersPanel.add(newButton);
          this.layersPanel.revalidate();
          this.layersPanel.repaint();
          ctrl.addLayer(input);
        }
        break;
      case "exit":
        this.dispose();
        break;
      case "load project":
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Collage files", "txt", "collage");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(JFrameView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          this.ctrl.loadProject(f.getAbsolutePath());
          this.renderAllLayers();
        }
        break;
      case "save project":
        if (!projectLoaded) {
          showErrorPopup("No project to save!");
        } else {
          String fileName = prjName; // the name of the file you're saving
          JFileChooser fileChooser = new JFileChooser(".");
          fileChooser.setDialogTitle("Save Project File");
          fileChooser.setSelectedFile(new File(fileName)); // set the default file name
          // allow selection of directories only
          fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          int userSelection = fileChooser.showSaveDialog(this);

          if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            this.ctrl.saveProject(fileToSave.getAbsolutePath());
          }
        }
        break;
      case "add image to layer":
        if (layerButtons.size() < 1) {
          showErrorPopup("Cannot add a image with no layer present. Add a layer first.");
        } else {
          JLabel addImageInput = new JLabel();
          addImageInput.setText("Layer Name");
          String layerName1 = "";
          for (int i = 0; i < layerButtons.size(); i++) {
            if (layerButtons.get(i).isSelected()) {
              layerName1 = layerButtons.get(i).getText();
              break;
            }
          }
          final JFileChooser chosenfile = new JFileChooser(".");
          String imagepath = "";
          FileNameExtensionFilter filefilter = new FileNameExtensionFilter(
                  "PPM images", "ppm", "JPEG file", "jpg", "jpeg", "png");
          chosenfile.setFileFilter(filefilter);
          int value = chosenfile.showOpenDialog(this);
          if (value == JFileChooser.APPROVE_OPTION) {
            File f = chosenfile.getSelectedFile();
            imagepath = f.getAbsolutePath();
          }
          JLabel addimageposn = new JLabel();
          addimageposn.setText("x Position of Image");
          boolean validx = false;
          int x = 0;
          while (!validx) {
            try {
              x = Integer.parseInt(JOptionPane.showInputDialog(addimageposn));
              validx = true;
            } catch (NumberFormatException exception) {
              showErrorPopup("Error: enter a number.");
            } catch (NullPointerException exception) {
              //ignore
            }
          }
          addimageposn.setText("y Position of Image");
          boolean validy = false;
          int y = 0;
          while (!validy) {
            try {
              y = Integer.parseInt(JOptionPane.showInputDialog(addimageposn));
              validy = true;
            } catch (NumberFormatException exception) {
              showErrorPopup("Error: enter a number.");
            } catch (NullPointerException exception) {
              //ignore
            }
          }
          this.ctrl.addImageToLayer(layerName1, imagepath, x, y);
          imageLoaded = true;
          imageLabel.setIcon(new ImageIcon(this.createImage(imagepath, x, y)));
        }
        break;
      case "save image":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to save!");
        } else {
          JFileChooser fileChooserSaveImg = new JFileChooser(".");
          fileChooserSaveImg.setDialogTitle("Save image");
          fileChooserSaveImg.showSaveDialog(this);
          String imagePathToSave = fileChooserSaveImg.getSelectedFile().getPath();
          String layerName = "";
          for (int i = 0; i < layerButtons.size(); i++) {
            if (layerButtons.get(i).isSelected()) {
              layerName = layerButtons.get(i).getText();
              break;
            }
          }
          this.ctrl.saveImage(imagePathToSave, layerName);
        }
        break;
      case "normal":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "normal");
          this.renderAllLayers();
        }
        break;
      case "red component":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "red-component");
          this.renderAllLayers();
        }
        break;
      case "green component":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "green-component");
          this.renderAllLayers();
        }
        break;
      case "blue component":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "blue-component");
          this.renderAllLayers();
        }
        break;
      case "brighten value":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "brighten-value");
          this.renderAllLayers();
        }
        break;
      case "darken value":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "darken-value");
          this.renderAllLayers();
        }
        break;
      case "brighten intensity":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "brighten-intensity");
          this.renderAllLayers();
        }
        break;
      case "darken intensity":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "darken-intensity");
          this.renderAllLayers();
        }
        break;
      case "brighten luma":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "brighten-luma");
          this.renderAllLayers();
        }
        break;
      case "darken luma":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "darken-luma");
          this.renderAllLayers();
        }
        break;
      case "difference":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "difference");
          this.renderAllLayers();
        }
        break;
      case "multiply":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "multiply");
          this.renderAllLayers();
        }
        break;
      case "screen":
        if (layerButtons.size() < 1 || !imageLoaded) {
          showErrorPopup("No image to apply filter to!");
        } else {
          ctrl.setFilter(this.getLayer(), "screen");
          this.renderAllLayers();
        }
        break;
      default:
        // do nothing
        break;
    }
  }

  private void showErrorPopup(String message) {
    JOptionPane.showMessageDialog(this, message, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  private Image createImage(String filepath, int xposn, int yposn) {
    String extension = filepath.substring(filepath.lastIndexOf('.') + 1).toLowerCase();
    IPixel[][] imagePixels = null;
    switch (extension) {
      case "jpg":
      case "jpeg":
        imagePixels = ImageUtil.readJPEG(filepath);
        break;
      case "png":
        imagePixels = ImageUtil.readPNG(filepath);
        break;
      case "ppm":
        imagePixels = ImageUtil.readImage(filepath);
        break;
      default:
        showErrorPopup("Unsupported file type!");
    }

    BufferedImage image = new BufferedImage(imagePixels[0].length + xposn,
            imagePixels.length + yposn, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < imagePixels.length; y++) {
      for (int x = 0; x < imagePixels[0].length; x++) {
        IPixel px = imagePixels[y][x];
        int argb = px.getRed() << 16;
        argb |= px.getGreen() << 8;
        argb |= px.getBlue();
        image.setRGB(x + xposn, y + yposn, argb);
      }
    }
    return image;
  }

  private void renderAllLayers() {
    IPixel[][] projectPixels = this.ctrl.getProjectPixels();
    BufferedImage image = new BufferedImage(projectPixels.length, projectPixels[0].length,
            BufferedImage.TYPE_INT_RGB);
    int height = projectPixels.length;
    int width = projectPixels[0].length;

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        IPixel px = projectPixels[x][y];
        int argb = px.getRed() << 16;
        argb |= px.getGreen() << 8;
        argb |= px.getBlue();
        image.setRGB(x, y, argb);
      }
    }
    imageLabel.setIcon(new ImageIcon(image));
  }


  private String getLayer() {
    String currentlayer = "";
    List<JRadioButton> projLayers = this.layerButtons;
    for (int i = 0; i < projLayers.size(); i++) {
      if (layerButtons.get(i).isSelected()) {
        currentlayer = layerButtons.get(i).getText();
      }
    }
    return currentlayer;
  }

}


