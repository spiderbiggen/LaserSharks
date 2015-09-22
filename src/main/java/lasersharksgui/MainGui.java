package lasersharksgui;

import javax.swing.JPanel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lasersharks.Options;

public class MainGui extends Application {
  
  private Scene currentScene;
  private Pane currentPane;
  private StackPane stackPane;  
  /**
   * @param args
   *          parameters to influence the startup of this game.
   */
  public static void main(String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage stage) throws Exception {
    stage.show();
    stage.setFullScreen(true);
    currentScene = new Scene(stackPane, 
        Options.getGlobalWidth(), 
        Options.getGlobalHeight(), 
        Options.getBackGroundColor()
        );
    stage.setScene(currentScene);
    
    //we start the application by showing the gamePanel
    showPane(GamePane.class);
  }
  
  public void showPane(Class paneClass) {
    try {
           Pane paneToShow = (Pane) paneClass.newInstance();
           paneToShow.setOpacity(1.0);
           currentPane.setOpacity(0.0);
           stackPane.getChildren().add(paneToShow);
       } catch (Exception e) {
           System.out.println("could not browse");
           e.printStackTrace();
       }       
 }
  
  



}
