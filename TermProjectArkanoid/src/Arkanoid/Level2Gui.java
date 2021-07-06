package Arkanoid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Level2Gui implements KeyListener, Runnable{
	private JFrame frame;
	private int xSpeed = 5;
	private int ySpeed = 5;
	private int brickCounter = 0;
	private Icon paddlePic = new ImageIcon(getClass().getResource("paddle.png"));
	private Icon orange = new ImageIcon(getClass().getResource("orange.png"));
	private Icon ballColor = new ImageIcon(getClass().getResource("black.png"));
	private Icon red = new ImageIcon(getClass().getResource("red.png"));
	private ArrayList<Brick> level2 = new ArrayList<>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private Paddle paddle = new Paddle(paddlePic);
	private int xActor = 463;
	private Ball ball = new Ball(ballColor);
	private int xActor2 = 450;
	private int yActor2 = 600;
	private JLabel levelLabel;
	private JLabel livesLabel;
	private JLabel scoreLabel;
	public void createGUI() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("players.txt"));
			String s;
			while((s = br.readLine())!= null) {
				String splitted [] = s.split(" ");
				players.add(new Player(splitted[0], LocalDate.parse(splitted[1]), LocalTime.parse(splitted[2]), Integer.parseInt(splitted[3])));
			}
			br.close();
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "File couldn't found");
		}
		// Frame
		frame = new JFrame();
		frame.setTitle("Level-1");
		frame.setSize(1000,1000);
		frame.setLayout(null);
		
		// Label
		levelLabel = new JLabel("Level-2");
		livesLabel = new JLabel("Lives: " + Main.lives);
		scoreLabel = new JLabel("Score: " + Main.score);
		
		// adding bricks to level1
		for(int i = 0; i < 27; i++) {
			if (i > 8 && i < 18) {
				level2.add(new Level2Brick(red));
			}
			else {
				level2.add(new Level1Brick(orange));
			}
		}
		
		// setting bounds to frame for brick
		int k = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				level2.get(k).setBounds((j+1)*95, (i+1)*90, 70, 40);
				frame.add(level2.get(k));
				k++;
			}
		}
		
		// bounding labels
		scoreLabel.setBounds(50,10,100,10);
		levelLabel.setBounds(450, 10, 50,10);
		livesLabel.setBounds(900,10,100,10);
		paddle.setBounds(xActor, 850, 110,40);
		ball.setBounds(xActor2, yActor2, 35,35);
		
		
		// adding game objects and labels
		frame.add(paddle);
		frame.add(ball);
		frame.add(livesLabel);
		frame.add(scoreLabel);
		frame.add(levelLabel);
		
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setVisible(true);
		
		
	}

	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			
			changeLayoutLeft();	
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			
			changeLayoutRight();
		}
		else if (e.getKeyCode() == KeyEvent.VK_Q) {
			frame.dispose();
		}
		else{
			
		}	
	}
	public void changeLayoutLeft()
	{
		xActor -= 50;
		moveBox(xActor);
	}
	public void changeLayoutRight()
	{
		xActor += 50;
		moveBox(xActor);
	}
	public void moveBox(int newX) {
		paddle.setBounds(newX, 850, 110, 40);
	}
	public void moveBox2(int newX, int newY) {
		ball.setBounds(newX, newY, 35, 35);
	}
	@Override
	public void run() {
		int sleep = 15;
		while(true) {
			try {
				xActor2 -= xSpeed;
				yActor2 -= ySpeed;
				moveBox2(xActor2, yActor2);
				if(ball.getX() == 0) {
					xSpeed = -xSpeed;
				}
				if(ball.getX() == 965) {
					xSpeed = -xSpeed;
					
				}
				if(ball.getY() == 0) {
					ySpeed = -ySpeed;
				}
				if(ball.getY() == 965) {
					ySpeed = -ySpeed;
				}
				if(ball.getBounds().intersects(paddle.getBounds())) {
					ySpeed = -ySpeed;
					
				}
				
				for(int i = 0; i < level2.size(); i++) {
					if(ball.getBounds().intersects(level2.get(i).getBounds())) {
						if(i > 8 && i < 18) {
							Level2Brick getBrick = (Level2Brick)level2.get(i);
							if(getBrick.life == 2) {
								getBrick.life--;
								ySpeed = -ySpeed;
							}
							else if(getBrick.life == 1) {
								getBrick.life=0;
								ySpeed = -ySpeed;
								xSpeed = -xSpeed;
								getBrick.setBounds(0,0,0,0);
								frame.remove(getBrick);
								Main.score += 20;
								brickCounter++;
							}
						}
						else {
							level2.get(i).life = 0;
							ySpeed = -ySpeed;
							xSpeed = -xSpeed;
							level2.get(i).setBounds(0,0,0,0);
							frame.remove(level2.get(i));
							Main.score += 10;
							brickCounter++;
						}
					}
				}
				if(ball.getY() == 965) {
					Main.lives--;
					xActor2=450;
					yActor2=600;
					moveBox2(xActor2, yActor2);
				}
				if(brickCounter == level2.size()) {
					Level3Gui level3 = new Level3Gui();
					try {
						level3.createGUI();
						new Thread(level3).start();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					frame.dispose();
					break;
				}
				if(brickCounter == 18) {
					sleep = 10;
				}
				if(Main.lives == 0) {
					scoreLabel.setText("Score: " + Main.score);
					livesLabel.setText("Lives: " + Main.lives);
					JOptionPane.showMessageDialog(null, "GAME OVER!!!");
					LocalDate scoreDate = LocalDate.now();
					LocalTime scoreTime = LocalTime.now();
					String userName;
					while(true) {
						try {
							userName = JOptionPane.showInputDialog(null, "Score Date: " + scoreDate + "\n" + "Score Time: " + scoreTime +  "\n" + "Score: " + Main.score + "\nUsername should be denemexyz it cant be deneme xyz",
									"Get User Name", JOptionPane.PLAIN_MESSAGE);
							if(userName.contains(" ")==true) {
								throw new SpaceException("DD");
							}
							if(userName.equals("")) {
								throw new ExceptionEmptyLine("dd");
							}
							players.add(new Player(userName, scoreDate, scoreTime, Main.score));
							break;
						}
						catch(SpaceException e1) {
							JOptionPane.showMessageDialog(null, "Username cant contain space character.", "Space Error", JOptionPane.ERROR_MESSAGE);
						}
						catch(ExceptionEmptyLine e1) {
							JOptionPane.showMessageDialog(null, "You have to type username", "Error username", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					Collections.sort(players, new CompareScore());
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter("players.txt"));
						
						String s = "";
						for(int i = 0; i < players.size(); i++) {
							s = players.get(i).userName + " " + players.get(i).locDate.toString() + " " + players.get(i).locTime.toString() + " " + Integer.toString(players.get(i).score) + "\n"; 
							bw.write(s);
						}
						bw.close();
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "File couldn't found");
					}
					frame.dispose();
					break;
				}
				scoreLabel.setText("Score: " + Main.score);
				livesLabel.setText("Lives: " + Main.lives);
				Thread.sleep(sleep);
			} catch (Exception e1) {
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
