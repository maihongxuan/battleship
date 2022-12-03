package headsup;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;

public class Server {
 	static Map <String,Client > Clients = new HashMap<String ,	Client > ();
 	static String s = "<<";
	static ArrayList <Client> waitingClients = new  ArrayList <Client> ();
	public ArrayList <String> grid = new ArrayList <String> ();
	private ServerSocket serverSocket;
	private Socket socket; 
	private String list;
	static int n = 10;
	public static void main (String [] args ) {
		Server Server = new Server();
		while (true) {
		try {
			
			
			Server.socket = Server.serverSocket.accept();
			System.out.println ("ClientAdded");
			new Client (Server,Server.socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
	public void sendWaitingList () throws IOException  {
		String list = "!!!";
		for (Client c  : waitingClients) {
			list += c.userName + "\\" ;  
		}
		for (Client c : waitingClients) {
			synchronized (c) {
				c.out.writeObject (list);
				System.out.println ("sending to " + c.userName);
				c.out.flush ();
			}}
		}
		
	
	public ServerBSS () {
//		gridmaker(n);
		try {
			serverSocket = new ServerSocket (4999);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Waiting for clients");
	} 
	public void removeClient (Client c) {
		synchronized (this) {
			Clients.remove(c);
		} 
	}
	
	public void send (Client c, String message) {
		ObjectOutputStream output = Clients.get(c).out;
		try {
			output.writeObject(message);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String read (ObjectInputStream in) throws ClassNotFoundException, IOException {
			
		String read = (String) in.readObject();
		
		return read; }
	private void gridmaker (int n) {
		char index = 'A' ; 
		for (int i = 0; i < n ; i++ ) {
			for (int j = 0; j <n; j++) {
				grid.add('#' +"" +  index +  "" + j);
				
			}
			index ++; 
		}
	}
	}
		
		
	
	
	

