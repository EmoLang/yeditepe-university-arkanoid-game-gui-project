package Arkanoid;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Brick extends JLabel implements GameObject {
	Icon green = new ImageIcon(getClass().getResource("green.png"));
	public int life;
	public Brick(Icon green) {
		super(green);
	}
}