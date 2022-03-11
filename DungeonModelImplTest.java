

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import dungeonmodel.DungeonImpl;
import dungeonmodel.IDungeon;

/**
 * Class to test the functionality of the dungeon. It tests 
 * creating dungeon, habitat, habitant, treasure. It test the 
 * ability of the habitant to move from one location to another.
 * It also ability of the player to pick up treasures.
 *
 */
public class DungeonModelImplTest {

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

  /**
   * It test a dungeon direction available in non wrapping dungeon.
   */
  @Test
  public void testNonWrappingDungeonByDirection() {
    IDungeon nonWrappingNonRandomDungeon = getDungeon(10, 10, 0, false, 10, 1, false, true);
    assertEquals("{0(R, D, ) & () } {1(L, R, D, ) & (DIAM0, DIAM1, DIAM2, ) } "
        + "{2(L, R, D, ) & (DIAM3, DIAM4, DIAM5, ) } {3(L, R, D, ) & (DIAM6, DIAM7, DIAM8, ) } "
        + "{4(L, R, D, ) & (DIAM9, SAP0, SAP1, ) } {5(L, R, D, ) & (SAP2, SAP3, SAP4, ) } "
        + "{6(L, R, D, ) & (SAP5, SAP6, SAP7, ) } {7(L, R, D, ) & (SAP8, SAP9, RBS0, ) } "
        + "{8(L, R, D, ) & (RBS1, RBS2, RBS3, ) } {9(L, D, ) & () } \n" 
        + "{10(U, D, ) & () } {11(U, D, ) & () } {12(U, D, ) & () }"
        + " {13(U, D, ) & () } {14(U, D, ) & () } {15(U, D, ) & () } "
        + "{16(U, D, ) & () } {17(U, D, ) & () } {18(U, D, ) & () } {19(U, D, ) & () } \n" 
        + "{20(U, D, ) & () } {21(U, D, ) & () } {22(U, D, ) & () } "
        + "{23(U, D, ) & () } {24(U, D, ) & () } {25(U, D, ) & () } "
        + "{26(U, D, ) & () } {27(U, D, ) & () } {28(U, D, ) & () } {29(U, D, ) & () } \n" 
        + "{30(U, D, ) & () } {31(U, D, ) & () } {32(U, D, ) & () } "
        + "{33(U, D, ) & () } {34(U, D, ) & () } {35(U, D, ) & () }"
        + " {36(U, D, ) & () } {37(U, D, ) & () } {38(U, D, ) & () } "
        + "{39(U, D, ) & () } \n"
        + "{40(U, D, ) & () } {41(U, D, ) & () } {42(U, D, ) & () } "
        + "{43(U, D, ) & () } {44(U, D, ) & () } {45(U, D, ) & () }"
        + " {46(U, D, ) & () } {47(U, D, ) & () } {48(U, D, ) & () } {49(U, D, ) & () } \n"
        + "{50(U, D, ) & () } {51(U, D, ) & () } {52(U, D, ) & () } "
        + "{53(U, D, ) & () } {54(U, D, ) & () } {55(U, D, ) & () } "
        + "{56(U, D, ) & () } {57(U, D, ) & () } {58(U, D, ) & () } {59(U, D, ) & () } \n"
        + "{60(U, D, ) & () } {61(U, D, ) & () } {62(U, D, ) & () } "
        + "{63(U, D, ) & () } {64(U, D, ) & () } {65(U, D, ) & () } "
        + "{66(U, D, ) & () } {67(U, D, ) & () } {68(U, D, ) & () } {69(U, D, ) & () } \n"
        + "{70(U, D, ) & () } {71(U, D, ) & () } {72(U, D, ) & () }"
        + " {73(U, D, ) & () } {74(U, D, ) & () } {75(U, D, ) & () } "
        + "{76(U, D, ) & () } {77(U, D, ) & () } {78(U, D, ) & () } "
        + "{79(U, D, ) & () } \n"
        + "{80(U, D, ) & () } {81(U, D, ) & () } {82(U, D, ) & () } "
        + "{83(U, D, ) & () } {84(U, D, ) & () } {85(U, D, ) & () } "
        + "{86(U, D, ) & () } {87(U, D, ) & () } {88(U, D, ) & () }"
        + " {89(U, D, ) & () } \n"
        + "{90(U, ) & (RBS4, RBS5, RBS6, ) } {91(U, ) & (RBS7, RBS8, RBS9, ) }"
        + " {92(U, ) & () } {93(U, ) & () } {94(U, ) & () } {95(U, ) & () } "
        + "{96(U, ) & () } {97(U, ) & () } {98(U, ) & () } {99(U, ) & () } \n",
        nonWrappingNonRandomDungeon.dungeonDescription());
  }

  /**
   * It test a dungeon direction available in a wrapping dungeon.
   */
  @Test
  public void testWrappingDungeonByDirection() {
    IDungeon wrappingNonRandomDungeon = getDungeon(10, 10, 0, true, 20, 1, false, true);
    assertEquals("{0(R, L, U, D, ) & (DIAM0, DIAM1, DIAM2, ) } "
        + "{1(L, R, U, D, ) & (DIAM3, DIAM4, DIAM5, ) } "
        + "{2(L, R, U, D, ) & (DIAM6, DIAM7, DIAM8, ) } "
        + "{3(L, R, U, D, ) & (DIAM9, DIAM10, DIAM11, ) }"
        + " {4(L, R, U, D, ) & (DIAM12, DIAM13, DIAM14, ) } "
        + "{5(L, R, U, D, ) & (DIAM15, DIAM16, DIAM17, ) } "
        + "{6(L, R, U, D, ) & (DIAM18, DIAM19, SAP0, ) } "
        + "{7(L, R, U, D, ) & (SAP1, SAP2, SAP3, ) } "
        + "{8(L, U, D, ) & (SAP4, SAP5, SAP6, ) } "
        + "{9(R, U, D, ) & (SAP7, SAP8, SAP9, ) } \n" 
        + "{10(U, D, ) & () } {11(U, D, ) & () } {12(U, D, ) & () }"
        + " {13(U, D, ) & () } {14(U, D, ) & () } {15(U, D, ) & () }"
        + " {16(U, D, ) & () } {17(U, D, ) & () } {18(U, D, ) & () } "
        + "{19(U, D, ) & () } \n" 
        + "{20(U, D, ) & () } {21(U, D, ) & () } {22(U, D, ) & () } "
        + "{23(U, D, ) & () } {24(U, D, ) & () } {25(U, D, ) & () } "
        + "{26(U, D, ) & () } {27(U, D, ) & () } {28(U, D, ) & () } "
        + "{29(U, D, ) & () } \n"
        + "{30(U, D, ) & () } {31(U, D, ) & () } {32(U, D, ) & () } "
        + "{33(U, D, ) & () } {34(U, D, ) & () } {35(U, D, ) & () } "
        + "{36(U, D, ) & () } {37(U, D, ) & () } {38(U, D, ) & () } "
        + "{39(U, D, ) & () } \n" 
        + "{40(U, D, ) & () } {41(U, D, ) & () } {42(U, D, ) & () } "
        + "{43(U, D, ) & () } {44(U, D, ) & () } {45(U, D, ) & () } "
        + "{46(U, D, ) & () } {47(U, D, ) & () } {48(U, D, ) & () } "
        + "{49(U, D, ) & () } \n" 
        + "{50(U, D, ) & () } {51(U, D, ) & () } {52(U, D, ) & () } "
        + "{53(U, D, ) & () } {54(U, D, ) & () } {55(U, D, ) & () } "
        + "{56(U, D, ) & () } {57(U, D, ) & () } {58(U, D, ) & () } {59(U, D, ) & () } \n" 
        + "{60(U, D, ) & () } {61(U, D, ) & () } {62(U, D, ) & () }"
        + " {63(U, D, ) & () } {64(U, D, ) & () } {65(U, D, ) & () }"
        + " {66(U, D, ) & () } {67(U, D, ) & () } {68(U, D, ) & () } {69(U, D, ) & () } \n" 
        + "{70(U, D, ) & () } {71(U, D, ) & () } {72(U, D, ) & () } "
        + "{73(U, D, ) & () } {74(U, D, ) & () } {75(U, D, ) & () } "
        + "{76(U, D, ) & () } {77(U, D, ) & () } {78(U, D, ) & () } "
        + "{79(U, D, ) & () } \n" 
        + "{80(U, ) & (SAP10, SAP11, SAP12, ) } {81(U, ) & (SAP13, SAP14, SAP15, ) } "
        + "{82(U, ) & (SAP16, SAP17, SAP18, ) } {83(U, ) & (SAP19, RBS0, RBS1, ) }"
        + " {84(U, ) & (RBS2, RBS3, RBS4, ) } {85(U, ) & (RBS5, RBS6, RBS7, ) } "
        + "{86(U, ) & (RBS8, RBS9, RBS10, ) } {87(U, ) & (RBS11, RBS12, RBS13, ) } "
        + "{88(U, ) & (RBS14, RBS15, RBS16, ) } {89(U, ) & (RBS17, RBS18, RBS19, ) } \n" 
        + "{90(D, ) & () } {91(D, ) & () } {92(D, ) & () } {93(D, ) & () } "
        + "{94(D, ) & () } {95(D, ) & () } {96(D, ) & () } {97(D, ) & () } {98(D, ) & () }"
        + " {99(D, ) & () } \n" ,wrappingNonRandomDungeon.dungeonDescription());
  }

