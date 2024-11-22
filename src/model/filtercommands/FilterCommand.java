package model.filtercommands;

import model.Layer;

/**
 * Represents our "command" interface with a method to execute the command on a layer object.
 * This command interface is to be used specifically for different types of filters.
 */
public interface FilterCommand {

  /**
   * Executes a command on a Layer object.
   *
   * @param l the layer that the command is being executed on
   */
  void execute(Layer l);

  /**
   * This filter's name in the form of a string.
   *
   * @return a String representing the name of this filter.
   */
  String toString();
}
