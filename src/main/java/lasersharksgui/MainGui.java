package lasersharksgui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lasersharks.Logger;
import lasersharks.Options;
import lasersharksgui.panes.AbstractStandardPane;
import lasersharksgui.panes.GamePane;

/**
 * The MainGui class is used for running the game.
 *
 * @author SEMGroup27
 */
@SuppressWarnings("restriction")
public class MainGui extends Application {

  private static MainGui instance;
  private Scene currentScene;
  private AbstractStandardPane currentPane;
  private StackPane stackPane;

  /**
   * @param args parameters to influence the startup of this game.
   */
  public static void main(final String... args) {
    launch(args);
  }

  /**
   * Returns the current MainGui instance.
   *
   * @return the current instance.
   */
  public static MainGui getInstance() {
    return instance;
  }

  /**
   * Sets the singleton instance of the maingui class.
   *
   * @param newInstance the new instance of the maingui class
   */
  public static void setInstance(final MainGui newInstance) {
    instance = newInstance;
  }

  /**
   * This is the static version of the browseTo method.
   *
   * @param paneClass The class of the panel we should browse to.
   */
  public static void browseToGlobal(final Class<? extends AbstractStandardPane> paneClass) {
    getInstance().browseTo(paneClass);
  }

  /**
   * Clear the current singleton instance.
   */
  public static void clearInstance() {
    instance = null;
  }

  /**
   * The start method is the first method run when we launch our application.
   */
  @Override
  public void start(final Stage stage) throws Exception {
    setInstance(this);
    stage.setFullScreen(true);
    stackPane = new StackPane();
    currentScene = new Scene(stackPane, Options.getGlobalWidth(), Options.getGlobalHeight(),
        Options.getBackgroundColor());
    stage.setScene(currentScene);

    // we start the application by showing the gamePanel
    currentPane = new GamePane();
    stackPane.getChildren().add(currentPane);
    stage.show();
  }

  /**
   * This function is used to browse to an other panel.
   *
   * @param paneClass The class of the panel we should browse to.
   */
  public void browseTo(final Class<? extends AbstractStandardPane> paneClass) {
    AbstractStandardPane paneToShow;
    try {
      paneToShow = paneClass.newInstance();
      browseTo(paneToShow);
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function is used to browse to an other panel.
   *
   * @param pane The pane we should browse to.
   */
  public void browseTo(final AbstractStandardPane pane) {
    try {
      pane.setOpacity(1.0);

      currentPane.setOpacity(0.0);
      currentPane.stop();
      currentPane = pane;

      stackPane.getChildren().add(pane);
    } catch (Exception e) {
      Logger.getInstance().write(e.getClass().getName() + "could not browse", e.getMessage());
    }
  }

  /**
   * Add an overlay to the current Pane.
   *
   * @param pane the pane that should act as an overlay.
   */
  public void addOverlay(final Pane pane) {
    stackPane.getChildren().add(pane);
  }

  /**
   * Removes a pane from the stackpane Makes sure that it's not the root pane.
   *
   * @param pane the pane to be removed.
   */
  public void removeOverlay(final Pane pane) {
    if (!pane.equals(currentPane)) {
      stackPane.getChildren().remove(pane);
    }
  }

  /**
   * @return the currentScene
   */
  public Scene getCurrentScene() {
    return currentScene;
  }

  /**
   * @return the currentPane
   */
  public synchronized Pane getCurrentPane() {
    return currentPane;
  }

  @Override
  public void stop() throws Exception {
    Platform.exit();
  }

}