  /**
   * It test a movement allowed in non wrapping dungeon.
   */
  @Test
  public void testNonWrappingDungeonBymove() {
    IDungeon wrappingNonRandomDungeon = getDungeon(10, 10, 0, true, 10, 1, false, true);
    assertEquals(1, wrappingNonRandomDungeon.getPlayersLocation());
    wrappingNonRandomDungeon.move("U");
    assertEquals(91, wrappingNonRandomDungeon.getPlayersLocation());
  }

  /**
   * It test a movement allowed in wrapping dungeon.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWrappingDungeonBymove() {
    IDungeon wrappingNonRandomDungeon = getDungeon(10, 10, 0, false, 10, 1, false, true);
    assertEquals(1, wrappingNonRandomDungeon.getPlayersLocation());
    wrappingNonRandomDungeon.move("U");
  }

  /**
   * It test that treasures allocated to cells is atleast given
   * percent of treasure for non-wrapping dungeon.
   * 
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWrongPercentOfTreasureNonWrapping() {
    new DungeonImpl(10, 10, 0, false, 100, 1, false, true);
  }

  /**
   * It test that treasures allocated to cells is atleast given
   * percent of treasure for wrapping dungeon.
   * 
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWrongPercentOfTreasureWrapping() {
    new DungeonImpl(10, 10, 0, true, 100, 1, false, true);
  }


  /**
   * It test percentage of treasure in non-wrapping dungeon
   * should be atleast the given number of percentage.
   * 
   */
  @Test
  public void testTreasureinNonWrappingDungeon() {
    IDungeon nonWrappingNonRandomDungeon = getDungeon(10, 10, 0, false, 10, 1, false, true);
    assertEquals("{0(R, D, ) & () } {1(L, R, D, ) & (DIAM0, DIAM1, DIAM2, ) } "
        + "{2(L, R, D, ) & (DIAM3, DIAM4, DIAM5, ) } {3(L, R, D, ) & (DIAM6, DIAM7, DIAM8, ) } "
        + "{4(L, R, D, ) & (DIAM9, SAP0, SAP1, ) } {5(L, R, D, ) & (SAP2, SAP3, SAP4, ) } "
        + "{6(L, R, D, ) & (SAP5, SAP6, SAP7, ) } {7(L, R, D, ) & (SAP8, SAP9, RBS0, ) } "
        + "{8(L, R, D, ) & (RBS1, RBS2, RBS3, ) } {9(L, D, ) & () } \n"  
        + "{10(U, D, ) & () } {11(U, D, ) & () } {12(U, D, ) & () }"
        + " {13(U, D, ) & () } {14(U, D, ) & () } {15(U, D, ) & () } "
        + "{16(U, D, ) & () } {17(U, D, ) & () } {18(U, D, ) & () } {19(U, D, ) & () } \n" 
        + "{20(U, D, ) & () } {21(U, D, ) & () } {22(U, D, ) & () } "
        + "{23(U, D, ) & () } {24(U, D, ) & () } {25(U, D, ) & () } {26(U, D, ) & () } "
        + "{27(U, D, ) & () } {28(U, D, ) & () } {29(U, D, ) & () } \n" 
        + "{30(U, D, ) & () } {31(U, D, ) & () } {32(U, D, ) & () } "
        + "{33(U, D, ) & () } {34(U, D, ) & () } {35(U, D, ) & () }"
        + " {36(U, D, ) & () } {37(U, D, ) & () } {38(U, D, ) & () } "
        + "{39(U, D, ) & () } \n" 
        + "{40(U, D, ) & () } {41(U, D, ) & () } {42(U, D, ) & () } "
        + "{43(U, D, ) & () } {44(U, D, ) & () } {45(U, D, ) & () }"
        + " {46(U, D, ) & () } {47(U, D, ) & () } {48(U, D, ) & () } {49(U, D, ) & () } \n" 
        + "{50(U, D, ) & () } {51(U, D, ) & () } {52(U, D, ) & () } "
        + "{53(U, D, ) & () } {54(U, D, ) & () } {55(U, D, ) & () } "
        + "{56(U, D, ) & () } {57(U, D, ) & () } {58(U, D, ) & () } {59(U, D, ) & () } \n" 
        + "{60(U, D, ) & () } {61(U, D, ) & () } {62(U, D, ) & () } "
        + "{63(U, D, ) & () } {64(U, D, ) & () } {65(U, D, ) & () } "
        + "{66(U, D, ) & () } {67(U, D, ) & () } {68(U, D, ) & () } {69(U, D, ) & () } \n" 
        + "{70(U, D, ) & () } {71(U, D, ) & () } {72(U, D, ) & () }"
        + " {73(U, D, ) & () } {74(U, D, ) & () } {75(U, D, ) & () } "
        + "{76(U, D, ) & () } {77(U, D, ) & () } {78(U, D, ) & () } "
        + "{79(U, D, ) & () } \n" 
        + "{80(U, D, ) & () } {81(U, D, ) & () } {82(U, D, ) & () } "
        + "{83(U, D, ) & () } {84(U, D, ) & () } {85(U, D, ) & () } "
        + "{86(U, D, ) & () } {87(U, D, ) & () } {88(U, D, ) & () }"
        + " {89(U, D, ) & () } \n" 
        + "{90(U, ) & (RBS4, RBS5, RBS6, ) } {91(U, ) & (RBS7, RBS8, RBS9, ) }"
        + " {92(U, ) & () } {93(U, ) & () } {94(U, ) & () } {95(U, ) & () } "
        + "{96(U, ) & () } {97(U, ) & () } {98(U, ) & () } {99(U, ) & () } \n",
        nonWrappingNonRandomDungeon.dungeonDescription());
  }

