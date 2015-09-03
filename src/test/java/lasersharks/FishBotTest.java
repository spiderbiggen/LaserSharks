package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;

public class FishBotTest {
  
  FishBot fishbot1;
  FishBot fishbot2;
  FishBot generatedFish;
  Position posOnScreen;
  Position posOffScreen;

  @Before
  public void setUp() throws Exception {
    posOnScreen = new Position(50, 50);
    posOffScreen = new Position(-1, -1);
    fishbot1 = new FishBot(posOnScreen, 30, 40, Direction.East);
    fishbot2 = new FishBot(posOffScreen, 30, 40, Direction.West);
    generatedFish = FishBot.generateFish();
  }

  @Test
  public void testGetPosition() {
    assertEquals(posOnScreen,fishbot1.getPosition());
  }

  @Test
  public void testGetSize() {
    assertEquals(fishbot1.getSize(),30,0);
  }

  @Test
  public void testGetSpeed() {
    assertEquals(fishbot1.getSpeed(),40,0);
  }

  @Test
  public void testGetDirection() {
    assertEquals(fishbot1.getDirection(), Direction.East);
  }

  @Test
  public void testCollisionFalse() {
    assertFalse(fishbot1.collision(fishbot2));
  }

  @Test
  public void testCollisionTrue() {
    FishBot fishbot3 = fishbot1;
    assertFalse(fishbot3.collision(fishbot2));
    fishbot3.move();
    assert(fishbot3.collision(fishbot2));
  }
  
  @Test
  public void testMove() {
    int oldX = fishbot1.getPosition().getPosX();
    fishbot1.move();
    int newX = fishbot1.getPosition().getPosX();
    assertEquals(oldX+fishbot1.getSpeed(), newX, 0);
    
  }

  @Test
  public void testRandomFish() {
    System.out.println(generatedFish.toString());
    assert(generatedFish.getSize()>=0);
    assert(generatedFish.getSize()<=100);
  }

  @Test
  public void testIsOnScreen() {
    assert(fishbot1.isOnScreen());
  }
  
  @Test
  public void testIsOffScreen() {
    assertFalse(fishbot2.isOnScreen());
  }


}
