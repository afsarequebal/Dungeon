import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import dungeonmodel.DungeonConsoleController;
import dungeonmodel.DungeonImpl;
import dungeonmodel.IDungeon;

/**
 * Test cases for the Dungeon controller, using mocks for readable and
 * appendable.
 */
public class DungeonControllerTest {

  IDungeon dungeonNonRandom;
  
  @Before
  public void setUp() throws Exception {
    int noOfRows = 5;
    int noOfColumns = 5;
    int interConnectivity = 0;
    interConnectivity = 0;
    boolean isWrapping = false;
    boolean isRandom = false;
    int percentageOfTreasure = 20;
    int numberOfOtyugh = 1;
    dungeonNonRandom = new DungeonImpl(noOfRows, noOfColumns, interConnectivity,
        isWrapping, percentageOfTreasure, numberOfOtyugh, isRandom, true);
  }
  
  protected IDungeon getDungeon(int noOfRows, int noOfColumns, int interConnectivity,
      boolean isWrapping, int percentageOfTreasure, int numberOfOtyugh, boolean isRandom,
      boolean playerWins) {
    IDungeon dungeonModel;
    dungeonModel = new DungeonImpl(noOfRows, noOfColumns, interConnectivity,
        isWrapping, percentageOfTreasure, numberOfOtyugh, isRandom, playerWins);
    return dungeonModel;
  }
  
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    int noOfRows = 5;
    int noOfColumns = 5;
    int interConnectivity = 0;
    interConnectivity = 0;
    boolean isWrapping = false;
    boolean isRandom = false;
    int percentageOfTreasure = 20;
    int numberOfOtyugh = 1;
    IDungeon m = new DungeonImpl(noOfRows, noOfColumns, interConnectivity,
        isWrapping, percentageOfTreasure, numberOfOtyugh, isRandom, true);
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    DungeonConsoleController c = new DungeonConsoleController(input, gameLog);
    c.playGame(m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    DungeonImpl m = null;
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    DungeonConsoleController c = new DungeonConsoleController(input, gameLog);
    c.playGame(m);
  }

