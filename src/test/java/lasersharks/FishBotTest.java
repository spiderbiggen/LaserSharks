package lasersharks;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FishBotTest {
  
  FishBot fishbot1;
  FishBot fishbot2;
  FishBot generatedFish;
  Position posOnScreen;
  Position posOffScreen;

  @Before
  public void setUp() throws Exception {
    posOnScreen = new Position(50,50);
    posOffScreen = new Position(-1,-1);
    fishbot1 = new FishBot(posOnScreen,30,40,Direction.East);
    fishbot2 = new FishBot(posOffScreen,30,40,Direction.East);
  }

  @After
  public void tearDown() throws Exception {
    
  }

  @Test
  public void testFishBot() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetPosition() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetSize() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetSpeed() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetDirection() {
    fail("Not yet implemented");
  }

  @Test
  public void testCollision() {
    fail("Not yet implemented");
  }

  @Test
  public void testMove() {
    int oldX = fishbot1.getPosition().getPosX();
    Direction direction = fishbot1.getDirection();
    fishbot1.move();
    int newX = fishbot1.getPosition().getPosX();
    assertEquals(oldX+fishbot1.getSpeed(), newX, 0);
    
  }

  @Test
  public void testRandomFish() {
    generatedFish = FishBot.RandomFish();
    System.out.println(generatedFish.toString());
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
