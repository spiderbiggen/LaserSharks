package lasersharks;

import java.sql.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Level {
  FishController fishCon;
  LaserShark shark;
  ScreenController screenCon;
  Boolean running;
  List<Fish> currentFish;
  Timer timer;
  TimerTask task;
  Date date;
  int score;
  
  private static int startSize = 20;
  private static long tickTime = 1000;
  
  /**
   * this is the constructor of the level class.
   */
  public Level() {
    fishCon = new FishController();
    shark = new LaserShark(Position.middlePosition(), Direction.East, startSize);
    running = false;
    fishCon.addFish(shark);
    timer = new Timer();
  }
  
  /**
   * calling this method causes the timertask to start.
   */
  public void start() {
    running = true;
    
    timer.schedule(new TimerTask() {      
      @Override
      public void run() {
        gameLoop();       
      }
    }, new Date(0), tickTime);
  }
  
  /**
   * calling this method causes the timerTask to stop.
   */
  public void stop() {
    running = false;
    //TODO: stop the ticker
  }
  
  /**
   * this function is run at each tick.
   * It includes checking for collisions, 
   */
  private void gameLoop() {
    currentFish = fishCon.getNextCycleInformation();
    screenCon.projectFish(currentFish);
    
    for(int i=0;i<currentFish.size();i++){
      Fish fish = currentFish.get(i);
      if(shark.collision(fish) && !shark.equals(fish)){
        if(shark.getSize() < fish.getSize()) {
          shark.eat(fish);
        } else {
          //TODO: implement that the game ends because the fish is larger than the shark
        }
      }
    }
  }
}
