package lasersharks;

/**
 * This class represent the fishes on the screen that are not player controllable.
 * @author sytze
 *
 */
public class FishBot implements Fish {

  /**
   * This value is used to modify the speed of the fishes that are generated.
   * The generated speed is equal to SpeedModifier*RandomNumber, 
   * where RandomNumber is a random int between 0 and 100.
   */
  static int SpeedModifier = 30;
  /**
   * This value is used to modify the size of the fishes that are generated.
   * The generated speed is equal to SizeModifier*RandomNumber, 
   * where RandomNumber is a random int between 0 and 100.
   */
  static int SizeModifier = 40;
  
  /**
   * This value represents the chance for fish to spawn on the left side of the screen.
   */
  static int ChanceForLeft = 50;
  
  Position position;
  float size;
  int speed;
  Direction direction;

  public FishBot (Position pos, float siz, int sp, Direction dir) {
    position = pos;
    size = siz;
    speed = sp;
    direction = dir;
  }
  
  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public float getSize() {
    return size;
  }
  
  public float getSpeed() {
    return speed;
  }
  
  public Direction getDirection() {
    return direction;
  }

  /**
   * We calculate the distance between the fishes. 
   * The sum of the size of both fishes is our hitbox.
   * Hitbox is now a circle, with size the radius in pixels.
   */
  @Override
  public boolean collision(Fish fish) {
    if (position.calculateDistance(fish.getPosition()) < size + fish.getSize()) {
      return true;
    }
    return false;
  }

  /**
   * moves the fishbot using the direction and speed values of this object.
   */
  @Override
  public boolean move() {
      return position.updatePosition(direction, speed);
  }
  
  /**
   * This function creates a new FishBot with random values.
   * This should be used to spawn fishes.
   * Starts on either the left side on 
   * @return a random fish with random speed, size and position. 
   */
  public static FishBot RandomFish() {    
    Position pos;
    float si;
    int sp;
    Direction dir;
    
    int posY = (int) (Position.HeightPanel * Math.random());
    int posX;
    if (Math.random() > ChanceForLeft) {
      //starts on the right side
      dir = Direction.East;
      posX = Position.WidthPanel;
    } else {
      //starts on the left side
      posX = 0;
      dir = Direction.West;
    }
    pos = new Position(posX, posY);
    si = (int) Math.random() * SizeModifier;
    sp = (int) Math.random() * SpeedModifier;
    
    return new FishBot(pos, si, sp, dir);
  }
  
  /**
   * this function checks if the fish is on the screen or not.
   * @return true if the fish is on the screen, and false if not.
   */
  public final boolean isOnScreen() {
    return position.isOnScreen();
  }
}