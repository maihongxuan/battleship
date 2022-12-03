package headsup;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Toolkit;

public class Lobby extends JFrame {

	private JPanel contentPane;
	JButton btnMainmenu;
	playersList playerList = new playersList();//
	JList list;
	Client client;
	JScrollPane scrollPane;
	String name;
	String enemy;
	private JLabel lbNewLabel;
	JLabel waiting;
	JLayeredPane layeredPane;
	private JScrollPane scrollPane_1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					Lobby frame = new Lobby();
					frame.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Lobby()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Lobby.class.getResource("/headsup/Newlogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1920,1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1,0,0,0));
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		
		lbNewLabel = new JLabel("New Label");
		lbNewLabel.setIcon(new ImageIcon(Lobby.class.getResource("/headsup/image_ship.jpg")));
		lbNewLabel.setBounds(0,0,1920,1080);//set up background
		layeredPane.add(lbNewLabel);
		
		btnMainmenu = new JButton("Main Menu");
		layeredPane.setLayer(btnMainmenu,1);
		btnMainmenu.setBounds(881, 515, 266, 41);
		layeredPane.add(btnMainmenu);
		btnMainmenu.setForeground(Color.WHITE);
		btnMainmenu.setBackground(Color.ORANGE);
		btnMainmenu.setFont(new Font("Snap ITC",Font.PLAIN,25));
		
		JButton Close = new JButton("Close");
		layeredPane.setLayer(Close, 1);
		Close.setBounds(881, 567, 266, 41);
		Close.setForeground(Color.WHITE);
		Close.setBackground(Color.RED);
		Close.setFont(new Font("Snap ITC", Font.PLAIN,25));
		layeredPane.add(Close);
		Close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					client.out.writeObject("EXIT");
					client.out.flush();
					client.sock.close();
					client.stop();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		JLabel logo = new JLabel("");
		logo.setBounds(350, 27, 940, 312);
		layeredPane.setLayer(logo, 1);
		logo.setIcon(new ImageIcon(SetShips.class.getResource("/headsup/Newlogo.png")));
		layeredPane.add(logo);
		
		scrollPane = new JScrollPane();
		layeredPane.setLayer(scrollPane, 1);
		scrollPane.setBounds(480, 387, 320, 360);
		layeredPane.add(scrollPane);
		
		list = new JList(playerList);
		
		list.setForeground(Color.WHITE);
		list.setBackground(new Color(0, 0, 51));
		list.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		list.setSelectedIndex(0);
		list.setFont(new Font("Snap ITC",Font.PLAIN,23));
		scrollPane.setViewportView(list);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				enemy = (String) playerList.getElementAt(list.getSelectedIndex());
			}
			
		});
		JLabel lbNewLable_1 = new JLabel("Available Players");
		lbNewLable_1.setForeground(Color.YELLOW);
		lbNewLable_1.setFont(new Font("Snap ITC",Font.BOLD, 27));
		layeredPane.setLayer(lbNewLable_1,1);
		lbNewLable_1.setBounds(480, 310, 317, 65);
		layeredPane.add(lbNewLable_1);
		
		JButton MainMenu_1 = new JButton("Request Game");
		MainMenu_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Done");
				if(enemy != null)
				{
					try
					{
						client.out.writeObject('@'+enemy);
						client.out.flush();
					}
					catch(IOException e1)
					{
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "please select a player","Error",JOptionPane.INFORMATION_MESSAGE );
				}
			}
		});
		
		layeredPane.setLayer(MainMenu_1, 1);
		MainMenu_1.setForeground(Color.WHITE);
		MainMenu_1.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		MainMenu_1.setBackground(Color.GREEN);
		MainMenu_1.setBounds(881, 461, 266, 41);
		layeredPane.add(MainMenu_1);
		
		waiting = new JLabel("Watting for player to respond");
		waiting.setForeground(Color.YELLOW);
		waiting.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		layeredPane.setLayer(waiting, 1);
		waiting.setBounds(830, 370, 420, 34);
		
	}
	public void lister(String message)
	{
		playerList.reset();
		message = message.substring(3);
		for(int i = 0; i < message.length() ; i++)
		{
			String userName = "";
			while(message.charAt(i) != '\\')
			{
				userName += message.charAt(i);
				i += i---i;
			}
			if(!userName.equals(name)) playerList.add(userName);
		}
		list = new JList(playerList);
		listIntialzer(list);
		scrollPane.setViewportView(list);
		repaint();
	}
	public void listIntialzer(final JList list)
	{
		list.setForeground(Color.WHITE);
		list.setBackground(new Color(0, 0, 51));
		list.setBorder(new MatteBorder(2, 2, 2, 2,(Color) new Color(0, 0, 0)));
		list.setSelectedIndex(0);
		list.setFont(new Font("Snap ITC", Font.PLAIN, 23));
		scrollPane.setViewportView(list);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				System.out.print("Done");
				enemy = (String) playerList.getElementAt(list.getSelectedIndex());
				System.out.println(enemy);
			}
		});
	}
}
