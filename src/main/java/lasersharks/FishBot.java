package lasersharks;

public class FishBot implements Fish{

  Position position;
  float size;
  float speed;

  public FishBot (Position pos, float siz, float sp) {
    position = pos;
    size = siz;
    speed = sp;
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

  @Override
  public boolean collision(Fish fish) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean move() {
    // TODO Auto-generated method stub
    return false;
  }
  
  public static FishBot RandomFish() {
    //TODO make a fishbot object that is random generated what could be used to represent the random fishes appearing on the screen
    return null;
  }
  

}