  /**
   * It test percentage of treasure in wrapping dungeon
   * should be atleast the given number of percentage.
   * 
   */
  @Test
  public void testTreasureWrappingDungeon() {
    IDungeon wrappingNonRandomDungeon = getDungeon(10, 10, 0, true, 20, 1, false, true);
    assertEquals("{0(R, L, U, D, ) & (DIAM0, DIAM1, DIAM2, ) } "
        + "{1(L, R, U, D, ) & (DIAM3, DIAM4, DIAM5, ) } "
        + "{2(L, R, U, D, ) & (DIAM6, DIAM7, DIAM8, ) } "
        + "{3(L, R, U, D, ) & (DIAM9, DIAM10, DIAM11, ) }"
        + " {4(L, R, U, D, ) & (DIAM12, DIAM13, DIAM14, ) } "
        + "{5(L, R, U, D, ) & (DIAM15, DIAM16, DIAM17, ) } "
        + "{6(L, R, U, D, ) & (DIAM18, DIAM19, SAP0, ) } "
        + "{7(L, R, U, D, ) & (SAP1, SAP2, SAP3, ) } "
        + "{8(L, U, D, ) & (SAP4, SAP5, SAP6, ) } "
        + "{9(R, U, D, ) & (SAP7, SAP8, SAP9, ) } \n" 
        + "{10(U, D, ) & () } {11(U, D, ) & () } {12(U, D, ) & () }"
        + " {13(U, D, ) & () } {14(U, D, ) & () } {15(U, D, ) & () }"
        + " {16(U, D, ) & () } {17(U, D, ) & () } {18(U, D, ) & () } "
        + "{19(U, D, ) & () } \n" 
        + "{20(U, D, ) & () } {21(U, D, ) & () } {22(U, D, ) & () } "
        + "{23(U, D, ) & () } {24(U, D, ) & () } {25(U, D, ) & () } "
        + "{26(U, D, ) & () } {27(U, D, ) & () } {28(U, D, ) & () } "
        + "{29(U, D, ) & () } \n" 
        + "{30(U, D, ) & () } {31(U, D, ) & () } {32(U, D, ) & () } "
        + "{33(U, D, ) & () } {34(U, D, ) & () } {35(U, D, ) & () } "
        + "{36(U, D, ) & () } {37(U, D, ) & () } {38(U, D, ) & () } "
        + "{39(U, D, ) & () } \n" 
        + "{40(U, D, ) & () } {41(U, D, ) & () } {42(U, D, ) & () } "
        + "{43(U, D, ) & () } {44(U, D, ) & () } {45(U, D, ) & () } "
        + "{46(U, D, ) & () } {47(U, D, ) & () } {48(U, D, ) & () } "
        + "{49(U, D, ) & () } \n" 
        + "{50(U, D, ) & () } {51(U, D, ) & () } {52(U, D, ) & () } "
        + "{53(U, D, ) & () } {54(U, D, ) & () } {55(U, D, ) & () } "
        + "{56(U, D, ) & () } {57(U, D, ) & () } {58(U, D, ) & () } {59(U, D, ) & () } \n" 
        + "{60(U, D, ) & () } {61(U, D, ) & () } {62(U, D, ) & () }"
        + " {63(U, D, ) & () } {64(U, D, ) & () } {65(U, D, ) & () }"
        + " {66(U, D, ) & () } {67(U, D, ) & () } {68(U, D, ) & () } {69(U, D, ) & () } \n" 
        + "{70(U, D, ) & () } {71(U, D, ) & () } {72(U, D, ) & () } "
        + "{73(U, D, ) & () } {74(U, D, ) & () } {75(U, D, ) & () } "
        + "{76(U, D, ) & () } {77(U, D, ) & () } {78(U, D, ) & () } "
        + "{79(U, D, ) & () } \n" 
        + "{80(U, ) & (SAP10, SAP11, SAP12, ) } {81(U, ) & (SAP13, SAP14, SAP15, ) } "
        + "{82(U, ) & (SAP16, SAP17, SAP18, ) } {83(U, ) & (SAP19, RBS0, RBS1, ) }"
        + " {84(U, ) & (RBS2, RBS3, RBS4, ) } {85(U, ) & (RBS5, RBS6, RBS7, ) } "
        + "{86(U, ) & (RBS8, RBS9, RBS10, ) } {87(U, ) & (RBS11, RBS12, RBS13, ) } "
        + "{88(U, ) & (RBS14, RBS15, RBS16, ) } {89(U, ) & (RBS17, RBS18, RBS19, ) } \n" 
        + "{90(D, ) & () } {91(D, ) & () } {92(D, ) & () } {93(D, ) & () } "
        + "{94(D, ) & () } {95(D, ) & () } {96(D, ) & () } {97(D, ) & () } {98(D, ) & () }"
        + " {99(D, ) & () } \n" ,wrappingNonRandomDungeon.dungeonDescription());
  }

  /**
   * It test that all locations with two directions are tunnel and 
   * cannot hold a treasure in wrapping dungeon.
   * Also location with directions not equal to two are caves and
   * possess treasure.
   * 
   */
  @Test
  public void testCavesAndTunnelsinNonWrappingDungeon() {
    IDungeon nonWrappingNonRandomDungeon = getDungeon(10, 10, 0, false, 10, 1, false, true);
    assertEquals("{0(R, D, ) & () } {1(L, R, D, ) & (DIAM0, DIAM1, DIAM2, ) } "
        + "{2(L, R, D, ) & (DIAM3, DIAM4, DIAM5, ) } {3(L, R, D, ) & (DIAM6, DIAM7, DIAM8, ) } "
        + "{4(L, R, D, ) & (DIAM9, SAP0, SAP1, ) } {5(L, R, D, ) & (SAP2, SAP3, SAP4, ) } "
        + "{6(L, R, D, ) & (SAP5, SAP6, SAP7, ) } {7(L, R, D, ) & (SAP8, SAP9, RBS0, ) } "
        + "{8(L, R, D, ) & (RBS1, RBS2, RBS3, ) } {9(L, D, ) & () } \n" 
        + "{10(U, D, ) & () } {11(U, D, ) & () } {12(U, D, ) & () }"
        + " {13(U, D, ) & () } {14(U, D, ) & () } {15(U, D, ) & () } "
        + "{16(U, D, ) & () } {17(U, D, ) & () } {18(U, D, ) & () } {19(U, D, ) & () } \n" 
        + "{20(U, D, ) & () } {21(U, D, ) & () } {22(U, D, ) & () } "
        + "{23(U, D, ) & () } {24(U, D, ) & () } {25(U, D, ) & () } {26(U, D, ) & () }"
        + " {27(U, D, ) & () } {28(U, D, ) & () } {29(U, D, ) & () } \n" 
        + "{30(U, D, ) & () } {31(U, D, ) & () } {32(U, D, ) & () } "
        + "{33(U, D, ) & () } {34(U, D, ) & () } {35(U, D, ) & () }"
        + " {36(U, D, ) & () } {37(U, D, ) & () } {38(U, D, ) & () } "
        + "{39(U, D, ) & () } \n" 
        + "{40(U, D, ) & () } {41(U, D, ) & () } {42(U, D, ) & () } "
        + "{43(U, D, ) & () } {44(U, D, ) & () } {45(U, D, ) & () }"
        + " {46(U, D, ) & () } {47(U, D, ) & () } {48(U, D, ) & () } {49(U, D, ) & () } \n" 
        + "{50(U, D, ) & () } {51(U, D, ) & () } {52(U, D, ) & () } "
        + "{53(U, D, ) & () } {54(U, D, ) & () } {55(U, D, ) & () } "
        + "{56(U, D, ) & () } {57(U, D, ) & () } {58(U, D, ) & () } {59(U, D, ) & () } \n" 
        + "{60(U, D, ) & () } {61(U, D, ) & () } {62(U, D, ) & () } "
        + "{63(U, D, ) & () } {64(U, D, ) & () } {65(U, D, ) & () } "
        + "{66(U, D, ) & () } {67(U, D, ) & () } {68(U, D, ) & () } {69(U, D, ) & () } \n" 
        + "{70(U, D, ) & () } {71(U, D, ) & () } {72(U, D, ) & () }"
        + " {73(U, D, ) & () } {74(U, D, ) & () } {75(U, D, ) & () } "
        + "{76(U, D, ) & () } {77(U, D, ) & () } {78(U, D, ) & () } "
        + "{79(U, D, ) & () } \n" 
        + "{80(U, D, ) & () } {81(U, D, ) & () } {82(U, D, ) & () } "
        + "{83(U, D, ) & () } {84(U, D, ) & () } {85(U, D, ) & () } "
        + "{86(U, D, ) & () } {87(U, D, ) & () } {88(U, D, ) & () }"
        + " {89(U, D, ) & () } \n" 
        + "{90(U, ) & (RBS4, RBS5, RBS6, ) } {91(U, ) & (RBS7, RBS8, RBS9, ) }"
        + " {92(U, ) & () } {93(U, ) & () } {94(U, ) & () } {95(U, ) & () } "
        + "{96(U, ) & () } {97(U, ) & () } {98(U, ) & () } {99(U, ) & () } \n",
        nonWrappingNonRandomDungeon.dungeonDescription());
  }

