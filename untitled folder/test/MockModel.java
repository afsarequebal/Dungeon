import java.io.IOException;

import dungeonmodel.IDungeon;

/**
 * Mock model used for testing controller in isolation.
 * It returns constant values received in the parameters.
 * 
 */
public class MockModel implements IDungeon {

  private Appendable log;

  /**
   * Constructs a mock model object.
   * 
   * @param log represent output object to show result
   */
  public MockModel(Appendable log) {
    this.log = log;
  }

  @Override
  public String getPlayersTreasures() {
    try {
      log.append("get Player Treasue called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return "diamond";
  }

  @Override
  public int getPlayersLocation() {
    try {
      log.append("get Player Location called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return 1;
  }

  @Override
  public int getStartPoint() {
    try {
      log.append("get Player Location called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return 1;
  }

  @Override
  public int getEndPoint() {
    try {
      log.append("get End point called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return 1;
  }

  @Override
  public String getlocationDetails() {
    try {
      log.append("get location details called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return "";
  }

  @Override
  public String dungeonDescription() {
    try {
      log.append("get dungeon description called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return "";
  }

  @Override
  public String[][] dungeonRepresentation() {
    try {
      log.append("get dungeon representation called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return new String[1][1];
  }

  @Override
  public boolean isGameOver() {
    try {
      log.append("is game over called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return true;
  }

  @Override
  public boolean getWinner() {
    try {
      log.append("get winner called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return true;
  }

  @Override
  public String getPlayersArrows() {
    try {
      log.append("get player arrow called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return "arrow";
  }

  @Override
  public int getNoOfColumns() {
    try {
      log.append("get number of columns called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return 1;
  }

  @Override
  public int getNoOfRows() {
    try {
      log.append("get number of rows called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return 1;
  }

  @Override
  public int getInterconnectivity() {
    try {
      log.append("get interconnectivity called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return 1;
  }

  @Override
  public boolean isWrapping() {
    try {
      log.append("is wrapping called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return true;
  }

  @Override
  public int numberOfOtyughs() {
    try {
      log.append("get otyugh number called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return 1;
  }

  @Override
  public int percentageOfTreasure() {
    try {
      log.append("get treasure percent called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return 1;
  }

  @Override
  public int getSeed() {
    try {
      log.append("get seed called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return 1;
  }

  @Override
  public boolean isfallenIntoPit() {
    try {
      log.append("is falled into pit called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return true;
  }

  @Override
  public boolean isEncounteredTheif() {
    try {
      log.append("is encountered thief called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return true;
  }

  @Override
  public boolean isEncounteredRMonster() {
    try {
      log.append("is encountered r monster called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return true;
  }

  @Override
  public boolean move(String dir) {
    try {
      log.append("move called" + " " + dir + " " + "\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return true;
  }

  @Override
  public boolean move(int row, int col) {
    try {
      log.append("move called" + row + " " + col + "\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return true;
  }

  @Override
  public boolean pickUpItem(String pickUpItem) {
    try {
      log.append("pick up item called" + " " + pickUpItem + "\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return true;
  }

  @Override
  public int attack(String dir, int distance) {
    try {
      log.append("attack Monster called\n" + dir + " " + distance);
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return 1;
  }

  @Override
  public int fightMonster() {
    try {
      log.append("fight Monster called\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
    return 1;
  }


}
