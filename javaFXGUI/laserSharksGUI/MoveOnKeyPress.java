package laserSharksGUI;

/**
 * This class is for moving around the lasershark when pressing a button
 * 
 */
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;

public class MoveOnKeyPress {
	
	private static final int KEYBOARD_MOVEMENT_DELTA = 5;
	
	/**
	 * Method to move the lasershark around when pressing the up, right, down or left button.
	 * @param scene
	 * @param circle
	 */
	static void moveCircleOnKeyPress(Scene scene, final Circle circle) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					circle.setCenterY(circle.getCenterY()
							- KEYBOARD_MOVEMENT_DELTA);
					break;
				case RIGHT:
					circle.setCenterX(circle.getCenterX()
							+ KEYBOARD_MOVEMENT_DELTA);
					break;
				case DOWN:
					circle.setCenterY(circle.getCenterY()
							+ KEYBOARD_MOVEMENT_DELTA);
					break;
				case LEFT:
					circle.setCenterX(circle.getCenterX()
							- KEYBOARD_MOVEMENT_DELTA);
					break;
				}
			}
		});
	}

}