  /**
   * It test that all locations with two directions are tunnel and 
   * cannot hold a treasure in non-wrapping dungeon.
   * Also location with directions not equal to two are caves and
   * possess treasure.
   * 
   */
  @Test
  public void testCavesAndTunnelsWrappingDungeon() {
    IDungeon wrappingNonRandomDungeon = getDungeon(10, 10, 0, true, 20, 1, false, true);
    assertEquals("{0(R, L, U, D, ) & (DIAM0, DIAM1, DIAM2, ) } "
        + "{1(L, R, U, D, ) & (DIAM3, DIAM4, DIAM5, ) } "
        + "{2(L, R, U, D, ) & (DIAM6, DIAM7, DIAM8, ) } "
        + "{3(L, R, U, D, ) & (DIAM9, DIAM10, DIAM11, ) }"
        + " {4(L, R, U, D, ) & (DIAM12, DIAM13, DIAM14, ) } "
        + "{5(L, R, U, D, ) & (DIAM15, DIAM16, DIAM17, ) } "
        + "{6(L, R, U, D, ) & (DIAM18, DIAM19, SAP0, ) } "
        + "{7(L, R, U, D, ) & (SAP1, SAP2, SAP3, ) } "
        + "{8(L, U, D, ) & (SAP4, SAP5, SAP6, ) } "
        + "{9(R, U, D, ) & (SAP7, SAP8, SAP9, ) } \n" 
        + "{10(U, D, ) & () } {11(U, D, ) & () } {12(U, D, ) & () }"
        + " {13(U, D, ) & () } {14(U, D, ) & () } {15(U, D, ) & () }"
        + " {16(U, D, ) & () } {17(U, D, ) & () } {18(U, D, ) & () } "
        + "{19(U, D, ) & () } \n" 
        + "{20(U, D, ) & () } {21(U, D, ) & () } {22(U, D, ) & () } "
        + "{23(U, D, ) & () } {24(U, D, ) & () } {25(U, D, ) & () } "
        + "{26(U, D, ) & () } {27(U, D, ) & () } {28(U, D, ) & () } "
        + "{29(U, D, ) & () } \n" 
        + "{30(U, D, ) & () } {31(U, D, ) & () } {32(U, D, ) & () } "
        + "{33(U, D, ) & () } {34(U, D, ) & () } {35(U, D, ) & () } "
        + "{36(U, D, ) & () } {37(U, D, ) & () } {38(U, D, ) & () } "
        + "{39(U, D, ) & () } \n" 
        + "{40(U, D, ) & () } {41(U, D, ) & () } {42(U, D, ) & () } "
        + "{43(U, D, ) & () } {44(U, D, ) & () } {45(U, D, ) & () } "
        + "{46(U, D, ) & () } {47(U, D, ) & () } {48(U, D, ) & () } "
        + "{49(U, D, ) & () } \n" 
        + "{50(U, D, ) & () } {51(U, D, ) & () } {52(U, D, ) & () } "
        + "{53(U, D, ) & () } {54(U, D, ) & () } {55(U, D, ) & () } "
        + "{56(U, D, ) & () } {57(U, D, ) & () } {58(U, D, ) & () } {59(U, D, ) & () } \n" 
        + "{60(U, D, ) & () } {61(U, D, ) & () } {62(U, D, ) & () }"
        + " {63(U, D, ) & () } {64(U, D, ) & () } {65(U, D, ) & () }"
        + " {66(U, D, ) & () } {67(U, D, ) & () } {68(U, D, ) & () } {69(U, D, ) & () } \n" 
        + "{70(U, D, ) & () } {71(U, D, ) & () } {72(U, D, ) & () } "
        + "{73(U, D, ) & () } {74(U, D, ) & () } {75(U, D, ) & () } "
        + "{76(U, D, ) & () } {77(U, D, ) & () } {78(U, D, ) & () } "
        + "{79(U, D, ) & () } \n" 
        + "{80(U, ) & (SAP10, SAP11, SAP12, ) } {81(U, ) & (SAP13, SAP14, SAP15, ) } "
        + "{82(U, ) & (SAP16, SAP17, SAP18, ) } {83(U, ) & (SAP19, RBS0, RBS1, ) }"
        + " {84(U, ) & (RBS2, RBS3, RBS4, ) } {85(U, ) & (RBS5, RBS6, RBS7, ) } "
        + "{86(U, ) & (RBS8, RBS9, RBS10, ) } {87(U, ) & (RBS11, RBS12, RBS13, ) } "
        + "{88(U, ) & (RBS14, RBS15, RBS16, ) } {89(U, ) & (RBS17, RBS18, RBS19, ) } \n" 
        + "{90(D, ) & () } {91(D, ) & () } {92(D, ) & () } {93(D, ) & () } "
        + "{94(D, ) & () } {95(D, ) & () } {96(D, ) & () } {97(D, ) & () } {98(D, ) & () }"
        + " {99(D, ) & () } \n" ,wrappingNonRandomDungeon.dungeonDescription());
  }

