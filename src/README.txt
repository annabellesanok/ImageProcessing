Our program allows the user to create a collage style project, composed of as many layers as is
desired. The user can add images at any position on to each layer, and then choose one filter
to set each layer to (or choose to leave it unfiltered). Thus, our image processor loads images
into the project in the desired location, allows the user to manipulate the colors of the image
in various ways, and stack multiple layers into one final composite image.

In both UIs, the user can create a new project or load an existing project into the program. They
can save a project when it is finished (as a text file formatted in a way that the program can read
and load later). The user can add layers to the project and then choose which layer to add a new
image to. The user can also save any layer as an image, and load the images to be placed onto a
layer. They have the option to use one of 12 filters on the layer. The images that can be used and/
or saved are PPMs, JPEGs, and PNGs. Both UIs will somehow let the user know that their desired
command has been executed by presenting a message, such as "project created!" or "image saved".

Requirements/dependencies list:
Classes needed to for compilation:
JFrameView and ImageViewImpl
ImageView Interface
Project interface
Layer interface
Features interface
Controller interface
Layer interface
Java 11 or higher JRE
JUnit 4 for running the tests

The USEME file can be found in the src/ folder.

Design of our code:

* we don't do load project in the script, as new project will already open a project and it must be
closed for load project to work.

Model:

The model package contains the representations of our Projects, Layers, and Pixels,
as well as a package with all the Filter Commands.

Firstly, the model contains the model.ImageUtil class to help read images. Right now, the processor
works with PPM, JPEG, and PNG images. We can extend the types of images allowed with the program
by adding a read method for any image type we want to work with, like using the ImageIO class.

Also, we have a Project interface which contains methods to help save, load, or make new
projects, and to add layers and images to projects and set filters to a layer within a specific
project. The Project interface also contains useful observers for any Project. The CollageProject
class implements Project. There is also the Layer interface which contains methods for changing a
layer by adding filters/images, as well as observers. There is also a setLayer method, which
is used by the filters to change the color values of the pixels on the layer. The CollageLayer class
implements Layer. A CollageProject has a List of Layers as a field.

Our Images are represented by a 2D array of Pixels and an x and y position. We have an IPixel
interface that is implemented by the RGBPixel class and the HSLPixel class.
The RGBPixel class has four fields to represent RGBA (red, green, blue, alpha) values and methods to
observe a Pixel's color values and compare two pixels by color. The HSLPixel class has three fields
to represent hue, saturation, and light. It has methods to observe the color values and compare them
as well. Both pixel types can be converted to the other type.

Finally, in the model, we have the filter commands represented each as a class. We used
the Command Design Pattern to implement the filters. Each class implements the interface FilterCommand,
which has one execute method to apply the respective changes to the 2D array of pixels on a layer.
Thus, a CollageProject has values representing its properties, as well as a list of Layers. Then,
a Layer has a name, FilterCommand, and IPixel[][].

controller:

The controller package contains an interface, ImageController, that has one method to process user
input instructions. THe ImageControllerImpl implements ImageController, and has protected helper
methods to communicate to the model and view. We have used the Command Design Pattern with our
controller as well, so each command implements the interface ICommand, and has one method execute
to complete a user command and delegate to the model if needed. Each command class has the model and
view of the current project, as well as the scanner passed in with user input.

There is also an interface called Features (used in the GUI code) with the behaviors desired from
a controller for our program, and then we made a new class to implement this interface. The features
represent things that can be done by a user, such as saving and loading projects, adding layers, and
setting filters. The new controller still uses the command design pattern with our old command
classes. The command classes contain all the IO interaction within the program.

view:

The view package contains an interface, ImageView, with a simple render message method to tell
the user important things about the program, such as indicate if there is an error, or display
welcome and end messages. ImageViewImpl implements ImageView. ImageViewImpl has an Appendable
for rendering messages, and a Project. We also have a new view class to create the graphical user
interface. This class, JFrameView, implements ImageView as well and has a Features object that it
delegates to in order to execute user command (which are received by action listeners in the GUI
features).

For the images we used as examples, we used the website shutterstock to find a ppm of a landscape
image.
