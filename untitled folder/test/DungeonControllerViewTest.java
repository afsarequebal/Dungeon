import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dungeonmodel.DungeonImpl;
import dungeonmodel.GUIController;
import dungeonmodel.IDungeon;
import dungeonmodel.IDungeonView;

/**
 * Test cases for the dungeon game toe controller, 
 * using mocks for view and model.
 * 
 */
public class DungeonControllerViewTest {

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
        isWrapping, percentageOfTreasure, numberOfOtyugh, isRandom, true, 1);
  }

  protected IDungeon getDungeon(int noOfRows, int noOfColumns, int interConnectivity,
      boolean isWrapping, int percentageOfTreasure, int numberOfOtyugh, boolean isRandom,
      boolean playerWins) {
    IDungeon dungeonModel;
    dungeonModel = new DungeonImpl(noOfRows, noOfColumns, interConnectivity,
        isWrapping, percentageOfTreasure, numberOfOtyugh, isRandom, playerWins, 1);
    return dungeonModel;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = null;
    new GUIController(model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = null;

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);
    c.attack("N", 1);
  }

  @Test
  public void testAttackMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);
    c.attack("N", 1);

    assertEquals("attack Monster called\n"
        + "N 1show attack result called\n"
        + "1",log.toString());
  }

  @Test
  public void testFightMonsterMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);

    c.fightMonster();
    assertEquals("fight Monster called\n"
        + "show fight result called\n"
        + "1",log.toString());
  }

  @Test
  public void testMoveDirMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);

    c.move("N");
    assertEquals("move called N \n"
        + "refresh\n"
        + "",log.toString());
  }

  @Test
  public void testMoveRowColMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);

    c.move(0, 0);
    assertEquals("move called0 0\n"
        + "refresh\n",log.toString());
  }

  @Test
  public void testPickUpArrowMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);

    c.pickUp("arrow");
    assertEquals("pick up item called arrow\n"
        + "refresh\n",log.toString());
  }

  @Test
  public void testPickUpDiamondMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);

    c.pickUp("diamond");
    assertEquals("pick up item called diamond\n"
        + "refresh\n",log.toString());
  }

  @Test
  public void testPickUpEmeraldMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);

    c.pickUp("emerald");
    assertEquals("pick up item called emerald\n"
        + "refresh\n",log.toString());
  }

  @Test
  public void testPickUpRubyMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);

    c.pickUp("ruby");
    assertEquals("pick up item called ruby\n"
        + "refresh\n",log.toString());
  }

  @Test
  public void testNewGameMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);

    c.newGame(10, 10, 1, false, 1, 1);
    assertEquals("Model setting done\n"
        + "refresh\n",log.toString());
  }

  @Test
  public void testPlayGameMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);

    c.playGame();
    assertEquals("Features added\n"
        + "Make visible called\n",log.toString());
  }

  @Test
  public void testRestartGameMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);

    c.restartGame();
    assertEquals("get number of rows called\n"
        + "get number of columns called\n"
        + "get interconnectivity called\n"
        + "is wrapping called\n"
        + "get treasure percent called\n"
        + "get otyugh number called\n"
        + "Error message shown\n"
        + "Treasures are not distributed to all location.",log.toString());
  }

  @Test
  public void testSuccessfulReuseGameMockModelView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = new MockModel(log);
    GUIController c = new GUIController(model, view);

    c.reuseGame();
    assertEquals("get number of rows called\n"
        + "get number of columns called\n"
        + "get interconnectivity called\n"
        + "is wrapping called\n"
        + "get treasure percent called\n"
        + "get otyugh number called\n"
        + "get seed called\n"
        + "Error message shown\n"
        + "Treasures are not distributed to all location.",log.toString());
  }

  @Test
  public void testAttackMockView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = getDungeon(10, 10, 10, true, 10, 3, false, true);
    GUIController c = new GUIController(model, view);
    c.attack("N", 1);

    assertEquals("show attack result called\n0",log.toString());
  }

  @Test
  public void testMoveDirMockView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = getDungeon(10, 10, 10, true, 10, 3, false, true);
    GUIController c = new GUIController(model, view);

    c.move("N");
    assertEquals("refresh\n",log.toString());
  }

  @Test
  public void testMoveRowColMockView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = getDungeon(10, 10, 10, true, 10, 3, false, true);
    GUIController c = new GUIController(model, view);

    c.move(0, 0);
    assertEquals("refresh\n",log.toString());
  }

  @Test
  public void testPickUpMockView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = getDungeon(10, 10, 10, true, 10, 3, false, true);
    GUIController c = new GUIController(model, view);

    c.pickUp("arrow");
    assertEquals("refresh\n",log.toString());
  }

  @Test
  public void testNewGameMockView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = getDungeon(10, 10, 10, true, 10, 3, false, true);
    GUIController c = new GUIController(model, view);

    c.newGame(10, 10, 1, false, 1, 1);
    assertEquals("Model setting done\n"
        + "refresh\n",log.toString());
  }

  @Test
  public void testPlayGameMockView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = getDungeon(10, 10, 10, true, 10, 3, false, true);
    GUIController c = new GUIController(model, view);

    c.playGame();
    assertEquals("Features added\n"
        + "Make visible called\n",log.toString());
  }

  @Test
  public void testRestartGameMockView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = getDungeon(10, 10, 10, true, 10, 3, false, true);
    GUIController c = new GUIController(model, view);

    c.restartGame();
    assertEquals("Model setting done\n"
        + "refresh\n",log.toString());
  }

  @Test
  public void testSuccessfulReuseGameMockView() {
    StringBuilder log = new StringBuilder();
    IDungeonView view = new MockView(log);

    IDungeon model = getDungeon(10, 10, 10, true, 10, 3, false, true);
    GUIController c = new GUIController(model, view);

    c.reuseGame();
    assertEquals("Model setting done\n"
        + "refresh\n",log.toString());
  }
}
