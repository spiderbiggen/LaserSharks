package laserSharksGUI;

import java.util.LinkedList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FXGUI extends Application {
	Label lb_text;
	private static final int KEYBOARD_MOVEMENT_DELTA = 5;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
					
		StackPane imageContainer = new StackPane();
        ImageView image = new ImageView("better background.jpg");
        
        final Circle circle = new Circle(100, 75, 25, Color.ORANGE);
		circle.setOpacity(1);
		
        imageContainer.getChildren().addAll(image);
        
        Pane pane = new Pane(circle);	
        pane.getChildren().add(image);
        
		final Scene scene = new Scene(pane, 1920, 1080, Color.BLUE);

		// Scene scene = new Scene(root);

		BackgroundImage myBI = new BackgroundImage(new Image(
				"better background.jpg", 1920, 1080, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		pane.setBackground(new Background(myBI));
		

		moveCircleOnKeyPress(scene, image);


        final double rectangleHSpeed = 500 ; // pixels per second
        final double rectangleVSpeed = 500 ;
        final double minX = 0 ;
        final double maxX = 1920 ; 
        final double minY = 0 ;
        final double maxY = 1080 ;

        final LinkedList<KeyCode> keyStack = new LinkedList<>();
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (! keyStack.contains(code)) {
                keyStack.push(code); 
            }
        });

        scene.setOnKeyReleased(event -> 
            keyStack.remove(event.getCode()));


        final LongProperty lastUpdateTime = new SimpleLongProperty();
        final AnimationTimer rectangleAnimation = new AnimationTimer() {
          @Override
          public void handle(long timestamp) {
            if (! keyStack.isEmpty() && lastUpdateTime.get() > 0) {
              final double elapsedSeconds = (timestamp - lastUpdateTime.get()) / 1_000_000_000.0 ;
              double deltaX = 0 ;
              double deltaY = 0 ;
              switch(keyStack.peek()) {
              case UP:
                  deltaY = -rectangleVSpeed * elapsedSeconds;
                  break ;
              case DOWN: 
                  deltaY = rectangleVSpeed * elapsedSeconds ;
                  break ;
              case LEFT:
                  deltaX = -rectangleHSpeed * elapsedSeconds ;
                  break ;
              case RIGHT:
                  deltaX = rectangleHSpeed * elapsedSeconds ;
              default:
                  break ;
              }
              double oldX = image.getTranslateX() ;
              double oldY = image.getTranslateY() ;
              image.setTranslateX(clamp(oldX + deltaX, minX, maxX));
              image.setTranslateY(clamp(oldY + deltaY, minY, maxY));
            }
            lastUpdateTime.set(timestamp);
          }
        };
        rectangleAnimation.start();
        
		stage.setScene(scene);
		stage.show();

	}
	
	private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
	
	private void moveCircleOnKeyPress(Scene scene, final ImageView image) {
	    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	      @Override public void handle(KeyEvent event) {
	        while(event.getCode() == KeyCode.UP){
	        	//image.setCenterY(image.getCenterY() - KEYBOARD_MOVEMENT_DELTA);
	        	if (event.KEY_RELEASED != null){
	        		break;
	        	}
	        }
	      }
	    });
	  }
	
	
}