  /**
   * It test that all types of treasure including 
   * diamond, rubies, sapphire are present in non 
   * wrapping dungeon.
   * 
   */
  @Test
  public void testTreasureTypeinNonWrappingDungeon() {
    IDungeon nonWrappingNonRandomDungeon = getDungeon(10, 10, 0, false, 10, 1, false, true);
    assertEquals("{0(R, D, ) & () } {1(L, R, D, ) & (DIAM0, DIAM1, DIAM2, ) } "
        + "{2(L, R, D, ) & (DIAM3, DIAM4, DIAM5, ) } {3(L, R, D, ) & (DIAM6, DIAM7, DIAM8, ) } "
        + "{4(L, R, D, ) & (DIAM9, SAP0, SAP1, ) } {5(L, R, D, ) & (SAP2, SAP3, SAP4, ) } "
        + "{6(L, R, D, ) & (SAP5, SAP6, SAP7, ) } {7(L, R, D, ) & (SAP8, SAP9, RBS0, ) } "
        + "{8(L, R, D, ) & (RBS1, RBS2, RBS3, ) } {9(L, D, ) & () } \n" 
        + "{10(U, D, ) & () } {11(U, D, ) & () } {12(U, D, ) & () }"
        + " {13(U, D, ) & () } {14(U, D, ) & () } {15(U, D, ) & () } "
        + "{16(U, D, ) & () } {17(U, D, ) & () } {18(U, D, ) & () } {19(U, D, ) & () } \n" 
        + "{20(U, D, ) & () } {21(U, D, ) & () } {22(U, D, ) & () } "
        + "{23(U, D, ) & () } {24(U, D, ) & () } {25(U, D, ) & () } "
        + "{26(U, D, ) & () } {27(U, D, ) & () } {28(U, D, ) & () } {29(U, D, ) & () } \n" 
        + "{30(U, D, ) & () } {31(U, D, ) & () } {32(U, D, ) & () } "
        + "{33(U, D, ) & () } {34(U, D, ) & () } {35(U, D, ) & () }"
        + " {36(U, D, ) & () } {37(U, D, ) & () } {38(U, D, ) & () } "
        + "{39(U, D, ) & () } \n" 
        + "{40(U, D, ) & () } {41(U, D, ) & () } {42(U, D, ) & () } "
        + "{43(U, D, ) & () } {44(U, D, ) & () } {45(U, D, ) & () }"
        + " {46(U, D, ) & () } {47(U, D, ) & () } {48(U, D, ) & () } {49(U, D, ) & () } \n" 
        + "{50(U, D, ) & () } {51(U, D, ) & () } {52(U, D, ) & () } "
        + "{53(U, D, ) & () } {54(U, D, ) & () } {55(U, D, ) & () } "
        + "{56(U, D, ) & () } {57(U, D, ) & () } {58(U, D, ) & () } {59(U, D, ) & () } \n" 
        + "{60(U, D, ) & () } {61(U, D, ) & () } {62(U, D, ) & () } "
        + "{63(U, D, ) & () } {64(U, D, ) & () } {65(U, D, ) & () } "
        + "{66(U, D, ) & () } {67(U, D, ) & () } {68(U, D, ) & () } {69(U, D, ) & () } \n" 
        + "{70(U, D, ) & () } {71(U, D, ) & () } {72(U, D, ) & () }"
        + " {73(U, D, ) & () } {74(U, D, ) & () } {75(U, D, ) & () } "
        + "{76(U, D, ) & () } {77(U, D, ) & () } {78(U, D, ) & () } "
        + "{79(U, D, ) & () } \n" 
        + "{80(U, D, ) & () } {81(U, D, ) & () } {82(U, D, ) & () } "
        + "{83(U, D, ) & () } {84(U, D, ) & () } {85(U, D, ) & () } "
        + "{86(U, D, ) & () } {87(U, D, ) & () } {88(U, D, ) & () }"
        + " {89(U, D, ) & () } \n" 
        + "{90(U, ) & (RBS4, RBS5, RBS6, ) } {91(U, ) & (RBS7, RBS8, RBS9, ) }"
        + " {92(U, ) & () } {93(U, ) & () } {94(U, ) & () } {95(U, ) & () } "
        + "{96(U, ) & () } {97(U, ) & () } {98(U, ) & () } {99(U, ) & () } \n",
        nonWrappingNonRandomDungeon.dungeonDescription());
  }

  /**
   * It test that all types of treasure including 
   * diamond, rubies, sapphire are present in  
   * wrapping dungeon.
   * 
   */
  @Test
  public void testTreasureTypeInWrappingDungeon() {
    IDungeon wrappingNonRandomDungeon = getDungeon(10, 10, 0, true, 20, 1, false, true);
    assertEquals("{0(R, L, U, D, ) & (DIAM0, DIAM1, DIAM2, ) } "
        + "{1(L, R, U, D, ) & (DIAM3, DIAM4, DIAM5, ) } "
        + "{2(L, R, U, D, ) & (DIAM6, DIAM7, DIAM8, ) } "
        + "{3(L, R, U, D, ) & (DIAM9, DIAM10, DIAM11, ) }"
        + " {4(L, R, U, D, ) & (DIAM12, DIAM13, DIAM14, ) } "
        + "{5(L, R, U, D, ) & (DIAM15, DIAM16, DIAM17, ) } "
        + "{6(L, R, U, D, ) & (DIAM18, DIAM19, SAP0, ) } "
        + "{7(L, R, U, D, ) & (SAP1, SAP2, SAP3, ) } "
        + "{8(L, U, D, ) & (SAP4, SAP5, SAP6, ) } "
        + "{9(R, U, D, ) & (SAP7, SAP8, SAP9, ) } \n" 
        + "{10(U, D, ) & () } {11(U, D, ) & () } {12(U, D, ) & () }"
        + " {13(U, D, ) & () } {14(U, D, ) & () } {15(U, D, ) & () }"
        + " {16(U, D, ) & () } {17(U, D, ) & () } {18(U, D, ) & () } "
        + "{19(U, D, ) & () } \n"  
        + "{20(U, D, ) & () } {21(U, D, ) & () } {22(U, D, ) & () } "
        + "{23(U, D, ) & () } {24(U, D, ) & () } {25(U, D, ) & () } "
        + "{26(U, D, ) & () } {27(U, D, ) & () } {28(U, D, ) & () } "
        + "{29(U, D, ) & () } \n" 
        + "{30(U, D, ) & () } {31(U, D, ) & () } {32(U, D, ) & () } "
        + "{33(U, D, ) & () } {34(U, D, ) & () } {35(U, D, ) & () } "
        + "{36(U, D, ) & () } {37(U, D, ) & () } {38(U, D, ) & () } "
        + "{39(U, D, ) & () } \n" 
        + "{40(U, D, ) & () } {41(U, D, ) & () } {42(U, D, ) & () } "
        + "{43(U, D, ) & () } {44(U, D, ) & () } {45(U, D, ) & () } "
        + "{46(U, D, ) & () } {47(U, D, ) & () } {48(U, D, ) & () } "
        + "{49(U, D, ) & () } \n" 
        + "{50(U, D, ) & () } {51(U, D, ) & () } {52(U, D, ) & () } "
        + "{53(U, D, ) & () } {54(U, D, ) & () } {55(U, D, ) & () } "
        + "{56(U, D, ) & () } {57(U, D, ) & () } {58(U, D, ) & () } {59(U, D, ) & () } \n" 
        + "{60(U, D, ) & () } {61(U, D, ) & () } {62(U, D, ) & () }"
        + " {63(U, D, ) & () } {64(U, D, ) & () } {65(U, D, ) & () }"
        + " {66(U, D, ) & () } {67(U, D, ) & () } {68(U, D, ) & () } {69(U, D, ) & () } \n" 
        + "{70(U, D, ) & () } {71(U, D, ) & () } {72(U, D, ) & () } "
        + "{73(U, D, ) & () } {74(U, D, ) & () } {75(U, D, ) & () } "
        + "{76(U, D, ) & () } {77(U, D, ) & () } {78(U, D, ) & () } "
        + "{79(U, D, ) & () } \n" 
        + "{80(U, ) & (SAP10, SAP11, SAP12, ) } {81(U, ) & (SAP13, SAP14, SAP15, ) } "
        + "{82(U, ) & (SAP16, SAP17, SAP18, ) } {83(U, ) & (SAP19, RBS0, RBS1, ) }"
        + " {84(U, ) & (RBS2, RBS3, RBS4, ) } {85(U, ) & (RBS5, RBS6, RBS7, ) } "
        + "{86(U, ) & (RBS8, RBS9, RBS10, ) } {87(U, ) & (RBS11, RBS12, RBS13, ) } "
        + "{88(U, ) & (RBS14, RBS15, RBS16, ) } {89(U, ) & (RBS17, RBS18, RBS19, ) } \n" 
        + "{90(D, ) & () } {91(D, ) & () } {92(D, ) & () } {93(D, ) & () } "
        + "{94(D, ) & () } {95(D, ) & () } {96(D, ) & () } {97(D, ) & () } {98(D, ) & () }"
        + " {99(D, ) & () } \n" ,wrappingNonRandomDungeon.dungeonDescription());
  }

