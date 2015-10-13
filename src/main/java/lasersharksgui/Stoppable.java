package lasersharksgui;

/**
 * Interface for pane that is stoppable.
 * @author SEMGroup27
 *
 */
public interface Stoppable {
  
  /**
   * Stops the pane. Some panes may have an empty stop() method.
   * Stopping the pane should remove all interactivity from this pane.
   */
  void stop();
}
