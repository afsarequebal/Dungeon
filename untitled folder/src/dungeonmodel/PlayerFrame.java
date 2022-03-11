package dungeonmodel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This frame represents the region where the
 * player description is shown.
 * It shows sample player, its treasures and 
 * arrows. It can be open as pop-up on click of 
 * my details button.
 */
class PlayerFrame extends JFrame implements IPlayerFrame {

  private static final long serialVersionUID = 4223108402349788597L;
  private JButton okbutton;

  /**
   * Constructor to create a pop-up showing players description.
   * It includes players treasures and arrows.
   * 
   * @param rm represent the read-only model to get model data.
   */
  public PlayerFrame(IReadOnlyDungeon rm) {
    super();
    if (null == rm) {
      throw new IllegalArgumentException("Argument can't be null.");
    }
    try {
      JLabel playerImageLabel;
      JLabel diamondLabel;
      JLabel arrowLabel;
      JLabel sapphireLabel;
      JLabel rubyLabel;
      setLayout(new GridBagLayout());

      setSize(400, 400);
      setResizable(false);
      setLocation(300, 300);
      GridBagConstraints c = new GridBagConstraints();

      BufferedImage bImage;
      InputStream stream = getClass().getResourceAsStream("/img/player1.png");
      bImage = ImageIO.read(stream);

      playerImageLabel = new JLabel(new ImageIcon(bImage));

      String playerArrow = rm.getPlayersArrows();
      String playerTreasure = rm.getPlayersTreasures();
      String [] playerArrowArr = playerArrow.split(",");
      String [] playerTreasureArr = playerTreasure.split(",");

      int arrowCount = 0;
      for (int i = 0; i < playerArrowArr.length; i++) {
        if (playerArrowArr[i].trim().equalsIgnoreCase("arrow")) {
          arrowCount++;
        }
      }

      int rubyCount = 0;
      int diamondCount = 0;
      int sapphireCount = 0;
      for (int i = 0; i < playerTreasureArr.length; i++) {
        if (playerTreasureArr[i].trim().equalsIgnoreCase("DIAMOND")) {
          diamondCount++;
        }
        if (playerTreasureArr[i].trim().equalsIgnoreCase("RUBY")) {
          rubyCount++;
        }
        if (playerTreasureArr[i].trim().equalsIgnoreCase("SAPPHIRE")) {
          sapphireCount++;
        }
      }

       
      stream = getClass().getResourceAsStream("/img/DIAMOND.png");
      BufferedImage bImage1 = ImageIO.read(stream);
      diamondLabel = new JLabel(new ImageIcon(bImage1));

      stream = getClass().getResourceAsStream("/img/arrow-black.png");
      BufferedImage bImage2 = ImageIO.read(stream);
      arrowLabel = new JLabel(new ImageIcon(bImage2));

      stream = getClass().getResourceAsStream("/img/SAPPHIRE.png");
      BufferedImage bImage3 = ImageIO.read(stream);
      sapphireLabel = new JLabel(new ImageIcon(bImage3));

      stream = getClass().getResourceAsStream("/img/RUBY.png");
      BufferedImage bImage4 = ImageIO.read(stream);
      rubyLabel = new JLabel(new ImageIcon(bImage4));

      c.gridx = 0;
      c.gridy = 0;
      c.weightx = 1;
      c.weighty = 1;
      c.gridwidth = 1;
      add(playerImageLabel, c);

      JLabel diamondCountL = new JLabel(String.valueOf(diamondCount));
      c.gridx = 0;
      c.gridy = 1;
      c.weightx = 1;
      c.weighty = 1;
      c.gridwidth = 1;
      add(diamondCountL, c);
      c.gridx = 1;
      c.gridy = 1;
      c.weightx = 1;
      c.weighty = 1;
      c.gridwidth = 1;
      add(diamondLabel, c);

      JLabel sapphireCountL = new JLabel(String.valueOf(sapphireCount));
      c.gridx = 0;
      c.gridy = 2;
      c.weightx = 1;
      c.weighty = 1;
      c.gridwidth = 1;
      add(sapphireCountL, c);
      c.gridx = 1;
      c.gridy = 2;
      c.weightx = 1;
      c.weighty = 1;
      c.gridwidth = 1;
      add(sapphireLabel, c);

      JLabel rubyCountL = new JLabel(String.valueOf(rubyCount));
      c.gridx = 0;
      c.gridy = 3;
      c.weightx = 1;
      c.weighty = 1;
      c.gridwidth = 1;
      add(rubyCountL, c);
      c.gridx = 1;
      c.gridy = 3;
      c.weightx = 1;
      c.weighty = 1;
      c.gridwidth = 1;
      add(rubyLabel, c);

      JLabel arrowCountL = new JLabel(String.valueOf(arrowCount));
      c.gridx = 0;
      c.gridy = 4;
      c.weightx = 1;
      c.weighty = 1;
      c.gridwidth = 1;
      add(arrowCountL, c);
      c.gridx = 1;
      c.gridy = 4;
      c.weightx = 1;
      c.weighty = 1;
      c.gridwidth = 1;
      add(arrowLabel, c);

      okbutton = new JButton("OK");
      c.gridx = 1;
      c.gridy = 5;
      c.weightx = 2;
      c.weighty = 1;
      c.gridwidth = 2;
      add(okbutton, c);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  @Override
  public void setButtonListener() {
    okbutton.addActionListener(l -> { 
      this.setVisible(false);
    });
  }

}