  @Test
  public void testLocationHasNoTreasureInitialNoWrappingDungeon() {
    IDungeon nonWrappingNonRandomDungeon = getDungeon(10, 10, 0, false, 10, 1, false, true);
    assertEquals(1, nonWrappingNonRandomDungeon.getPlayersLocation());
    assertEquals("", nonWrappingNonRandomDungeon.getPlayersTreasures());
    nonWrappingNonRandomDungeon.move("L");
    String locationDetails = nonWrappingNonRandomDungeon.getlocationDetails();
    String [] locDetails = locationDetails.split(":");
    assertEquals("", locDetails[1].trim());
    nonWrappingNonRandomDungeon.pickUpItem("RUBY");
    assertEquals("", nonWrappingNonRandomDungeon.getPlayersTreasures());
  }

  @Test
  public void testLocationHasNoTreasureInitialWrappingDungeon() {
    IDungeon wrappingNonRandomDungeon = getDungeon(10, 10, 0, true, 10, 1, false, true);
    assertEquals(1, wrappingNonRandomDungeon.getPlayersLocation());
    assertEquals("", wrappingNonRandomDungeon.getPlayersTreasures());
    wrappingNonRandomDungeon.move("D");
    String locationDetails = wrappingNonRandomDungeon.getlocationDetails();
    String [] locDetails = locationDetails.split(":");
    assertEquals("", locDetails[1].trim());
    wrappingNonRandomDungeon.pickUpItem("RUBY");
    assertEquals("", wrappingNonRandomDungeon.getPlayersTreasures());
  }

  @Test
  public void testPlayerPickUpTreasureNonWrappingDungeon() {
    IDungeon nonWrappingNonRandomDungeon = getDungeon(10, 10, 0, false, 10, 1, false, true);
    assertEquals(1, nonWrappingNonRandomDungeon.getPlayersLocation());
    assertEquals("", nonWrappingNonRandomDungeon.getPlayersTreasures());
    String locationDetails = nonWrappingNonRandomDungeon.getlocationDetails();
    String [] locDetails = locationDetails.split(":");
    assertEquals(" DIAM0, DIAM1, DIAM2, ", locDetails[1]);
    nonWrappingNonRandomDungeon.pickUpItem("RUBY");
    assertEquals("DIAM0, DIAM1, DIAM2, ", nonWrappingNonRandomDungeon.getPlayersTreasures());
  }

  @Test
  public void testPlayerPickUpTreasureWrappingDungeon() {
    IDungeon wrappingNonRandomDungeon = getDungeon(10, 10, 0, true, 10, 1, false, true);
    assertEquals(1, wrappingNonRandomDungeon.getPlayersLocation());
    assertEquals("", wrappingNonRandomDungeon.getPlayersTreasures());
    String locationDetails = wrappingNonRandomDungeon.getlocationDetails();
    String [] locDetails = locationDetails.split(":");
    assertEquals(" DIAM3, DIAM4, DIAM5, ", locDetails[1]);
    wrappingNonRandomDungeon.pickUpItem("RUBY");
    assertEquals("DIAM3, DIAM4, DIAM5, ", wrappingNonRandomDungeon.getPlayersTreasures());
  }

  @Test
  public void testVerifyLocationDescNonWrappingDungeon() {
    IDungeon nonWrappingNonRandomDungeon = getDungeon(10, 10, 0, false, 10, 1, false, true);
    assertEquals(1, nonWrappingNonRandomDungeon.getPlayersLocation());
    String locationDetails = nonWrappingNonRandomDungeon.getlocationDetails();
    assertEquals("L, R, D,  : DIAM0, DIAM1, DIAM2, ", locationDetails);
  }

  @Test
  public void testVerifyLocationWrappingDungeon() {
    IDungeon wrappingNonRandomDungeon = getDungeon(10, 10, 0, true, 10, 1, false, true);
    assertEquals(1, wrappingNonRandomDungeon.getPlayersLocation());
    String locationDetails = wrappingNonRandomDungeon.getlocationDetails();
    assertEquals("L, R, U, D,  : DIAM3, DIAM4, DIAM5, ", locationDetails);
  }

  @Test
  public void testVerifyLocationIfNoTreasureNonWrappingDungeon() {
    IDungeon nonWrappingNonRandomDungeon = getDungeon(10, 10, 0, false, 10, 1, false, true);
    assertEquals(1, nonWrappingNonRandomDungeon.getPlayersLocation());
    nonWrappingNonRandomDungeon.move("L");
    String locationDetails = nonWrappingNonRandomDungeon.getlocationDetails();
    assertEquals("R, D,  :", locationDetails.trim());
  }

