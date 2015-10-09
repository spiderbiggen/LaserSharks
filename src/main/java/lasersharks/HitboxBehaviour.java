package lasersharks;

import javafx.scene.shape.Rectangle;

public interface HitboxBehaviour {

  public Position getMiddlePoint();
  
  public Rectangle makeHitbox();
}
