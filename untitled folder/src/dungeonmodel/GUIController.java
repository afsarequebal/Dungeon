package dungeonmodel;

import java.util.Random;

/**
 * Controller class is used by user for interaction. It gets the 
 * user input from screen which includes move, attack, pickup treasure.
 * After each call, controller asks model to get the do action.
 * It also returns values to be shown in screen.
 * 
 */
public class GUIController implements IGUIController, Features {

  private IDungeonView view;
  private IDungeon model;

  /**
   * Constructs a gui controller to process commands from the screen and 
   * return the result.
   * 
   * @param model represents model to do the move, pickup, attack task.
   * @param view represents the view to show the maze to the user and
   *     allow various actions.
   */
  public GUIController(IDungeon model, IDungeonView view) {
    if (null == model || null == view)  {
      throw new IllegalArgumentException("Arguments can't be null.");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void playGame() {
    view.setFeatures(this);
    view.makeVisible();
  }

  @Override
  public void move(int row, int col) {
    try { 
      model.move(row, col);
      view.refresh();
    } catch (Exception e) {
      view.refresh();
    }
  }

  @Override
  public void move(String dir) {
    try { 
      model.move(dir);
      view.refresh();
    } catch (Exception e) {
      view.refresh();
    }
  }

  @Override
  public void pickUp(String item) {
    try { 
      model.pickUpItem(item);
      view.refresh();
    } catch (Exception e) {
      view.refresh();
    }
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public boolean newGame(int noOfRows, int noOfColumns, int interConnectivity, boolean isWrapping,
      int percentageOfTreasure, int numberOfOtyughs) {
    try {
      Random rand = new Random(); 
      int upperbound = 100;
      int seed = rand.nextInt(upperbound); 
      model = new DungeonImpl(noOfRows, noOfColumns, interConnectivity,
          isWrapping, percentageOfTreasure, numberOfOtyughs, true, true, seed);
      view.setModel(model);
      view.refresh();
      return true;
    } catch (Exception e) {
      view.showErrorMessage("Dungeon cannot be drawn with given input. Please retry.");
      view.makeOptionFrameVisible();
      return false;
    }
  }

  @Override
  public void restartGame() {
    try {
      Random rand = new Random(); //instance of random class
      int upperbound = 100;
      int seed = rand.nextInt(upperbound); 
      model = new DungeonImpl(model.getNoOfRows(), 
          model.getNoOfColumns(), model.getInterconnectivity(),
          model.isWrapping(), model.percentageOfTreasure(), 
          model.numberOfOtyughs(), true, true, seed);
      view.setModel(model);
      view.refresh();
    } catch (Exception e) {
      view.showErrorMessage(e.getMessage());
    }
  }

  @Override
  public void attack(String shootDir, int distancerow) {
    try {
      int s = model.attack(shootDir, distancerow);
      view.showAttackResult(s);
    } catch (Exception e) {
      view.printResult(e.getMessage());
    }
  }

  @Override
  public void fightMonster() {
    int ret = model.fightMonster();
    view.showFightResult(ret);
  }

  @Override
  public boolean reuseGame() {
    boolean success = false;
    try {
      model = new DungeonImpl(model.getNoOfRows(), 
          model.getNoOfColumns(), model.getInterconnectivity(),
          model.isWrapping(), model.percentageOfTreasure(), 
          model.numberOfOtyughs(), true, true, model.getSeed());
      view.setModel(model);
      view.refresh();
      success = true;
    } catch (Exception e) {
      view.showErrorMessage(e.getMessage());
    }
    return success;
  }
}
