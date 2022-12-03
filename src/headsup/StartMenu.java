package headsup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JToggleButton;
import java.awt.Toolkit;
import javax.swing.JScrollPane;

public class StartMenu extends JFrame {

	private JPanel contentPane;
	JButton Play;
	JButton createRoom;
	JButton chooseRoom;
	JButton GameRules;
	JButton Exit;
	JToggleButton MusicOnOff ; 
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	
	public StartMenu() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartMenu.class.getResource("/headsup/NewLogo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1920,1080 );
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1920, 1080);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		
		JLabel BackGround = new JLabel("");
		BackGround.setBounds(0, 0, 1920, 1080);
		BackGround.setIcon(new ImageIcon(StartMenu.class.getResource("/headsup/image_ship.jpg")));
		layeredPane.add(BackGround);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		layeredPane.setLayer(panel, 1);
		panel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		Play = new JButton("Play Game");
		Play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Play.setBackground(Color.GREEN);
		Play.setFont(new Font("Snap ITC", Font.BOLD, 30));
		Play.setBounds(750, 360, 300, 75);
		panel.add(Play);
		
		createRoom = new JButton("Create Room");
		createRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		createRoom.setBackground(Color.YELLOW);
		createRoom.setFont(new Font("Snap ITC", Font.BOLD, 30));
		createRoom.setBounds(1080, 360, 300, 75);
		panel.add(createRoom);
		
		chooseRoom = new JButton("Choose Room");
		chooseRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chooseRoom.setBackground(Color.YELLOW);
		chooseRoom.setFont(new Font("Snap ITC", Font.BOLD, 30));
		chooseRoom.setBounds(750, 455, 300, 75);
		panel.add(chooseRoom);
		
		GameRules = new JButton("Game Rules");
		GameRules.setBackground(Color.ORANGE);
		GameRules.setFont(new Font("Snap ITC", Font.BOLD, 30));
		GameRules.setBounds(1080, 455, 300, 75);
		panel.add(GameRules);
		GameRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {
	                    Desktop.getDesktop().browse(new URI("https://docs.google.com/document/d/18ked2LmRy7K1AWPDVG5tTx6v_nVKs-bV1KBP0LYXSMs/edit?usp=sharing"));
	                } catch (URISyntaxException | IOException ex) {
	                    //It looks like there's a problem
	                }
			}
		});
		
		Exit = new JButton("Exit");
		Exit.setBackground(Color.RED);
		Exit.setFont(new Font("Snap ITC", Font.BOLD, 30));
		Exit.setBounds(1080, 550, 300, 75);
		panel.add(Exit);
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit (0);
				
			}});
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(StartMenu.class.getResource("/headsup/NewLogo.png")));
		lblNewLabel.setBounds(431, 13, 938, 291);
		panel.add(lblNewLabel);
		
		MusicOnOff = new JToggleButton("Sound On");
		MusicOnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MusicOnOff.isSelected() == true)
				{
					MusicOnOff.setText("Sound Off");
				}
				else
				{
					MusicOnOff.setText("Sound On");
				}
			}
		});
		MusicOnOff.setBounds(750, 550, 300, 75);
		MusicOnOff.setBackground(new Color(255, 102, 102));
		MusicOnOff.setFont(new Font("Snap ITC", Font.BOLD, 30));
		panel.add(MusicOnOff);
	}
	public static void main(String[] args) {
		new StartMenu().setVisible(true);
	}
}
