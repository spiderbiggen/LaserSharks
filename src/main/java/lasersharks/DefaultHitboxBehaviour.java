package lasersharks;

import javafx.scene.shape.Rectangle;

public class DefaultHitboxBehaviour implements HitboxBehaviour {


  private static final double WIDTH_TO_COORD = 0.85;
  private static final double SIZE_TO_COORD = 0.075;
  
  Swimmer swimmer;  
  
  public DefaultHitboxBehaviour(Swimmer swimmer) {
    this.swimmer = swimmer;
  }
  
  @Override
  public Position getMiddlePoint() {
    // TODO Auto-generated method stub
    return null;
  }


  /**
   * Draw a rectangle shaped hitbox around the fishbot.
   * 
   * @return a rectangle hitbox.
   */
  public Rectangle makeHitbox() {
    double xcoordinate = swimmer.getPosition().getPosX();
    double ycoordinate = swimmer.getPosition().getPosY();
    Rectangle rekt = new Rectangle(xcoordinate + SIZE_TO_COORD * swimmer.getSize(),
        ycoordinate + SIZE_TO_COORD * swimmer.getSize(),
        swimmer.getWidthScale() * this.getSize() * WIDTH_TO_COORD, this.getSize() * WIDTH_TO_COORD);
    return rekt;
  }

}
