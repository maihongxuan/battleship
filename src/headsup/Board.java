package headsup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.text.DefaultCaret;

import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLayeredPane;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
public class Board extends JFrame {

	protected HashMap<String, JToggleButton> btns = new HashMap<String , JToggleButton>();
	protected ArrayList <JToggleButton> btnsEnemy = new ArrayList<JToggleButton>();
	protected JPanel contentPane;
	protected JPanel panel;
	protected JPanel panelSelf;
	protected JTextArea textArea;
	private int radarCount = 0 ;
	protected JButton MainMenu;
	JLayeredPane layeredPane_1;//Lớp JLayeredPane được sử dụng để thêm chiều sâu cho thùng chứa
	JLabel black;
	JLabel lblNewLabel_3;
	JButton Close;
	JLabel Turn;
	JLabel NotTurn;
	JLabel time;
	
	/**
	 * @param list
	 * @param n
	 */
	public Board(ArrayList<String> list,int n)// với n là số ô của board n*n
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Board.class.getResource("/headsup/Logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,1920,1080);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(153,102,255));
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(0,0,1920,1080);
		layeredPane_1.setLayout(null);
		contentPane.add(layeredPane_1);
		addElements(layeredPane_1);
		addButtons(layeredPane_1,list);
		
		panel = new JPanel();
		layeredPane_1.setLayer(panel, 1);
		panel.setBounds(80, 250, 500, 500);
		layeredPane_1.add(panel);
		panel.setOpaque(false);// để đặt màu nền cho Label
		panel.setLayout(new GridLayout(n,n,0,0));
		setEnemyGrid(panel, n);
		
		panelSelf = new JPanel();
		layeredPane_1.setLayer(panelSelf, 1);
		panelSelf.setBounds(779, 287, 500, 500);
		layeredPane_1.add(panelSelf);
		panelSelf.setOpaque(false);
		panelSelf.setLayout(new GridLayout(n,n,0,0));
		setPlayerGrid(panelSelf, list, n);
		
		JScrollPane scrollPane = new JScrollPane();
		
		layeredPane_1.setLayer(scrollPane, 1);
		scrollPane.setBounds(1200, 250, 300, 300);
		layeredPane_1.add(scrollPane);
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 20));
		scrollPane.setViewportView(textArea);
		
		Turn = new JLabel("Your Turn");
		Turn.setForeground(Color.YELLOW);
		Turn.setFont(new Font("Snap ITC",Font.PLAIN,40));
		layeredPane_1.setLayer(Turn, 1);
		Turn.setBounds(780, 780, 243, 49);
		
		NotTurn = new JLabel("Opppnent's turn");
		layeredPane_1.setLayer(NotTurn, 1);
		
		time = new JLabel("");
		time.setFont(new Font("Snap ITC",Font.PLAIN,30));
		time.setForeground(Color.YELLOW);
		layeredPane_1.setLayer(time, 1);
		time.setBounds(700, 780, 56, 58);
		layeredPane_1.add(time);
		NotTurn.setForeground(Color.YELLOW);
		NotTurn.setFont(new Font("Snap ITC",Font.PLAIN,40));
		NotTurn.setBounds(780, 780, 392, 49);
		
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
	}
	
	private void setPlayerGrid(JPanel panelSelf, ArrayList<String> shipLocation,int n)
	{
		char loopy = 'A';
		for(int i=0 ; i < n ; i++)
		{
			for(int j = 0 ; j < n ;j++)
			{
				JToggleButton btn  = new JToggleButton("");
				btn.setName((char) loopy + "" +(j));
				btns.put(btn.getName(), btn);
				panelSelf.setEnabled(false);
				if(shipLocation.contains(btn.getName()))
				{
					//setDisabledIcon nếu btn này không ở trạng thái disabel gì nó hiển thị icon này
					btn.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));
					btn.setIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));
				}
			}
			loopy++;
		}
	}
	private void setEnemyGrid(JPanel panel, int n)
	{
		char loopy = 'A';
		for(int i = 0; i < n  ; i++)
		{
			for(int j = 0 ; j < n ;j++)
			{
				JToggleButton btn = new JToggleButton("");//JToggelButton là cái nút chỉ có 2 trạng thái
				btn.setName((char) loopy + "" +(j));
				btnsEnemy.add(btn);
				panel.add(btn);
			}
			loopy++;
		}
	}
	public void addElements(JLayeredPane layeredPane)
	{
		JLabel Background = new JLabel("");
		layeredPane.setLayer(Background, 0);
		Background.setBounds(0, 0, 1920, 1080);
		Background.setIcon(new ImageIcon(Board.class.getResource("/headsup/image_ship.jpg")));
		layeredPane.add(Background);
		
		JLabel logo = new JLabel("");
		logo.setBounds(349, 27, 940, 312);
		layeredPane.setLayer(logo, 1);
		logo.setIcon(new ImageIcon(SetShips.class.getResource("/headsup/NewLogo.png")));
		layeredPane.add(logo);
		
		JLabel lbCommentaryBox = new JLabel("Chatting");
		layeredPane.setLayer(lbCommentaryBox, 1);
		lbCommentaryBox.setBounds(1250, 200, 310, 40);
		layeredPane.add(lbCommentaryBox);
		lbCommentaryBox.setForeground(Color.BLACK);
		lbCommentaryBox.setFont(new Font("Snap ITC", Font.BOLD,30));
		
		black = new JLabel("");
		black.setIcon(new ImageIcon(SetShips.class.getResource("/headsup/BackGround - Copy (2).jpg")));
		layeredPane.setLayer(black, 2);
		black.setBounds(0, 0, 1908, 1031);
		

		lblNewLabel_3 = new JLabel("Waiting for Opponent ...");
		lblNewLabel_3.setForeground(Color.YELLOW);
		lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD, 76));
		layeredPane.setLayer(lblNewLabel_3, 4);
		lblNewLabel_3.setBounds(463, 390, 1133, 137);
	}
	public void addButtons(JLayeredPane layeredPane,final ArrayList <String> list)
	{
		JButton Hint = new JButton("Hint");//là sao ta
		layeredPane.setLayer(Hint, 1);
		Hint.setBounds(1200, 570, 300, 40);
		layeredPane.add(Hint);
		Hint.setForeground(Color.WHITE);
		Hint.setBackground(Color.GREEN);
		Hint.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		Hint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radarCount<3) {
				while (true) {
				
				int i = list.size();
				int location = (int)(Math.random()*i);
				if (list.get(location)!= "/" || list.contains(list.get(location))) {
				System.out.println (list);
				System.out.println(list.get(location));
				textArea.append("The radar says " +  list.get(location) + " is a hot zone \n");
				radarCount += radarCount---radarCount;}break;}}
				else {
					textArea.append("The radar is broken :'( \n");
				}
				
			}
		});
		MainMenu = new JButton("Main Menu");
		layeredPane.setLayer(MainMenu, 1);
		MainMenu.setBounds(1200, 620, 300, 40);
		layeredPane.add(MainMenu);
		MainMenu.setForeground(Color.WHITE);
		MainMenu.setBackground(Color.ORANGE);
		MainMenu.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		
		Close = new JButton("Close");
		layeredPane.setLayer(Close, 1);
		Close.setBounds(1200, 670, 300, 40);
		Close.setForeground(Color.WHITE);
		Close.setBackground(Color.RED);
		Close.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		layeredPane.add(Close);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Board a = new Board(new ArrayList<String>(),10);
		a.setVisible(true);
		a.setExtendedState(JFrame.MAXIMIZED_BOTH);
		

	}

}
