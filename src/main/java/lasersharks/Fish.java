package lasersharks;

/**
 * Interface Fish.
 */
public interface Fish {
  
  public abstract Position getPosition();

  public abstract float getSize();

  public abstract boolean collision(Fish fish);

  public abstract boolean move();
}
