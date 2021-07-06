package Arkanoid;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class Score extends JFrame{
	ArrayList<Player> p = new ArrayList<Player>();
	public Score() {
		super("Score");
		setSize(600,600);
		setLayout(null);
		try {
			BufferedReader br = new BufferedReader(new FileReader("players.txt"));
			String s;
			while((s = br.readLine())!= null) {
				String splitted [] = s.split(" ");
				p.add(new Player(splitted[0], LocalDate.parse(splitted[1]), LocalTime.parse(splitted[2]), Integer.parseInt(splitted[3])));
			}
			br.close();
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "File couldn't found");
		}
//		Collections.sort(p, new CompareScore());
		Collections.sort(p, new CompareScore());
		JTextArea text = new JTextArea();
		text.setBounds(0,0,600,600);
		text.append("Username\t\tDate\tTime\t\tScore\n\n");
		for(int i = 0; i < p.size(); i++) {
			String userName;
			if(p.get(i).userName.length() >= 10) {
				userName = p.get(i).userName.substring(0, 7) + "...";
			}
			else {
				userName = p.get(i).userName;
			}
			text.append((i+1) + ": " + userName + "\t\t" + p.get(i).locDate.toString() + "\t" + p.get(i).locTime.toString() + "\t" + Integer.toString(p.get(i).score) + "\n");
		}
		add(text);
		text.setEditable(false);
		setVisible(true);
		setResizable(false);
	}
}
