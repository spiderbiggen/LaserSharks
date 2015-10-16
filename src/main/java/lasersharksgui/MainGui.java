package lasersharksgui;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lasersharks.Logger;
import lasersharks.controllers.Options;
import lasersharks.controllers.RestartGameController;

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
    try {
      StandardPane paneToShow = (StandardPane) paneClass.newInstance();
      paneToShow.setOpacity(1.0);
      
      currentPane.setOpacity(0.0);
      currentPane.stop();
      currentPane = paneToShow;
      
      stackPane.getChildren().add(paneToShow);
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
   * This is the static version of the browseTo method.
   * 
   * @param paneClass
   *          The class of the panel we should browse to.
   */
  public static void browseToGlobal(Class<? extends StandardPane> paneClass) {
    instance.browseTo(paneClass);
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

  private static void setInstance(MainGui newInstance) {
    instance = newInstance;
  }
  
  public static void clearInstance() {
    instance = null;
  }

}