 @Test
  public void testPlayerReachEnd() {
   IDungeon nonWrappingNonRandomDungeon = getDungeon(10, 10, 10, true, 10, 3, false, true);
    StringReader input = new StringReader("M E M E M E M E S 1 N S 1 N M N");
    Appendable gameLog = new StringBuilder();
    DungeonConsoleController d = new DungeonConsoleController(input, gameLog);
    d.playGame(nonWrappingNonRandomDungeon);
    String expected =  "Start point : 1\n"
        + "End point : 95\n"
        + "{0(E, W, N, S, ) & (DIAMOND, DIAMOND, DIAMOND, )  & (Arrow, ) & (Otyugh, )} {1(W, E, N, S, ) & (DIAMOND, DIAMOND, DIAMOND, )  & (Arrow, ) & ()} {2(W, E, N, S, ) & (DIAMOND, DIAMOND, DIAMOND, )  & (Arrow, ) & ()} {3(W, E, N, S, ) & (DIAMOND, SAPPHIRE, SAPPHIRE, )  & (Arrow, ) & ()} {4(W, E, N, S, ) & (SAPPHIRE, SAPPHIRE, SAPPHIRE, )  & (Arrow, ) & ()} {5(W, E, N, S, ) & (SAPPHIRE, SAPPHIRE, SAPPHIRE, )  & (Arrow, ) & ()} {6(W, E, N, S, ) & (SAPPHIRE, SAPPHIRE, RUBY, )  & (Arrow, ) & ()} {7(W, E, N, S, ) & (RUBY, RUBY, RUBY, )  & (Arrow, ) & ()} {8(W, N, S, E, ) & (RUBY, RUBY, RUBY, )  & (Arrow, ) & ()} {9(E, N, S, W, ) & (RUBY, RUBY, RUBY, )  & (Arrow, ) & ()} \n"
        + "{10(N, S, E, W, ) & ()  & () & ()} {11(N, S, W, E, ) & ()  & () & ()} {12(N, S, W, E, ) & ()  & () & ()} {13(N, S, W, E, ) & ()  & () & ()} {14(N, S, W, E, ) & ()  & () & ()} {15(N, S, W, E, ) & ()  & () & ()} {16(N, S, W, E, ) & ()  & () & ()} {17(N, S, W, E, ) & ()  & () & ()} {18(N, S, W, ) & ()  & () & ()} {19(N, S, E, ) & ()  & () & ()} \n"
        + "{20(N, S, ) & ()  & () & ()} {21(N, S, ) & ()  & () & ()} {22(N, S, ) & ()  & () & ()} {23(N, S, ) & ()  & () & ()} {24(N, S, ) & ()  & () & ()} {25(N, S, ) & ()  & () & ()} {26(N, S, ) & ()  & () & ()} {27(N, S, ) & ()  & () & ()} {28(N, S, ) & ()  & () & ()} {29(N, S, ) & ()  & () & ()} \n"
        + "{30(N, S, ) & ()  & () & ()} {31(N, S, ) & ()  & () & ()} {32(N, S, ) & ()  & () & ()} {33(N, S, ) & ()  & () & ()} {34(N, S, ) & ()  & () & ()} {35(N, S, ) & ()  & () & ()} {36(N, S, ) & ()  & () & ()} {37(N, S, ) & ()  & () & ()} {38(N, S, ) & ()  & () & ()} {39(N, S, ) & ()  & () & ()} \n"
        + "{40(N, S, ) & ()  & () & ()} {41(N, S, ) & ()  & () & ()} {42(N, S, ) & ()  & () & ()} {43(N, S, ) & ()  & () & ()} {44(N, S, ) & ()  & () & ()} {45(N, S, ) & ()  & () & ()} {46(N, S, ) & ()  & () & ()} {47(N, S, ) & ()  & () & ()} {48(N, S, ) & ()  & () & ()} {49(N, S, ) & ()  & () & ()} \n"
        + "{50(N, S, ) & ()  & () & ()} {51(N, S, ) & ()  & () & ()} {52(N, S, ) & ()  & () & ()} {53(N, S, ) & ()  & () & ()} {54(N, S, ) & ()  & () & ()} {55(N, S, ) & ()  & () & ()} {56(N, S, ) & ()  & () & ()} {57(N, S, ) & ()  & () & ()} {58(N, S, ) & ()  & () & ()} {59(N, S, ) & ()  & () & ()} \n"
        + "{60(N, S, ) & ()  & () & ()} {61(N, S, ) & ()  & () & ()} {62(N, S, ) & ()  & () & ()} {63(N, S, ) & ()  & () & ()} {64(N, S, ) & ()  & () & ()} {65(N, S, ) & ()  & () & ()} {66(N, S, ) & ()  & () & ()} {67(N, S, ) & ()  & () & ()} {68(N, S, ) & ()  & () & ()} {69(N, S, ) & ()  & () & ()} \n"
        + "{70(N, S, ) & ()  & () & ()} {71(N, S, ) & ()  & () & ()} {72(N, S, ) & ()  & () & ()} {73(N, S, ) & ()  & () & ()} {74(N, S, ) & ()  & () & ()} {75(N, S, ) & ()  & () & ()} {76(N, S, ) & ()  & () & ()} {77(N, S, ) & ()  & () & ()} {78(N, S, ) & ()  & () & ()} {79(N, S, ) & ()  & () & ()} \n"
        + "{80(N, ) & ()  & () & ()} {81(N, ) & ()  & () & ()} {82(N, ) & ()  & () & ()} {83(N, ) & ()  & () & ()} {84(N, ) & ()  & () & ()} {85(N, ) & ()  & () & ()} {86(N, ) & ()  & () & ()} {87(N, ) & ()  & () & ()} {88(N, ) & ()  & () & ()} {89(N, ) & ()  & () & ()} \n"
        + "{90(S, ) & ()  & () & ()} {91(S, ) & ()  & () & ()} {92(S, ) & ()  & () & ()} {93(S, ) & ()  & () & ()} {94(S, ) & ()  & () & ()} {95(S, ) & ()  & () & (Otyugh, )} {96(S, ) & ()  & () & ()} {97(S, ) & ()  & () & ()} {98(S, ) & ()  & () & ()} {99(S, ) & ()  & () & ()} \n"
        + "You have Arrow, Arrow, Arrow\n"
        + "You smell monster in nearby cell\n"
        + "You are in a cave\n"
        + "Doors lead to the W,  E,  N,  S\n"
        + "You find DIAMOND, DIAMOND, DIAMOND here\n"
        + "You find 1 arrow here\n"
        + "Move, Pickup, or Shoot (M-P-S)? \n"
        + "Where to ?\n"
        + "\n"
        + "\n"
        + "You have Arrow, Arrow, Arrow\n"
        + "You found faint smell of monster\n"
        + "You are in a cave\n"
        + "Doors lead to the W,  E,  N,  S\n"
        + "You find DIAMOND, DIAMOND, DIAMOND here\n"
        + "You find 1 arrow here\n"
        + "Move, Pickup, or Shoot (M-P-S)? \n"
        + "Where to ?\n"
        + "\n"
        + "\n"
        + "You have Arrow, Arrow, Arrow\n"
        + "You are in a cave\n"
        + "Doors lead to the W,  E,  N,  S\n"
        + "You find DIAMOND, SAPPHIRE, SAPPHIRE here\n"
        + "You find 1 arrow here\n"
        + "Move, Pickup, or Shoot (M-P-S)? \n"
        + "Where to ?\n"
        + "\n"
        + "\n"
        + "You have Arrow, Arrow, Arrow\n"
        + "You found faint smell of monster\n"
        + "You are in a cave\n"
        + "Doors lead to the W,  E,  N,  S\n"
        + "You find SAPPHIRE, SAPPHIRE, SAPPHIRE here\n"
        + "You find 1 arrow here\n"
        + "Move, Pickup, or Shoot (M-P-S)? \n"
        + "Where to ?\n"
        + "\n"
        + "\n"
        + "You have Arrow, Arrow, Arrow\n"
        + "You smell monster in nearby cell\n"
        + "You are in a cave\n"
        + "Doors lead to the W,  E,  N,  S\n"
        + "You find SAPPHIRE, SAPPHIRE, SAPPHIRE here\n"
        + "You find 1 arrow here\n"
        + "Move, Pickup, or Shoot (M-P-S)? \n"
        + "No. of caves (1-5)?\n"
        + "Where to?\n"
        + "You hear a slight howl\n"
        + "\n"
        + "You have Arrow, Arrow\n"
        + "You smell monster in nearby cell\n"
        + "You are in a cave\n"
        + "Doors lead to the W,  E,  N,  S\n"
        + "You find SAPPHIRE, SAPPHIRE, SAPPHIRE here\n"
        + "You find 1 arrow here\n"
        + "Move, Pickup, or Shoot (M-P-S)? \n"
        + "No. of caves (1-5)?\n"
        + "Where to?\n"
        + "You hear a great howl, seems monster is dead.\n"
        + "\n"
        + "You have Arrow\n"
        + "You are in a cave\n"
        + "Doors lead to the W,  E,  N,  S\n"
        + "You find SAPPHIRE, SAPPHIRE, SAPPHIRE here\n"
        + "You find 1 arrow here\n"
        + "Move, Pickup, or Shoot (M-P-S)? \n"
        + "Where to ?\n"
        + "\n"
        + "\n"
        + "End location reached\n"
        + "Game is over! Player wins.";
    assertEquals(expected, gameLog.toString());
  }
  
