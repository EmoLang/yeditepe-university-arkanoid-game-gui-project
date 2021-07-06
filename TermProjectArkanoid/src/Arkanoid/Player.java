package Arkanoid;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Player {
	public String userName;
	public int score;
	public LocalDate locDate;
	public LocalTime locTime;
	
	public Player(String userName, LocalDate locDate, LocalTime locTime, int score) {
		this.userName = userName;
		this.locDate = locDate;
		this.locTime = locTime;
		this.score = score;
	}
}
