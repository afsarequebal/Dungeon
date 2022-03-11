import java.io.IOException;

import dungeonmodel.Features;
import dungeonmodel.IDungeonView;
import dungeonmodel.IReadOnlyDungeon;

/**
 * Mock view used for testing controller in isolation.
 * It returns constant values received in the parameters.
 * 
 */
public class MockView implements IDungeonView {
  private Appendable log;

  /**
   * Constructs a mock model object.
   * 
   * @param log represent output object to show result
   */
  public MockView(Appendable log) {
    this.log = log;
  }


  @Override
  public void refresh() {
    try {
      log.append("refresh\n");
    } catch (IOException e) {
      //Mock view exception catch 
    }
  }

  @Override
  public void setFeatures(Features f) {
    try {
      log.append("Features added\n");
    } catch (IOException e) {
    //Mock view exception catch 
    }
  }

  @Override
  public void resetFocus() {
    try {
      log.append("Focus request done\n");
    } catch (IOException e) {
    //Mock view exception catch 
    }
  }

  @Override
  public void makeVisible() {
    try {
      log.append("Make visible called\n");
    } catch (IOException e) {
    //Mock view exception catch 
    }

  }

  @Override
  public void showErrorMessage(String error) {
    try {
      log.append("Error message shown\n" + error);
    } catch (IOException e) {
    //Mock view exception catch 
    }
  }

  @Override
  public void setModel(IReadOnlyDungeon rm) {
    try {
      log.append("Model setting done\n");
    } catch (IOException e) {
    //Mock view exception catch 
    }
  }

  @Override
  public void makeInVisible() {
    try {
      log.append("Make visible called\n");
    } catch (IOException e) {
    //Mock view exception catch 
    }

  }

  @Override
  public void printResult(String result) {
    try {
      log.append("Print result called\n" + result);
    } catch (IOException e) {
    //Mock view exception catch 
    }

  }


  @Override
  public void showAttackResult(int s) {
    try {
      log.append("show attack result called\n" + s);
    } catch (IOException e) {
    //Mock view exception catch 
    }

  }


  @Override
  public void showFightResult(int s) {
    try {
      log.append("show fight result called\n" + s);
    } catch (IOException e) {
    //Mock view exception catch 
    }

  }


  @Override
  public void makeOptionFrameVisible() {
    try {
      log.append("make option frame invisible\n");
    } catch (IOException e) {
    //Mock view exception catch 
    }

  }

}