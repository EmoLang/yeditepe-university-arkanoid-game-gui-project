package Arkanoid;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel {

  protected Image backgroundImage;
  private ImageIcon myBackgroundIcon; 

  public JPanelWithBackground(String fileName) throws IOException {
	myBackgroundIcon=new ImageIcon(getClass().getResource(fileName));
	
    backgroundImage =myBackgroundIcon.getImage(); 
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.drawImage(backgroundImage, 0, 0, null);
  }
}