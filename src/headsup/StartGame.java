package headsup;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.applet.Applet;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.ExecutionException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import com.sun.*;

public class StartGame {
	static ArrayList<String> playerShips;
	static ArrayList<String> enemyShips;
	static Board board;
	static Lobby lobby;
	static SetShip SetShips;
	static boolean playerTurn = true;
	static OponentsTurn turn;
	static private boolean done = false;
	static boolean MusicOn = true;
	static private boolean start = true;
	static Client client;
	static Clip clip;
	static URL Menus = StartGame.class.getResource("Automation.wav");
	static URL GamePlay = StartGame.class.getResource("Machines.wav");
	static URL boom = StartGame.class.getResource("boom.wav");
	static ArrayList<String> previousStrikes;
	static int n = 10;
	public static void main(String [] args) {
		
	}
	public static void GameOn(SetShip SetShips)
	{
		board = new Board(SetShips.shipsLocation,n);
		board.setExtendedState(JFrame.MAXIMIZED_BOTH);
		board.setUndecorated(true);
		client.game = board;
		
		board.Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.out.writeObject("EXIT");
					client.out.flush();
					client.sock.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		board.MainMenu.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {//client muốn trở về menu
					client.out.writeObject("EXIT");
					client.out.flush();
					client.sock.close();
				}
				catch(IOException e1) {
					e1.printStackTrace();
				}
				client.stop();
				main (null);
				board.dispose();
			}
		});
		board.textArea.append("");
		if(playerTurn) {
			StartGame.board.layeredPane_1.add(StartGame.board.Turn);
		}
		board.setExtendedState(JFrame.MAXIMIZED_BOTH);
		board.setUndecorated(true);
		board.setVisible(true);
		SetShips.dispose();
		if(MusicOn) {
			play(GamePlay);
			done = false;
		}
		turn =new OponentsTurn (board.btns,board,client);
		Thread Oponent = new Thread (turn) ;
		Oponent.start();
		client.turn = turn;
		playerShips = SetShips.shipsLocation;
		previousStrikes = new ArrayList<String>();
		playTurnOnline (board.btnsEnemy,enemyShips);
	}
	public static void boom()
	{
		Clip clip2;
		try
		{
			URL url = StartGame.class.getResource("boom.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(url);
			clip2 = AudioSystem.getClip();
			clip2.open(sound);
			clip2.start();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e+"");
		}
	}
	public static void play(URL url)//mở nhạc cho nó chạy vòng lặp
	{
		try {
			clip.stop();
			AudioInputStream sound = AudioSystem.getAudioInputStream(url);	
			clip = AudioSystem.getClip();
			clip.open(sound);
			int loop = clip.LOOP_CONTINUOUSLY;
			clip.loop(loop);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void playVsComputer()
	{
		
	}
	public static void PlayOnline()
	{
		SetShips = new SetShip(n);
		client.SetShips = SetShips;
		SetShips.setExtendedState(JFrame.MAXIMIZED_BOTH);
		SetShips.setUndecorated(true);
		final timer60 time= new timer60(SetShips.timee,90);
		final Timer timer = new Timer();
		SetShips.setVisible(true);
		timer.schedule(time, 0,1000);
		SetShips.layeredPane.add(SetShips.timee);
		SetShips.OExit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					client.out.writeObject("EXIT");
					client.out.flush();
					client.sock.close();
					client.stop();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
		SetShips.Exit.setText("Back to lobby");
		SetShips.Exit.setBackground(Color.MAGENTA);
		SetShips.Exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					client.out.writeObject("XX");
					client.out.flush();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
				Lobby lobby = new Lobby ();
				client.lobby = lobby;
				lobby.client = client;
				lobby.name = client.userName;
				lobby.setVisible(true);
				SetShips.dispose();
			}
		});
		
	}

	public static void playTurnOnline(ArrayList<JToggleButton> btnsEnemy,final ArrayList <String> enemy)
	{
		for(final JToggleButton button : btnsEnemy)
		{
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					synchronized (this) {
						if(playerTurn) {
							String message = button.getName();
							previousStrikes.add(message);
							try {
								client.out.writeObject("##"+message);
								client.out.flush();
							}
							catch(Exception e) {}
							checkOnlineEnemy (button,enemy);
							button.setEnabled(false);
							playerTurn = false;
						}
						else
						{
							button.setSelected(false);
						}
					}
				}
			});
		}
	}
	private static void checkOnlineEnemy(JToggleButton button, ArrayList<String> enemyShips) {
		// TODO Auto-generated method stub
		board.textArea.append("");
		if(enemyShips.contains(button.getName())) {
			enemyShips.remove(button.getName());
			board.textArea.append("");
			button.setIcon(new ImageIcon(Board.class.getResource("/headsup/hit.png")));
			button.setDisabledSelectedIcon(new ImageIcon(Board.class.getResource("/headsup/hit.png")));
			char firstIndex =  button.getName().charAt(0);
			int secondIndex =  Integer.parseInt(button.getName().substring(1)); //
			String Namebtn1 = (char)(firstIndex-1) + "" + (secondIndex-1);
			String Namebtn2 = (char)(firstIndex-1) + "" + (secondIndex+1);
			String Namebtn3 = (char)(firstIndex+1) + "" + (secondIndex-1);
			String Namebtn4 = (char)(firstIndex+1) + "" + (secondIndex+1);
			System.out.print(Namebtn1 + "\t" + Namebtn2+ "\t"+Namebtn3+"\t"+Namebtn4);

			for(JToggleButton btn : board.btnsEnemy)
			{
				if(btn.getName().equalsIgnoreCase(Namebtn1) ||btn.getName().equalsIgnoreCase(Namebtn2) ||
						btn.getName().equalsIgnoreCase(Namebtn3)|| btn.getName().equalsIgnoreCase(Namebtn4))
				{
					btn.setIcon(new ImageIcon(Board.class.getResource("/headsup/miss.png")));
					btn.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/miss.png")));
					btn.setEnabled(false);
				}
			}
			button.setEnabled(false);
			int Count = 0; 
			for (int i =0 ; i < enemyShips.size();i++) {
				if (enemyShips.get(i).equals("/"))Count +=Count---Count;
				else Count = 0;
				if (Count == 2) {
					//chặn chỗ này như nào ta
					board.textArea.append (" We destroyed their ship \n");
					enemyShips.remove(i);
					i+=i---1;
					if (MusicOn)boom();
				}
			}
		}
		else
		{
			button.setIcon(new ImageIcon(Board.class.getResource("/headsup/miss.png")));
			button.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/miss.png")));
			board.textArea.append (button.getName () + " is a miss \n");
		}
		button.setEnabled(false);
		boolean done = true; 
		for (String s: enemyShips) {
			if (!s.equals("/")) {
				done = false;
				break;
			}
		}
		if (done) {
			JOptionPane.showMessageDialog(null, "You won", "Game Over", JOptionPane.YES_OPTION, new ImageIcon (Board.class.getResource("/headsup/win.png")));
				
				try {
					client.out.writeObject("XX");
					client.out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
						Lobby lobby = new Lobby ();
						client.lobby = lobby;
						lobby.client = client;
						lobby.name = client.userName;
						lobby.setVisible(true);
						board.dispose();
				
		}
			
	}
	public static void time90out()
	{
		SetShips.random();
		String loc = "%%";
		for(String i : SetShips.shipsLocation)
		{
			loc+= i;
		}
		try {
		client.out.writeObject(loc);
		client.out.flush();}
		catch (Exception e1) {}
		SetShips.layeredPane.add(SetShips.black);
		SetShips.layeredPane.add(SetShips.txt);
	}
	public static void time60out()
	{
		//tách ra làm 3 trường hợp //8 :10 :12
		if(n == 8)
		{
			while(true)
			{
				char firstchar = (char)(int)(Math.random()*n+65);
				int second = (int) (Math.random()*n);
				
				String testHit = (char)firstchar +"" + second;
				if(firstchar<'I' && firstchar>'@'&&second< 8 && second > 0)
				{
					if(!previousStrikes.contains(testHit))
					{
						JToggleButton btn = null;
						for(JToggleButton b : board.btnsEnemy) {
							if(b.getName().equals(testHit))
							{
								btn = b;
								break;
							}
						}
						previousStrikes.add(testHit);
						try {
							client.out.writeObject ("##" + testHit);
							client.out.flush();
						}
						catch(IOException e){}
						checkOnlineEnemy(btn,enemyShips);
						
					}
					
				}
			}
		}
		else if( n ==10)
		{
			while(true)
			{
				String testHit = (char)(int)(Math.random()*n+65) +"" + ((int)(Math.random()*n));
				if(testHit.charAt(0)<'K' && testHit.charAt(0)>'@'&&testHit.charAt(1)< ':' && testHit.charAt(1) > '/')
				{
					if(!previousStrikes.contains(testHit))
					{
						JToggleButton btn = null;
						for(JToggleButton b : board.btnsEnemy) {
							if(b.getName().equals(testHit))
							{
								btn = b;
								break;
							}
						}
						previousStrikes.add(testHit);
						try {
							client.out.writeObject ("##" + testHit);
							client.out.flush();
						}
						catch(IOException e){}
						checkOnlineEnemy(btn,enemyShips);
						
					}
					
				}
			}
		}
		else if(n == 12)
		{
			while(true)
			{
				char firstchar = (char)(int)(Math.random()*n+65);
				int second = (int) (Math.random()*n);
				
				String testHit = (char)firstchar +"" + second;
				if(firstchar<'M' && firstchar>'@'&&second< 12 && second > 0)
				{
					if(!previousStrikes.contains(testHit))
					{
						JToggleButton btn = null;
						for(JToggleButton b : board.btnsEnemy) {
							if(b.getName().equals(testHit))
							{
								btn = b;
								break;
							}
						}
						previousStrikes.add(testHit);
						try {
							client.out.writeObject ("##" + testHit);
							client.out.flush();
						}
						catch(IOException e){}
						checkOnlineEnemy(btn,enemyShips);
						
					}
					
				}
			}
		}
		
	}

	
}
