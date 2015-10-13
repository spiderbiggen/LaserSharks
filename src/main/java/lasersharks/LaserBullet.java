package lasersharks;

public class LaserBullet extends Fish {

  private static final String LASER_IMAGE = "";
  private static final double LASER_DEFAULT_STRENGTH = 1.0;
  
  public LaserBullet(Position position, float size, double startSpeed, Direction direction) {
    super(position, size, startSpeed, direction);
    collisionBehaviour = new DefaultCollisionBehaviour(this);
    eatBehaviour = new CantEatBehaviour(this);
    moveBehaviour = new BotMoveBehaviour(this);
  }

  @Override
  public String getImageResource() {
    return LASER_IMAGE;
  }

  @Override
  public double getWidthScale() {
    // TODO Auto-generated method stub
    return 0;
  }
  
}
