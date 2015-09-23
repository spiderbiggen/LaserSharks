package lasersharksgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lasersharks.Logger;
import lasersharks.Options;

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
  
  @Override
  public void start(Stage stage) throws Exception {
    instance = this;
    stage.setFullScreen(true);
    stackPane = new StackPane();
    currentScene = new Scene(
        stackPane, 
        Options.getGlobalWidth(), 
        Options.getGlobalHeight(), 
        Options.getBackGroundColor()
    );
    stage.setScene(currentScene);
    currentPane = new Pane();
    
    //we start the application by showing the gamePanel
    showPane(GamePane.class);
    stage.show();
  }
  
  public void showPane(Class<? extends StandardPane> paneClass) {
    try {
           Pane paneToShow = (Pane) paneClass.newInstance();
           paneToShow.setOpacity(1.0);
           currentPane.setOpacity(0.0);
           currentPane = paneToShow;
           stackPane.getChildren().add(paneToShow);
       } catch (Exception e) {
           Logger.getInstance().write(e.getClass().getName() + "could not browse", e.getMessage());
       }       
  }
  
  public static MainGui getInstance() {
    return instance;
  }
  
  public static void browseTo(Class<? extends StandardPane>  paneClass) {
    instance.showPane(paneClass);
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