 @Test
 public void testPlayerReachEnd1() {
  IDungeon nonWrappingNonRandomDungeon = getDungeon(10, 10, 10, true, 10, 3, false, true);
   StringReader input = new StringReader("M E M E M E M E S 1 N S 1 N M N");
   Appendable gameLog = new StringBuilder();
   DungeonConsoleController d = new DungeonConsoleController(input, gameLog);
   d.playGame(nonWrappingNonRandomDungeon);
   String expected =  "You have Arrow, Arrow, Arrow\n"
       + "You smell monster in nearby cell\n"
       + "You are in a cave\n"
       + "Doors lead to the W,  E,  N,  S\n"
       + "You find DIAMOND, DIAMOND, DIAMOND here\n"
       + "You find 1 arrow here\n"
       + "Move, Pickup, or Shoot (M-P-S)? \n"
       + "Where to ?\n"
       + "\n"
       + "\n"
       + "You have Arrow, Arrow, Arrow\n"
       + "You found faint smell of monster\n"
       + "You are in a cave\n"
       + "Doors lead to the W,  E,  N,  S\n"
       + "You find DIAMOND, DIAMOND, DIAMOND here\n"
       + "You find 1 arrow here\n"
       + "Move, Pickup, or Shoot (M-P-S)? \n"
       + "Where to ?\n"
       + "\n"
       + "\n"
       + "You have Arrow, Arrow, Arrow\n"
       + "You are in a cave\n"
       + "Doors lead to the W,  E,  N,  S\n"
       + "You find DIAMOND, SAPPHIRE, SAPPHIRE here\n"
       + "You find 1 arrow here\n"
       + "Move, Pickup, or Shoot (M-P-S)? \n"
       + "Where to ?\n"
       + "\n"
       + "\n"
       + "You have Arrow, Arrow, Arrow\n"
       + "You found faint smell of monster\n"
       + "You are in a cave\n"
       + "Doors lead to the W,  E,  N,  S\n"
       + "You find SAPPHIRE, SAPPHIRE, SAPPHIRE here\n"
       + "You find 1 arrow here\n"
       + "Move, Pickup, or Shoot (M-P-S)? \n"
       + "Where to ?\n"
       + "\n"
       + "\n"
       + "You have Arrow, Arrow, Arrow\n"
       + "You smell monster in nearby cell\n"
       + "You are in a cave\n"
       + "Doors lead to the W,  E,  N,  S\n"
       + "You find SAPPHIRE, SAPPHIRE, SAPPHIRE here\n"
       + "You find 1 arrow here\n"
       + "Move, Pickup, or Shoot (M-P-S)? \n"
       + "No. of caves (1-5)?\n"
       + "Where to?\n"
       + "You hear a slight howl\n"
       + "\n"
       + "You have Arrow, Arrow\n"
       + "You smell monster in nearby cell\n"
       + "You are in a cave\n"
       + "Doors lead to the W,  E,  N,  S\n"
       + "You find SAPPHIRE, SAPPHIRE, SAPPHIRE here\n"
       + "You find 1 arrow here\n"
       + "Move, Pickup, or Shoot (M-P-S)? \n"
       + "No. of caves (1-5)?\n"
       + "Where to?\n"
       + "You hear a great howl, seems monster is dead.\n"
       + "\n"
       + "You have Arrow\n"
       + "You are in a cave\n"
       + "Doors lead to the W,  E,  N,  S\n"
       + "You find SAPPHIRE, SAPPHIRE, SAPPHIRE here\n"
       + "You find 1 arrow here\n"
       + "Move, Pickup, or Shoot (M-P-S)? \n"
       + "Where to ?\n"
       + "\n"
       + "\n"
       + "End location reached\n"
       + "Game is over! Player wins.";
   assertEquals(expected, gameLog.toString());
 }
 
