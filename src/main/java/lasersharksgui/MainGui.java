package lasersharksgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lasersharks.Logger;
import lasersharks.Options;
import lasersharks.RestartGameController;

/**
 * The MainGui class is used for running the game.
 * 
 * @author Sytze
 *
 */
@SuppressWarnings("restriction")
public class MainGui extends Application {

  private Scene currentScene;
  private Pane currentPane;
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
    instance = this;
    stage.setFullScreen(true);
    stackPane = new StackPane();
    currentScene = new Scene(stackPane, Options.getGlobalWidth(), Options.getGlobalHeight(),
        Options.getBackGroundColor());
    stage.setScene(currentScene);
    currentPane = new Pane();

    // we start the application by showing the gamePanel
    browseTo(GamePane.class);
    stage.show();
    new RestartGameController();
  }

  /**
   * This function is used to browse to an other panel.
   * 
   * @param paneClass
   *          The class of the panel we should browse to.
   */
  public void browseTo(Class<? extends StandardPane> paneClass) {
    try {

      Pane paneToShow = (Pane) paneClass.newInstance();
      paneToShow.setOpacity(1.0);
      currentPane.setOpacity(0.0);
      if (currentPane instanceof GamePane) {
        GamePane gamePane = (GamePane) currentPane;
        gamePane.stopGame();
      }
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
  public Pane getCurrentPane() {
    return currentPane;
  }
}
