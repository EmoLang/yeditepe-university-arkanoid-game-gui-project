package Arkanoid;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.Collections;


public class Main {
	public static int score = 0;
	public static int lives = 3;
	public static void main(String [] args) throws IOException {
		JFrame mainMenu = new JFrame("Main Menu");
		mainMenu.setTitle("Arkanoid Main Menu");
		mainMenu.setLayout(null);
		mainMenu.setSize(1000,800);
		JPanel panel=new JPanelWithBackground("orange.png");
		panel.setLayout(null);
		JButton playButton = new JButton("Play");
		JButton option = new JButton("Options");
		JButton about = new JButton("About");
		JButton scores = new JButton("Scores");
		JButton help = new JButton("Help");
		JButton exit = new JButton("Exit");
		JButton story = new JButton("Story");
		panel.add(playButton);
		panel.add(option);
		panel.add(scores);
		panel.add(help);
		panel.add(about);
		panel.add(story);
		panel.add(exit);
		
		panel.setBounds(0,0,1000,800);
		playButton.setBounds(400, 50, 200,80);
		option.setBounds(400, 150, 200, 80);
		scores.setBounds(400, 250, 200, 80);
		help.setBounds(400, 350, 200, 80);
		about.setBounds(400, 450, 200, 80);
		story.setBounds(400,550,200,80);
		exit.setBounds(400,650,200,80);
		mainMenu.add(panel);
		
		ArrayList<Player> player = new ArrayList<Player>();
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object [] levels = {"Level-1", "Level-2", "Level-3"};
				String getLevel = (String)JOptionPane.showInputDialog(
						null,
						"Select a Level",
						"Levels",
						JOptionPane.PLAIN_MESSAGE, 
						null,
						levels,
						"Level-1"
						);
				if(getLevel.equals("Level-1")) {
					Level1Gui level1 = new Level1Gui();
					try {
						score = 0;
						lives = 3;
						level1.createGUI();
						new Thread(level1).start();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else if(getLevel.equals("Level-2")) {
					Level2Gui level2 = new Level2Gui();
					try {
						score = 0;
						lives = 3;
						level2.createGUI();
						new Thread(level2).start();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else if(getLevel.equals("Level-3")) {
					Level3Gui level3 = new Level3Gui();
					try {
						score = 0;
						lives = 3;
						level3.createGUI();
						new Thread(level3).start();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		option.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "You dont need to reach option pane.");
			}
		});
		
		scores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(scores, "Comming Soon");
				Score scores = new Score();
			}
		});
		
		
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(help, "You can just play with arrow keys. You can press Q key, when you play a game, to quit", "Help", JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(about, "Name: Muzaffer Tolga\nSurname: Yakar\nSchool Number: 20180702036\nMail: muzaffertolga.yakar@std.yeditepe.edu.tr", "About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		story.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(story, "You catched Covid-19 you have to defeat Covid-19 in your body\n(Bricks are Covid-19, Ball is Biontech Vaccine)");
			}
		});
		
		// for exit button
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String audioFilePath = "MicrosoftWindowsXPShutdown-Sound Effect(HD).wav";
		        AudioPlayerExample1 player = new AudioPlayerExample1();
		        player.play(audioFilePath);
		        
				JOptionPane.showMessageDialog(exit,"GOODBYE");
				mainMenu.dispatchEvent(new WindowEvent(mainMenu, WindowEvent.WINDOW_CLOSING));
				System.exit(0);
			}
		});
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setResizable(false);
		mainMenu.setVisible(true);
		
	}
}
