package lasersharksgui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import lasersharks.Options;

public abstract class StandardPane extends Pane {


  protected static final int TEXT_SCALE_SIZE_BIG = 17;
  protected static final int TEXT_SCALE_SIZE_MED = 10;
  protected static final int TEXT_SCALE_SIZE_SMALL = 4;
  
  public StandardPane() {
    super();
    addBackGround(); 
  }
  
  /**
   * This function adds a background to the panel
   */
  public void addBackGround() {
    BackgroundImage myBI = new BackgroundImage(
        new Image(Options.getInstance().getBackGroundImage(), 
            Options.getGlobalWidth(), Options.getGlobalHeight(), true, false), 
            BackgroundRepeat.REPEAT,
        BackgroundRepeat.NO_REPEAT, 
        BackgroundPosition.DEFAULT, 
        BackgroundSize.DEFAULT);
    setBackground(new Background(myBI));
  }
}
