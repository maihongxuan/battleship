package headsup;

import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class playersList implements ListModel {

	private ArrayList <String> waitingClients =  new ArrayList <String> ();
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return waitingClients.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return waitingClients.get(index);
	}
	
	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}
	public void add (String name ) {
		waitingClients.add(name);
	}
	public void remove (String name) {
		waitingClients.remove(name);
	}
	public void reset () {
		waitingClients = new ArrayList <String> (); 
		
	}

}
