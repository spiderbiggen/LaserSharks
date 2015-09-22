package lasersharksgui;

import java.io.FileNotFoundException;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lasersharks.Highscores;
import lasersharks.Options;
import lasersharks.Position;

public class MessagePane extends StandardPane {

  /**
   * This function makes a scene with a message to display.
   * 
   * @param message
   *          the message to display
   * @return new scene
   * @throws FileNotFoundException
   *           highscore file not found
   */
  public MessagePane () {
    super();   
  }
  
  public void addText(String message, int textSize, Position position){
    Text gameText = new Text(message);
    gameText.setScaleX(textSize);
    gameText.setScaleY(textSize);
    gameText.setX(position.getPosX());
    gameText.setY(position.getPosY());
    getChildren().add(gameText);
  }
  
  public void addMidText(String message, int textSize, double deltaY) {
    addText(message, textSize, new Position(Position.middlePosition().getPosX(),Position.middlePosition().getPosY() - deltaY ));
  }
}
