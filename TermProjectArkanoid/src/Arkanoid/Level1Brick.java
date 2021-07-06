package Arkanoid;

import java.awt.Color;

import javax.swing.Icon;

public class Level1Brick extends Brick{
	public int life;
	public Level1Brick(Icon green) {
		super(green);
		this.life = 1;
	}
}
