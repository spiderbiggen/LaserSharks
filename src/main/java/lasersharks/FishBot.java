package lasersharks;

public class FishBot implements Fish{

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

  @Override
  public boolean collision(Fish fish) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean move() {
      return position.updatePosition(direction, speed);
  }
  
  public static FishBot RandomFish() {      
    //TODO make a fishbot object that is random generated what could be used to represent the random fishes appearing on the screen
    return null;
  }
  

}
