package headsup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.AbstractListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class SetShip extends JFrame{
	private JPanel contentPane;
	HashMap<String, JToggleButton> ButtonIndex = new HashMap <String,JToggleButton>();
	ArrayList<String> shipsLocation = new ArrayList<String>();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	shipList shipList = new shipList();
	private int shipSize = 0;
	private int listIndex = 5;
	
	private boolean horizontal = true;
	static SetShips frame;
	JButton btnPlay;
	JButton Back;
	JButton Exit;
	JButton OExit;
	JLayeredPane layeredPane;
	boolean Online;
	JLabel black;
	JLabel lbNewLable_3;
	JLabel background;
	private JLabel lbNewLabel;
	JLabel timee;
	JLabel txt;
	int n;
	public SetShip(int n)
	{
		this.n = n;
		setIconImage(Toolkit.getDefaultToolkit().getImage(SetShip.class.getResource("/headsup/Logo.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1920,1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1920, 1080);
		layeredPane.setLayout(null);
		
		JPanel panel = new JPanel();
		layeredPane.setLayer(panel, 1);
		panel.setBounds(230, 250, 620, 520);
		layeredPane.add(panel);
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(n,n,0,0));
		
		initiateShips();
		setGird(panel);
		addElements(layeredPane);
		
		black = new JLabel("");
		black.setIcon(new ImageIcon(SetShip.class.getResource("/headsup/image_ship.jpg")));
		layeredPane.setLayer(black,2);
		black.setBounds(0, 0, 1900, 1030);
		
		timee = new JLabel();
		timee.setForeground(Color.ORANGE);
		timee.setFont(new Font("Snap ITC", Font.PLAIN,40));
		layeredPane.setLayer(timee, 1);
		
		txt = new JLabel("Waitting for Opponent ...");
		txt.setForeground(Color.YELLOW);
		layeredPane.setLayer(txt, 3);
		txt.setFont(new Font("Snap ITC", Font.PLAIN,60));
		txt.setBounds(423, 350, 970, 90);
		
		timee.setBounds(360, 280, 96, 69);
		//
		
	}
	public void setListIndex()
	{
		if(n == 8)
		{
			listIndex = 4;
		}
		else if(n == 10)
		{
			listIndex = 5;
		}
		else if(n == 12)
		{
			listIndex = 6;
		}
	}
	public boolean valid(String location,boolean horizontal,int shipsize,ArrayList<String> shipsLocation)
	{
		boolean rs = false;
			switch(n){
			case 8:{
				char firstIndex = location.charAt(0);
				int secondIndex = Integer.parseInt(location.substring(1));
				if(horizontal)
				{
					for(int  i = 0 ; i<shipsize ;i++)
					{
						if(shipsLocation.contains(location.charAt(0)+ "" +(char) (location.charAt(1)+i))) {
							rs = false;	
							return rs;
						
						}
					}
					for(int i = (secondIndex-1) ; i < (shipsize + secondIndex +1 ); i++ )
					{
						if(shipsLocation.contains((char)(firstIndex-1)+""+i))
						{
							rs = false;
							return rs;
						}
						if(shipsLocation.contains((char)(firstIndex+1)+""+i))
						{
							rs = false;
							return rs;
						}
					}
					if(shipsLocation.contains((char)(firstIndex)+""+(secondIndex-1))||shipsLocation.contains((char)(firstIndex)+""+(secondIndex+shipsize)))
					{
						rs = false;
						return rs;
					}
					rs =  ((char)('7'-shipsize+1) - location.charAt(1) >=0);
				}	
				else
				{
					for(int  i =0 ; i < shipsize ; i++)
					{
						if(shipsLocation.contains((char)(location.charAt(0) + i) + ""+(location.charAt(1)))) {
							rs = false;	
							return rs;
						}
					}
					for(char i =(char) (firstIndex-1) ; i < (shipsize + firstIndex +1 ); i++ )
					{
						if(shipsLocation.contains((char)i+""+(secondIndex-1)))
						{
							rs = false;
							return rs;
						}
						if(shipsLocation.contains((char)i+""+(secondIndex+1)))
						{
							rs = false;
							return rs;
						}
					}
					if(shipsLocation.contains((char)(firstIndex-1)+""+(secondIndex))||shipsLocation.contains((char)(firstIndex+shipsize)+""+(secondIndex)))
					{
						rs = false;
						return rs;
					}
					rs = (((char)('H'- shipsize + 1) - location.charAt(0))>=0);
				}
				break;
				
			}
			case 10 : 
			{
				char firstIndex = location.charAt(0);
				int secondIndex = Integer.parseInt(location.substring(1));
				
				if(horizontal)//theo chiều ngang
				{
					for(int  i = 0 ; i<shipsize ;i++)
					{
						if(shipsLocation.contains(location.charAt(0)+""+(char) (location.charAt(1) +i))){
							rs = false;	
							return rs;
						
						}
					}
					for(int i = (secondIndex-1) ; i < (shipsize + secondIndex +1 ); i++ )
					{
						if(shipsLocation.contains((char)(firstIndex-1)+""+i))
						{
							rs = false;
							return rs;
						}
						if(shipsLocation.contains((char)(firstIndex+1)+""+i))
						{
							rs = false;
							return rs;
						}
					}
					if(shipsLocation.contains((char)(firstIndex)+""+(secondIndex-1))||shipsLocation.contains((char)(firstIndex)+""+(secondIndex+shipsize)))
					{
						rs = false;
						return rs;
					}
					rs = ((char)('9'-shipsize+1) - location.charAt(1) >=0);
				}	
				else
				{
					for(int  i =0 ; i < shipsize ; i++)
					{
						if(shipsLocation.contains((char)(location.charAt(0) + i) + ""+(location.charAt(1)))) {
							rs = false;	
							return rs;
						
						}
					}
					for(char i =(char) (firstIndex-1) ; i < (shipsize + firstIndex +1 ); i++ )
					{
						if(shipsLocation.contains((char)i+""+(secondIndex-1)))
						{
							rs = false;
							return rs;
						}
						if(shipsLocation.contains((char)i+""+(secondIndex+1)))
						{
							rs = false;
							return rs;
						}
					}
					if(shipsLocation.contains((char)(firstIndex-1)+""+(secondIndex))||shipsLocation.contains((char)(firstIndex+shipsize)+""+(secondIndex)))
					{
						rs = false;
						return rs;
					}
					rs = (((char)('J'- shipsize + 1) - location.charAt(0))>=0);
				}
				break;
			}
			case 12 :
			{
				char firstIndex = location.charAt(0);
				int secondIndex = Integer.parseInt(location.substring(1));
				if(horizontal)// đang theo chiều ngang
				{
					for(int  i = 0 ; i<shipsize ;i++)//
					{
						if(shipsLocation.contains(location.charAt(0)+""+(Integer.parseInt(location.substring(1))+i))){//đã có tàu rồi
							rs = false;	
							return rs;
						
						}
					}
					//chặn xung quanh con thuyền
					for(int i = (secondIndex-1) ; i < (shipsize + secondIndex +1 ); i++ )
					{
						if(shipsLocation.contains((char)(firstIndex-1)+""+i))
						{
							rs = false;
							return rs;
						}
						if(shipsLocation.contains((char)(firstIndex+1)+""+i))
						{
							rs = false;
							return rs;
						}
					}
					if(shipsLocation.contains((char)(firstIndex)+""+(secondIndex-1))||shipsLocation.contains((char)(firstIndex)+""+(secondIndex+shipsize)))
					{
						rs = false;
						return rs;
					}
					rs = ((char)(11 - shipsize + 1) -  Integer.parseInt(location.substring(1)) >=0);// cái này bị sai nè viết lại làm sao cho đúng??
				}	
				else
				{
					for(int  i =0 ; i < shipsize ; i++)
					{
						if(shipsLocation.contains((char)(location.charAt(0) + i) + ""+Integer.parseInt(location.substring(1)))) {
							rs = false;	
							return rs;
						
						}
					}
					for(char i =(char) (firstIndex-1) ; i < (shipsize + firstIndex +1 ); i++ )
					{
						if(shipsLocation.contains((char)i+""+(secondIndex-1)))
						{
							rs = false;
							return rs;
						}
						if(shipsLocation.contains((char)i+""+(secondIndex+1)))
						{
							rs = false;
							return rs;
						}
					}
					if(shipsLocation.contains((char)(firstIndex-1)+""+(secondIndex))||shipsLocation.contains((char)(firstIndex+shipsize)+""+(secondIndex)))
					{
						rs = false;
						return rs;
					}
					rs = (((char)('L'- shipsize + 1) - location.charAt(0))>=0);
				}
				break;
			}
		}
			return rs;
	}
	public void setGird(JPanel panel)
	{
		char loopy = 'A';
		for(int i =0;i < n ; i++)
		{
			for(int j = 0;j < n ;j++)//0->11
			{
				final JToggleButton button = new JToggleButton("");
				button.setOpaque(false);
				String index = loopy + "" + (j);
//				System.out.print(index);
				button.setName(index);
//				System.out.println(button.getText());
				ButtonIndex.put(index, button);
				panel.add(button);
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						button.setSelected(false);
						boolean valid =true;
						if(shipSize ==0) valid = false;
						
						char firstIndex = button.getName().charAt(0);
						int secondIndex = Integer.parseInt(button.getName().substring(1));
						System.out.println("first : " + firstIndex);
						
						System.out.println("second : " + secondIndex);

						// la chuyen tu :
					
						valid = valid(firstIndex +""+ secondIndex,horizontal,shipSize,shipsLocation);
						System.out.println(valid);
						if(valid)
						{
							for(int i =0 ; i< shipSize ; i++)
							{
								
								shipsLocation.add(firstIndex + "" + secondIndex);
//								System.out.println(firstIndex + "" + secondIndex);
								
								JToggleButton current = ButtonIndex.get(firstIndex + "" + secondIndex);
								current.setSelected(true);
								current.setIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));
								current.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));
								current.setEnabled(false);
								if(horizontal) {secondIndex++;}
								else {
									firstIndex++;
								}
								shipsLocation.add("/");
							}
						shipList.remove(listIndex);
						setListIndex();
						shipSize = 0;
						repaint();
						}
						
					}
				});
			}
			loopy+=1;
		}
	}
	public void addElements(JLayeredPane layeredPane)
	{
		btnPlay = new JButton("Play");
		layeredPane.setLayer(btnPlay, 1);
		btnPlay.setBounds(1150, 288, 228, 39);
		layeredPane.add(btnPlay);
		btnPlay.setBackground(Color.GREEN);
		btnPlay.setFont(new Font("Snap ITC",Font.BOLD,23));
		background = new JLabel("");
		background.setBounds(0, 0, 1920, 1080);
		background.setIcon(new ImageIcon(Board.class.getResource("/headsup/image_ship.jpg")));
		layeredPane.add(background);
		
		contentPane.setLayout(null);
		contentPane.add(layeredPane);
		
		JButton btnPlay_1 = new JButton("Random");
		layeredPane.setLayer(btnPlay_1, 1);
		btnPlay_1.setFont(new Font("Snap ITC",Font.BOLD,23));
		btnPlay_1.setBackground(Color.ORANGE);
		btnPlay_1.setBounds(1150, 388, 228, 39);
		layeredPane.add(btnPlay_1);
		btnPlay_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				random();
			}
		});
		Exit = new JButton("Exit");
		Exit.setBackground(Color.RED);
		Exit.setFont(new Font("Snap ITC",Font.BOLD,23));
		layeredPane.setLayer(Exit, 1);
		Exit.setBounds(1150, 488, 228, 39);
		layeredPane.add(Exit);
		
		OExit = new JButton("Exit");
		OExit.setFont(new Font("Snap ITC",Font.BOLD,23));
		layeredPane.setLayer(OExit, 2);
		OExit.setBackground(Color.RED);
		OExit.setBounds(1150, 540, 228, 39);
		
		JLabel lbNewLabel_1 = new JLabel("Help");
		lbNewLabel_1.setForeground(Color.ORANGE);
		lbNewLabel_1.setFont(new Font("Snap ITC",Font.PLAIN,30));
		lbNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(null, "You should arrange your ships on the grid in prepration for the battle \n" +   
						"Choose the formation wisely, May the force be with you", "Crew advice ", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (Board.class.getResource("/headsup/madam.png")));	
			}
		});
		layeredPane.setLayer(lbNewLabel_1, 1);
		lbNewLabel_1.setIcon(new ImageIcon(SetShip.class.getResource("/headsup/help.png")));
		lbNewLabel_1.setBounds(27,13,131,80);
		layeredPane.add(lbNewLabel_1);
		
		JButton reset = new JButton("Reset");
		layeredPane.setLayer(reset, 1);
		reset.setBounds(1150, 338, 228, 39);
		layeredPane.add(reset);
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset();
			}
		});
		reset.setFont(new Font("Snap ITC", Font.BOLD,25));
		reset.setBackground(Color.YELLOW);
		
		Back = new JButton("Back");
		layeredPane.setLayer(Back, 1);
		Back.setFont(new Font("Snap ITC", Font.BOLD,23));
		Back.setBackground(new Color(255, 102, 102));
		Back.setBounds(1150, 438, 228, 39);
		layeredPane.add(Back);
		
		lbNewLabel = new JLabel("");
		layeredPane.setLayer(lbNewLabel, 1);
		lbNewLabel.setIcon(new ImageIcon(SetShip.class.getResource("/headsup/NewLogo.png")));
		lbNewLabel.setBounds(350, 27, 940, 312);
		layeredPane.add(lbNewLabel);
		
		JRadioButton Horizontal = new JRadioButton("Horizontal");
		layeredPane.setLayer(Horizontal,1);
		Horizontal.setBounds(900, 616, 248, 30);
		layeredPane.add(Horizontal);
		Horizontal.setBackground(new Color(0,0,51));
		Horizontal.setFont(new Font("Snap ITC",Font.PLAIN,30));
		Horizontal.setForeground(Color.WHITE);
		Horizontal.setSelected(true);
		Horizontal.setOpaque(false);
		Horizontal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				horizontal = true;
			}
		});
		buttonGroup.add(Horizontal);
		
		JRadioButton Vertical = new JRadioButton("Vertical");
		layeredPane.setLayer(Vertical, 1);
		Vertical.setBounds(900, 659, 203, 25);
		layeredPane.add(Vertical);
		Vertical.setOpaque(false);
		Vertical.setBackground(new Color(0,0,51));
		Vertical.setFont(new Font("Snap ITC",Font.PLAIN,30));
		Vertical.setForeground(Color.WHITE);
		Vertical.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				horizontal = false;
			}
		});
		
		buttonGroup.add(Vertical);
		final JList list = new JList(shipList);
		layeredPane.setLayer(list, 1);
		list.setBounds(900, 281, 203, 298);
		layeredPane.add(list);
		
		list.setForeground(Color.WHITE);
		list.setBackground(new Color(0, 0, 51));
		//Matte Border là một dạng cho phép chúng ta sử dụng màu hoặc hình ảnh tạo ra đường viền.
		list.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		list.setSelectedIndex(0);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(shipList.getElementAt(list.getSelectedIndex()).equals("Ship 4"))
						shipSize = 4;
				else if(shipList.getElementAt(list.getSelectedIndex()).equals("Ship 3"))
					shipSize = 3;
				else if(shipList.getElementAt(list.getSelectedIndex()).equals("Ship 2"))
					shipSize = 2;
			listIndex = list.getSelectedIndex();
			}
		});
		list.setFont(new Font("Snap ITC" , Font.PLAIN,23));
	}
	public void random()
	{
		reset();
		shipList.empty();
		shipsLocation = shipGenerator.generateShip(n);
		for(String location : shipsLocation)
		{
			if(!location.equals("/")) {
				ButtonIndex.get(location).setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));
				ButtonIndex.get(location).setIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));
			}
		}
	}
	public void reset()
	{
		shipsLocation = new ArrayList<String>();
		shipsLocation.add("/");
		shipList.empty();
		switch (n) {
			case 8:
			{
				shipsLocation.add("/");
				shipList.add("Ship 4");
				shipList.add("Ship 3");
				shipList.add("Ship 3");
				shipList.add("Ship 2");
				break;	
			}
			case 10:
			{
				shipsLocation.add("/");
				shipList.add("Ship 4");
				shipList.add("Ship 4");
				shipList.add("Ship 3");
				shipList.add("Ship 3");
				shipList.add("Ship 2");
				break;	
			}
			case 12:
			{
				shipsLocation.add("/");
				shipList.add("Ship 4");
				shipList.add("Ship 4");
				shipList.add("Ship 3");
				shipList.add("Ship 3");
				shipList.add("Ship 2");
				shipList.add("Ship 2");
				break;
			}
		}
		repaint();// dùng để cập nhật lại vị trí của mỗi điểm
		
		char loopy = 'A';
		Icon icon  = new ImageIcon();
		for(int i = 0; i < n ;i++)
		{
			for(int j =0; j < n ; j++)
			{
				String index = loopy + "" + j;
				JToggleButton current = ButtonIndex.get(index);
				current.setSelected(false);
				current.setEnabled(true);
				current.setIcon(icon);
			}
			loopy++;
		}
		
	}
	private void initiateShips()
	{
		switch (n) {
			case 8:
			{
				shipsLocation.add("/");
				shipList.add("Ship 4");
				shipList.add("Ship 3");
				shipList.add("Ship 3");
				shipList.add("Ship 2");
				break;	
			}
			case 10:
			{
				shipsLocation.add("/");
				shipList.add("Ship 4");
				shipList.add("Ship 4");
				shipList.add("Ship 3");
				shipList.add("Ship 3");
				shipList.add("Ship 2");
				break;	
			}
			case 12:
			{
				shipsLocation.add("/");
				shipList.add("Ship 4");
				shipList.add("Ship 4");
				shipList.add("Ship 3");
				shipList.add("Ship 3");
				shipList.add("Ship 2");
				shipList.add("Ship 2");
				break;
			}
		}
	}
	public static void main(String[] args) {
		SetShip a = new SetShip(12);
		a.setVisible(true);
		
	}
}
