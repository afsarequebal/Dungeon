import java.io.InputStreamReader;

import dungeonmodel.DungeonConsoleController;
import dungeonmodel.DungeonImpl;
import dungeonmodel.IDungeon;

/**
 * Run a Dungeon game interactively on the console.
 */
public class DungeonConsoleDriver {

  /**
   * Run a Dungeon game interactively on the console.
   */
  public static void main(String[] args) {
    if (args == null || args.length != 6) {
      System.out.println("The command line input is not correct.\nA valid"
          + " input will be have values including noOfRows, noOfColumsn,"
          + "interconnectivity, isWrapping, percentageOfTreasure, noOfOtyugh.\nAn examples is"
          + "10 10 0 0 20 1\n");
      return;
    }
    String noOfRowsArg = args[0];
    String noOfColumnsArg = args[1];
    String interConnectivityArg = args[2];
    String isWrappingArg = args[3];

    String percentageOfTreasureArg = args[4];
    String numberOfOtyughArg = args[5];
    int noOfRows = 0;
    int noOfColumns = 0;
    int interConnectivity = 0;
    boolean isWrapping = false;
    int percentageOfTreasure = 0;
    int numberOfOtyughs = 0;

    try {
      noOfRows = Integer.valueOf(noOfRowsArg);
      noOfColumns = Integer.valueOf(noOfColumnsArg);
      interConnectivity = Integer.valueOf(interConnectivityArg);
      isWrapping = Integer.valueOf(isWrappingArg).equals(1);
      percentageOfTreasure = Integer.valueOf(percentageOfTreasureArg);
      numberOfOtyughs = Integer.valueOf(numberOfOtyughArg);

    } catch (NumberFormatException e) {
      System.out.println("The input should be a number");
    }

    IDungeon dungeon = new DungeonImpl(noOfRows, noOfColumns, interConnectivity,
        isWrapping, percentageOfTreasure, numberOfOtyughs, true, true);
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    new DungeonConsoleController(input, output).playGame(dungeon);
  }
}
