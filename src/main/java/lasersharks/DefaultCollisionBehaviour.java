package lasersharks;

public class DefaultCollisionBehaviour implements CollisionBehaviour {

  Swimmer swimmer;
  
  @Override
  public boolean collide(Swimmer swimmer) {
    float distance = this.swimmer.getMiddlePoint().calculateDistance(swimmer.getMiddlePoint());
    return distance < this.swimmer.getSize() + swimmer.getSize();
  }

  
}
