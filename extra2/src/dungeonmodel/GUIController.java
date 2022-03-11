package dungeonmodel;

import java.util.Random;

/**
 * It represents a  
 *
 */
public class GUIController implements IGUIController, Features {

  private IDungeonView view;
  private IDungeon model;

  public GUIController(IDungeon model, IDungeonView view) {
    if (null == model || null == view)  {
      throw new IllegalArgumentException("Arguments can't be null.");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void playGame() {
    //view.addClickListener(this);
    //
    // this.view = view;
    // give the feature callbacks to the view
    // view.addClickListener(this);
    view.setFeatures(this);
    view.makeVisible();
  }


  @Override
  public void processInput(String input) {
    // TODO Auto-generated method stub

  }

  @Override
  public void move(int row, int col) {
    try { 
      model.move(row, col);
      view.refresh();
      //out.append("\n");
      //moveDone = true;
      //break;
    } catch (Exception e) {
      view.printError(e.getMessage() + ". Try again\n");
    }

  }
  
  @Override
  public void move(String dir) {
    try { 
      model.move(dir);
      view.refresh();
      //out.append("\n");
      //moveDone = true;
      //break;
    } catch (Exception e) {
      view.printError(e.getMessage() + ". Try again\n");
    }

  }
  
  @Override
  public boolean pickUp(String item) {
    try { 
      model.pickUpItem(item);
      view.refresh();
      return true;
      //out.append("\n");
      //moveDone = true;
      //break;
    } catch (Exception e) {
      view.printError(e.getMessage() + ". Try again\n");
      return false;
    }

  }

  /* @Override
  public void handleCellClick(int row, int col) {
    try {
      model.move(row, col);
      view.refresh();
    } catch (IllegalArgumentException | IllegalStateException e) {

    }
  }*/
  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public boolean newGame(int noOfRows, int noOfColumns, int interConnectivity, boolean isWrapping,
      int percentageOfTreasure, int numberOfOtyughs) {
    boolean success = false;
    try {
      Random rand = new Random(); 
      int upperbound = 100;
      int seed = rand.nextInt(upperbound); 
      model = new DungeonImpl(noOfRows, noOfColumns, interConnectivity,
          isWrapping, percentageOfTreasure, numberOfOtyughs, true, true, seed);
      view.makeInVisible();
      view = new DungeonViewImpl("Dungeon Game", model);
      playGame();
      success = true;
    } catch (Exception e) {
      view.showErrorMessage(e.getMessage());
    }
    return success;
  }

  @Override
  public boolean restartGame() {
    boolean success = false;
    try {
      model = new DungeonImpl(model.getNoOfRows(), model.getNoOfColumns(), model.getInterconnectivity(),
          model.isWrapping(), model.percentageOfTreasure(), model.numberOfOtyughs(), true, true, model.getSeed());
      view.makeInVisible();
      view = new DungeonViewImpl("Dungeon Game", model);
      playGame();
      success = true;
    } catch (Exception e) {
      view.showErrorMessage(e.getMessage());
    }
    return success;
  }

  @Override
  public int attack(String shootDir, int distancerow) {
    try {
      return model.attack(shootDir, distancerow);
    } catch (IllegalArgumentException e) {
      view.printResult(e.getMessage());
      return -1;
    }
  }

  @Override
  public int fightMonster() {
    int ret = model.fightMonster();
    return ret;
  }
}
