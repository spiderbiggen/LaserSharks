package lasersharksgui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lasersharks.Logger;
import lasersharks.Options;
import lasersharksgui.panes.GamePane;
import lasersharksgui.panes.StandardPane;

/**
 * The MainGui class is used for running the game.
 * 
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class MainGui extends Application {

  private Scene currentScene;
  private StandardPane currentPane;
  private StackPane stackPane;
  private static MainGui instance;

  /**
   * @param args
   *          parameters to influence the startup of this game.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * The start method is the first method run when we launch our application.
   */
  @Override
  public void start(Stage stage) throws Exception {
    setInstance(this);
    stage.setFullScreen(true);
    stackPane = new StackPane();
    currentScene = new Scene(stackPane, Options.getGlobalWidth(), Options.getGlobalHeight(),
        Options.getBackGroundColor());
    stage.setScene(currentScene);

    // we start the application by showing the gamePanel
    currentPane = new GamePane();
    stackPane.getChildren().add(currentPane);
    stage.show();
  }

  /**
   * This function is used to browse to an other panel.
   * 
   * @param paneClass
   *          The class of the panel we should browse to.
   */
  public void browseTo(Class<? extends StandardPane> paneClass) {
    StandardPane paneToShow;
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
   * @param pane
   *          The pane we should browse to.
   */
  public void browseTo(StandardPane pane) {
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
   * Returns the current MainGui instance.
   * 
   * @return the current instance.
   */
  public static MainGui getInstance() {
    return instance;
  }

  /**
   * Add an overlay to the current Pane.
   * 
   * @param pane
   *          the pane that should act as an overlay.
   */
  public void addOverlay(Pane pane) {
    stackPane.getChildren().add(pane);
  }

  /**
   * Removes a pane from the {@link javafx.scene.layout.StackPane} 
   * Makes sure that it's not the root pane.
   * 
   * @param pane
   *          the pane to be removed.
   */
  public void removeOverlay(Pane pane) {
    if (!pane.equals(currentPane)) {
      stackPane.getChildren().remove(pane);
    }
  }

  /**
   * This is the static version of the browseTo method.
   * 
   * @param paneClass
   *          The class of the panel we should browse to.
   */
  public static void browseToGlobal(Class<? extends StandardPane> paneClass) {
    getInstance().browseTo(paneClass);
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

  /**
   * Sets the singleton instance of the main gui class.
   * 
   * @param newInstance
   *          the new instance of the main gui class
   */
  public static void setInstance(MainGui newInstance) {
    instance = newInstance;
  }

  /**
   * Clear the current singleton instance.
   */
  public static void clearInstance() {
    instance = null;
  }

}
