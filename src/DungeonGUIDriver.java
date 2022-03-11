
import java.io.InputStreamReader;
import java.util.Random;

import dungeonmodel.DungeonConsoleController;
import dungeonmodel.DungeonImpl;
import dungeonmodel.DungeonViewImpl;
import dungeonmodel.GUIController;
import dungeonmodel.IDungeon;
import dungeonmodel.IDungeonView;
import dungeonmodel.IGUIController;

/**
 * Run a Dungeon game interactively on the console or run in gui if no input 
 * parameters is passed from console.
 * It replaces the original main. It creates model and controller,
 * It calls user to controller and passes model to it.
 */
public class DungeonGUIDriver {

  /**
   * Run a Dungeon game interactively on the console.
   */
  public static void main(String[] args) {
    IDungeon dungeon = null;
    Random rand = new Random(); //instance of random class
    int upperbound = 100;
    int seed = rand.nextInt(upperbound); 
    int noOfRows = 0;
    int noOfColumns = 0;
    int interConnectivity = 0;
    boolean isWrapping = false;
    int percentageOfTreasure = 0;
    int numberOfOtyughs = 0;
    if (args != null && args.length == 6) {
      String noOfRowsArg = args[0];
      String noOfColumnsArg = args[1];
      String interConnectivityArg = args[2];
      String isWrappingArg = args[3];
      String percentageOfTreasureArg = args[4];
      String numberOfOtyughArg = args[5];

      try {
        noOfRows = Integer.valueOf(noOfRowsArg);
        noOfColumns = Integer.valueOf(noOfColumnsArg);
        interConnectivity = Integer.valueOf(interConnectivityArg);
        isWrapping = Integer.valueOf(isWrappingArg).equals(1);
        percentageOfTreasure = Integer.valueOf(percentageOfTreasureArg);
        numberOfOtyughs = Integer.valueOf(numberOfOtyughArg);
        dungeon = new DungeonImpl(noOfRows, noOfColumns, interConnectivity,
            isWrapping, percentageOfTreasure, numberOfOtyughs, true, true, seed);
        Readable input = new InputStreamReader(System.in);
        Appendable output = System.out;
        new DungeonConsoleController(input, output).playGame(dungeon);
      } catch (NumberFormatException e) {
        System.out.println("The input should be a number");
      }
    } else {
      dungeon = new DungeonImpl(6, 6, 1,
          true, 40, 1, true, true, seed);
      IDungeonView view = new DungeonViewImpl("Dungeon Game", dungeon);
      IGUIController controller = new GUIController(dungeon, view);
      controller.playGame();
    } 
  }
}
