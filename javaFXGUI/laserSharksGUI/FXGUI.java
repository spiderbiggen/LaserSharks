package laserSharksGUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FXGUI extends Application {

	Label lb_text;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		
		final Circle circle = createCircle();
		final Group group = new Group(circle);
		final Scene scene = new Scene(group, 800, 400, Color.BLUE);

		//Scene scene = new Scene(root);
		stage.setScene(scene);

		BackgroundImage myBI = new BackgroundImage(new Image("better background.jpg", 1920, 1080, false, true), 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		root.setBackground(new Background(myBI));
		
		MoveOnKeyPress.moveCircleOnKeyPress(scene, circle);

		stage.show();

	}
	
	/*
	 * This circle is for testing, in the end it will be the lasershark
	 */
	private Circle createCircle() {
		final Circle circle = new Circle(100, 75, 25, Color.ORANGE);
		circle.setOpacity(1);
		return circle;
	}
}
