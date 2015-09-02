package lasersharks;

/**
 * Interface Fish.
 */
public interface Fish {
  
  protected Direction direction;

  public abstract Position getPosition();

  public abstract float getSize();

  public abstract boolean collision(Fish fish);

  public abstract boolean move();
}