 /* @Test
  public void testInvalidRowInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("asdf q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected =  "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "Not a valid number: asdf\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testInvalidColumnInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 asdf q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected =  "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "Not a valid number: asdf\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testOutOfBoundRowInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("10 1 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected =  "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "Not a valid move: 10, 1\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testOutOfBoundColumnsInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 10 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected =  "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "Not a valid move: 1, 10\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testQForARowInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected =  "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testQForAColInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected =  "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testValidMove() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected = "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testMoveToOccupiedSpace() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 2 2 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected = "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + "Not a valid move: 2, 2\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testMoveAfterInvalidMove() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 2 2 1 1 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected = "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + "Not a valid move: 2, 2\n"
        + " O |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "Game quit! Ending game state:\n"
        + " O |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testPlayerXWinning() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 1 2 3 3 3 2");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected = "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + " O |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + " O | X |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + " O | X |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   | O\n"
        + "Enter a move for X:\n"
        + " O | X |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   | X | O\n"
        + "Game is over! X wins.";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testPlayerOWinning() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 1 2 3 3 3 1 1 3 2 1 2 3");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected = "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + " O |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + " O | X |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + " O | X |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   | O\n"
        + "Enter a move for X:\n"
        + " O | X |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + " X |   | O\n"
        + "Enter a move for O:\n"
        + " O | X | O\n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + " X |   | O\n"
        + "Enter a move for X:\n"
        + " O | X | O\n"
        + "-----------\n"
        + " X | X |  \n"
        + "-----------\n"
        + " X |   | O\n"
        + "Enter a move for O:\n"
        + " O | X | O\n"
        + "-----------\n"
        + " X | X | O\n"
        + "-----------\n"
        + " X |   | O\n"
        + "Game is over! O wins.";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 3 2 1 2 3 3 2 3 2 1 2 2 1 3 3 1 2 1");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String expected = "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + " X |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + " X |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | O |  \n"
        + "Enter a move for X:\n"
        + " X | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | O |  \n"
        + "Enter a move for O:\n"
        + " X | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | O | O\n"
        + "Enter a move for X:\n"
        + " X | X |  \n"
        + "-----------\n"
        + "   |   | X\n"
        + "-----------\n"
        + "   | O | O\n"
        + "Enter a move for O:\n"
        + " X | X |  \n"
        + "-----------\n"
        + " O |   | X\n"
        + "-----------\n"
        + "   | O | O\n"
        + "Enter a move for X:\n"
        + " X | X |  \n"
        + "-----------\n"
        + " O | X | X\n"
        + "-----------\n"
        + "   | O | O\n"
        + "Enter a move for O:\n"
        + " X | X | O\n"
        + "-----------\n"
        + " O | X | X\n"
        + "-----------\n"
        + "   | O | O\n"
        + "Enter a move for X:\n"
        + " X | X | O\n"
        + "-----------\n"
        + " O | X | X\n"
        + "-----------\n"
        + " X | O | O\n"
        + "Game is over! Tie game.";
    assertEquals(expected, gameLog.toString());
  }*/
}