  @Test
  public void testVerifyLocationIfNoTreasureWrappingDungeon() {
    IDungeon wrappingNonRandomDungeon = getDungeon(10, 10, 0, true, 10, 1, false, true);
    assertEquals(1, wrappingNonRandomDungeon.getPlayersLocation());
    wrappingNonRandomDungeon.move("D");
    String locationDetails = wrappingNonRandomDungeon.getlocationDetails();
    assertEquals("U, D,  :", locationDetails.trim());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInAllDirectionWrappingDungeon() {

    IDungeon wrappingNonRandomDungeon = getDungeon(10, 10, 0, true, 10, 1, false, true);
    assertEquals(1, wrappingNonRandomDungeon.getPlayersLocation());
    String locationDetails = wrappingNonRandomDungeon.getlocationDetails();
    assertEquals("L, R, U, D,  : DIAM3, DIAM4, DIAM5, ", locationDetails);

    wrappingNonRandomDungeon.move("U"); 
    assertEquals(91, wrappingNonRandomDungeon.getPlayersLocation());
    wrappingNonRandomDungeon.move("D");
    assertEquals(1, wrappingNonRandomDungeon.getPlayersLocation());

    wrappingNonRandomDungeon.move("L");
    assertEquals(0, wrappingNonRandomDungeon.getPlayersLocation());
    wrappingNonRandomDungeon.move("R");
    assertEquals(1, wrappingNonRandomDungeon.getPlayersLocation());

    wrappingNonRandomDungeon.move("R");
    assertEquals(2, wrappingNonRandomDungeon.getPlayersLocation());
    wrappingNonRandomDungeon.move("L");
    assertEquals(1, wrappingNonRandomDungeon.getPlayersLocation());

    wrappingNonRandomDungeon.move("D");
    assertEquals(11, wrappingNonRandomDungeon.getPlayersLocation());
    wrappingNonRandomDungeon.move("U");
    assertEquals(1, wrappingNonRandomDungeon.getPlayersLocation());

    wrappingNonRandomDungeon.move("D");
    assertEquals(11, wrappingNonRandomDungeon.getPlayersLocation());
    wrappingNonRandomDungeon.move("L");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveinAllDirectionNonWrappingDungeon() {

    IDungeon nonWrappingNonRandomDungeon = getDungeon(10, 10, 0, false, 10, 1, false, true);
    assertEquals(1, nonWrappingNonRandomDungeon.getPlayersLocation());
    String locationDetails = nonWrappingNonRandomDungeon.getlocationDetails();
    assertEquals("L, R, D,  : DIAM0, DIAM1, DIAM2, ", locationDetails);

    nonWrappingNonRandomDungeon.move("L");
    assertEquals(0, nonWrappingNonRandomDungeon.getPlayersLocation());
    nonWrappingNonRandomDungeon.move("R");
    assertEquals(1, nonWrappingNonRandomDungeon.getPlayersLocation());

    nonWrappingNonRandomDungeon.move("R");
    assertEquals(2, nonWrappingNonRandomDungeon.getPlayersLocation());
    nonWrappingNonRandomDungeon.move("L");
    assertEquals(1, nonWrappingNonRandomDungeon.getPlayersLocation());

    nonWrappingNonRandomDungeon.move("D");
    assertEquals(11, nonWrappingNonRandomDungeon.getPlayersLocation());
    nonWrappingNonRandomDungeon.move("U");
    assertEquals(1, nonWrappingNonRandomDungeon.getPlayersLocation());

    nonWrappingNonRandomDungeon.move("U");
    // assertEquals(11, nonWrappingNonRandomDungeon.getPlayersLocation());
    //wrappingNonRandomDungeon.move("L");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNoOfRows() {
    getDungeon(-10, 10, 0, false, 10, 1, true, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNoOfColumns() {
    getDungeon(10, -10, 0, false, 10, 1, true, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInterconnectivity() {
    getDungeon(10, 10, -10, false, 10, 1, true, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPercentageOfTreasure() {
    getDungeon(10, 10, 0, false, -10, 1, true, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInputAndOutputSize() {
    getDungeon(1, 5, 0, false, 10, 1, true, true);
  }

  @Test
  public void testInvalidStartPoint() {
    IDungeon dungeon = getDungeon(10, 10, 0, false, 10, 1, true, true);
    int startPoint = dungeon.getStartPoint();
    List<Integer>[][] twoDRepresentationOfDirections = dungeon.dungeonRepresentation();
    int row = startPoint / twoDRepresentationOfDirections[0].length; 
    int column = startPoint % twoDRepresentationOfDirections[0].length; 
    assertTrue(twoDRepresentationOfDirections[row][column].size() != 2);
  }

  @Test
  public void testInvalidEndPoint() {
    IDungeon dungeon = getDungeon(10, 10, 0, true, 10, 1, true, true);
    int endPoint = dungeon.getEndPoint();
    List<Integer>[][] twoDRepresentationOfDirections = dungeon.dungeonRepresentation();
    int row = endPoint / twoDRepresentationOfDirections[0].length; 
    int column = endPoint % twoDRepresentationOfDirections[0].length; 
    assertTrue(twoDRepresentationOfDirections[row][column].size() != 2);
  }

  @Test
  public void testDistanceBetweenStartAndEndPoint() {
    IDungeon dungeon = getDungeon(10, 10, 0, true, 10, 1, true, true);
    List<Integer>[][] twoDRepresentationOfDirections = dungeon.dungeonRepresentation();
    int leastDistance = leastDistance(twoDRepresentationOfDirections,dungeon.getStartPoint(),
        dungeon.getEndPoint());
    assertTrue(leastDistance >= 5);
  }

  @Test
  public void testIsAMST() {
    IDungeon dungeon = getDungeon(10, 10, 0, true, 10, 1, true, true);
    List<Integer>[][] twoDRepresentationOfDirections = dungeon.dungeonRepresentation();
    boolean isNotMST = isNotMST(twoDRepresentationOfDirections);
    assertFalse(isNotMST);
  }

  @Test
  public void testAllNodesReachablex() {
    IDungeon dungeon = getDungeon(10, 10, 0, true, 10, 1, true, true);
    List<Integer>[][] twoDRepresentationOfDirections = dungeon.dungeonRepresentation();
    boolean allNodesReachable = allNodesReachable(twoDRepresentationOfDirections);
    assertTrue(allNodesReachable);
  }

  //It test the start point is same as player current location.
  @Test
  public void testPlayerStartPoint() {
    IDungeon dungeon = getDungeon(10, 10, 0, true, 10, 1, true, true);
    int playerLocation = dungeon.getPlayersLocation();
    int dungeonStartPoint = dungeon.getStartPoint();
    assertEquals(playerLocation, dungeonStartPoint);
  }

  //It test after end point can be reached after sequence of step
  //For this I am running a bfs algorithm to find the end location and 
  //tracing the reverse direction.
  @Test
  public void testReachingEndPoint() {
    IDungeon dungeon = getDungeon(10, 10, 0, true, 10, 1, true, true);
    List<Integer>[][] twoDRepresentationOfDirections = dungeon.dungeonRepresentation();

    //Getting a valid path from start to end.
    List<String> validDirections = dirStartEnd(twoDRepresentationOfDirections, 
        dungeon.getPlayersLocation(), dungeon.getEndPoint());
    for (int i = 0 ; i < validDirections.size(); i++) {
      dungeon.move(validDirections.get(i));
    }
    assertEquals(dungeon.getEndPoint(), dungeon.getPlayersLocation());
  }

  @Test
  public void testAllNodesAreReachedByMoving() {
    IDungeon dungeon = getDungeon(10, 10, 0, true, 10, 1, true, true);
    Set<Integer> allNodes = new HashSet<>();
    List<Integer>[][] twoDRepresentationOfDirections = dungeon.dungeonRepresentation();
    List<String> validAllDirections = dirAllLoc(twoDRepresentationOfDirections,
        dungeon.getPlayersLocation(), dungeon.getEndPoint());

    boolean isAllNodeReachable = true;
    
    for (int i = 0 ; i < validAllDirections.size(); i++) {
      dungeon.move(validAllDirections.get(i));
      allNodes.add(dungeon.getPlayersLocation());
    }
    
    for (int i = 0 ; i < twoDRepresentationOfDirections[0].length 
        * twoDRepresentationOfDirections.length - 1; i++) {
      if (!allNodes.contains(i)) {
        isAllNodeReachable = false;
        break;
      }
    }
    assertTrue(isAllNodeReachable);
  }

  //all public methods 
  // check mst, interconnectivity, test all locations reached, test start to end reached.
  // check distance between start and end point


  private int leastDistance(List<Integer>[][] twoDirRepresentation, 
      int start, int end) {
    int  length = 0;
    int noOfColumns = twoDirRepresentation[0].length;
    boolean found = false;

    Set<Integer> visitedNodes = new HashSet<>();
    Deque<Integer> d = new ArrayDeque<>();
    d.add(start);
    visitedNodes.add(start);
    int count = 1; 
    int currCount = 0;
    while (!d.isEmpty() && !found) {
      currCount = 0;
      length++;
      for (int i = 0 ; i < count ; i++) {
        Integer topElement = d.remove();
        int row = topElement / noOfColumns;
        int col = topElement % noOfColumns;
        List<Integer> allAvblDir = twoDirRepresentation[row][col];
        for (int elemI = 0; elemI < allAvblDir.size(); elemI++) {
          int nextNode = allAvblDir.get(elemI);
          if (nextNode == end) {
            found = true;
          }
          if (!visitedNodes.contains(nextNode)) {
            d.add(nextNode);
            visitedNodes.add(nextNode);
            currCount++;
          }
        }
      }
      count = currCount;
    }
    return length;
  }

  private boolean allNodesReachable(List<Integer>[][] twoDirRepresentation) {
    int noOfColumns = twoDirRepresentation[0].length;
    int noOfRows = twoDirRepresentation.length;
    boolean isAllNodeReachable = true;
    int start = 0;
    Set<Integer> nodesVisited = new HashSet<>();
    Deque<Integer> d = new ArrayDeque<>();
    Set<Integer> parents = new HashSet<>();
    Set<Integer> nonParents = new HashSet<>();
    d.add(start);
    nonParents.add(start);
    nodesVisited.add(start);
    int count = 1; 
    int currCount = 0;
    while (!d.isEmpty()) {
      currCount = 0;
      for (int i = 0 ; i < count ; i++) {
        Integer topElement = d.remove();
        int row = topElement / noOfColumns;
        int col = topElement % noOfColumns;
        List<Integer> allAvblDir = twoDirRepresentation[row][col];
        for (int elemI = 0; elemI < allAvblDir.size(); elemI++) {
          Integer nextNode = allAvblDir.get(elemI);

          if (!parents.contains(nextNode)) {
            if (!nonParents.contains(nextNode)) {
              d.add(nextNode);
              nonParents.add(nextNode);
              currCount++;
            }
          }
        }
        parents.add(topElement);
        nonParents.remove(topElement);
      }
      count = currCount;
    }
    for (int i = 0 ; i < noOfRows * noOfColumns; i++) {
      if (!parents.contains(i)) {
        isAllNodeReachable = false;
        break;
      }
    }
    return isAllNodeReachable;
  }

  Set<Integer> allNodesFromStartNode(List<Integer>[][] twoDirRepresentation) {
    int noOfColumns = twoDirRepresentation[0].length;
    int start = 0;
    Set<Integer> nodesVisited = new HashSet<>();
    Deque<Integer> d = new ArrayDeque<>();
    Set<Integer> parents = new HashSet<>();
    Set<Integer> nonParents = new HashSet<>();
    d.add(start);
    nonParents.add(start);
    nodesVisited.add(start);
    int count = 1; 
    int currCount = 0;
    while (!d.isEmpty()) {
      currCount = 0;
      for (int i = 0 ; i < count ; i++) {
        Integer topElement = d.remove();
        int row = topElement / noOfColumns;
        int col = topElement % noOfColumns;
        List<Integer> allAvblDir = twoDirRepresentation[row][col];
        for (int elemI = 0; elemI < allAvblDir.size(); elemI++) {
          Integer nextNode = allAvblDir.get(elemI);

          if (!parents.contains(nextNode)) {
            if (!nonParents.contains(nextNode)) {
              d.add(nextNode);
              nonParents.add(nextNode);
              currCount++;
            }
          }
        }
        parents.add(topElement);
        nonParents.remove(topElement);
      }
      count = currCount;
    }
    return parents;
  }

  private boolean isNotMST(List<Integer>[][] twoDirRepresentation) {
    int noOfColumns = twoDirRepresentation[0].length;
    boolean nodeRepeated = false;
    int start = 0;
    Set<Integer> nodesVisited = new HashSet<>();
    Deque<Integer> d = new ArrayDeque<>();
    Set<Integer> parents = new HashSet<>();
    Set<Integer> nonParents = new HashSet<>();
    d.add(start);
    nonParents.add(start);
    nodesVisited.add(start);
    int count = 1; 
    int currCount = 0;
    while (!d.isEmpty() && !nodeRepeated) {
      currCount = 0;
      for (int i = 0 ; i < count ; i++) {
        Integer topElement = d.remove();
        int row = topElement / noOfColumns;
        int col = topElement % noOfColumns;
        List<Integer> allAvblDir = twoDirRepresentation[row][col];
        //List<Integer> allAvblDir = twoDimHabitat[row][col].getAvblDir();
        for (int elemI = 0; elemI < allAvblDir.size(); elemI++) {
          Integer nextNode = allAvblDir.get(elemI);

          if (!parents.contains(nextNode)) {
            if (nonParents.contains(nextNode)) {
              nodeRepeated = true;
              break;
            } else {
              d.add(nextNode);
              nonParents.add(nextNode);
              currCount++;
            }
          }
        }
        parents.add(topElement);
        nonParents.remove(topElement);
      }
      count = currCount;
    }
    return nodeRepeated;
  }

  /**
   * It returns the all the paths between start and end location.
   * 
   * @param twoDimHabitat represents two dimensional habitat.
   * @param start represents the start location.
   * @param end represents the end location
   * @return the distance between start and end location.
   */
  private static List<String> dirAllLoc(List<Integer>[][] twoDimHabitat,
      int start, int end) {

    List<String> retList = new ArrayList<>();
    Map<Integer,  Deque<Integer>> map = paths(twoDimHabitat, start, end);
    Deque<Integer> d = new ArrayDeque<>();
    d.addFirst(start); 
    boolean found = false;
    while (!d.isEmpty() && !found) {
      Integer currS = d.getFirst();
      Deque<Integer> currDeq = map.get(currS);
      if (null != currDeq && currDeq.size() > 0) {
        Integer elem = currDeq.removeFirst();
        d.addFirst(elem);
        retList.add(getDirection(currS, elem, twoDimHabitat.length, twoDimHabitat[0].length));
      } else {
        d.removeFirst();
        if (!d.isEmpty()) {
          retList.add(getDirection(currS, d.getFirst(), 
              twoDimHabitat.length, twoDimHabitat[0].length));
        }
      }
    }
    return retList;
  }

  /**
   * It returns the all the paths between start and end location.
   * 
   * @param twoDimHabitat represents two dimensional habitat.
   * @param start represents the start location.
   * @param end represents the end location
   * @return the distance between start and end location.
   */
  private static List<String> dirStartEnd(List<Integer>[][] twoDimHabitat,
      int start, int end) {

    List<String> retList = new ArrayList<>();
    Map<Integer,  Deque<Integer>> map = paths(twoDimHabitat, start, end);
    //Create queue
    Deque<Integer> d = new ArrayDeque<>();
    d.addFirst(start); 
    boolean found = false;
    while (!d.isEmpty() && !found) {
      Integer currS = d.getFirst();
      Deque<Integer> currDeq = map.get(currS);
      if (null != currDeq && currDeq.size() > 0) {
        Integer elem = currDeq.removeFirst();
        if (elem == end) {
          found = true;
        }
        d.addFirst(elem);
        retList.add(getDirection(currS, elem, twoDimHabitat.length, twoDimHabitat[0].length));
      } else {
        d.removeFirst();
        if (!d.isEmpty()) {
          retList.add(getDirection(currS, d.getFirst(),
              twoDimHabitat.length, twoDimHabitat[0].length));
        }
      }
    }
    return retList;
  }

  /**
   * It returns the all the paths between start and end location.
   * 
   * @param twoDimHabitat represents two dimensional habitat.
   * @param start represents the start location.
   * @param end represents the end location
   * @return the distance between start and end location.
   */
  private static Map<Integer,  Deque<Integer>> paths(List<Integer>[][] twoDimHabitat, 
      int start, int end) {
    int noOfColumns = twoDimHabitat[0].length;

    Set<Integer> visitedNodes = new HashSet<>();
    Deque<Integer> d = new ArrayDeque<>();
    d.add(start);
    visitedNodes.add(start);
    Map<Integer,  Deque<Integer>> map = new HashMap<>();
    int count = 1; 
    int currCount = 0;
    while (!d.isEmpty()) {
      currCount = 0;
      for (int i = 0 ; i < count ; i++) {
        Integer topElement = d.remove();
        int row = topElement / noOfColumns;
        int col = topElement % noOfColumns;
        List<Integer> allAvblDir = twoDimHabitat[row][col];
        for (int elemI = 0; elemI < allAvblDir.size(); elemI++) {
          int nextNode = allAvblDir.get(elemI);
          if (!visitedNodes.contains(nextNode)) {
            Deque<Integer> deque = map.getOrDefault(topElement, new ArrayDeque<>());
            deque.addFirst(nextNode);
            map.put(topElement, deque);
            d.add(nextNode);
            visitedNodes.add(nextNode);
            currCount++;
          }
        }
      }
      count = currCount;
    }
    return map;
  }

  /**
   * It returns the direction from start to end location.
   * 
   * @param start represents start location.
   * @param end represents end location.
   * @param noOfRows represents number of rows.
   * @param noOfColumns represents number of columns.
   * @return
   */
  private static String getDirection(int start, int end, int noOfRows,
      int noOfColumns) {
    int startRow = start / noOfColumns; 
    int startColumn = start % noOfColumns; 
    int endRow = end / noOfColumns;
    int endColumn = end % noOfColumns;

    if (startRow == endRow) {
      if (endColumn == startColumn + 1 || (startColumn == (noOfColumns - 1) 
          && endColumn == 0)) {
        return "R";
      } else {
        return "L";
      }
    } else {
      if (endRow == startRow + 1 || (startRow == (noOfRows - 1) 
          && endRow == 0)) {
        return "D";
      } else {
        return "U";
      }
    }
  }
}
