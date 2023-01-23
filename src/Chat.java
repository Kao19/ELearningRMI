import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import java.awt.*;

public class Chat extends UnicastRemoteObject implements IChat{
    
    public String name;
	public IChat client = null;
    static public int count = 0;
    public ArrayList<IChat> clts = new ArrayList<IChat>();

	static public JTextPane panelRoom;
    public JTextField input;
    
	public Chat(String n, JTextPane p) throws RemoteException { 
		this.name = n; 
		panelRoom = p;
	}

	public JTextPane getPanelRoom() throws RemoteException {
		return panelRoom;
	}

	public String getName() throws RemoteException {
		return this.name;
	}

    public static int getCount() throws RemoteException {
		return count;
	}
 
	public void setClient(IChat c){
		client = c;
        count++;
        clts.add(c);
	}
 
	public IChat getClient(){
		return client;
	}
 
    public ArrayList<IChat> getClts(){
		return clts;
	}

	public void send(String s, JTextPane p) throws RemoteException{
		System.out.println(s);
		Chat.panelRoom.add(new JTextArea(s));
		StyledDocument doc = Chat.panelRoom.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), s + "\n", null);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
	}
    
    public void sendToALL(String msg, JTextPane p) throws RemoteException{
		for (int i = 0; i < clts.size(); i++) {
            System.out.println(msg);
            //clts.get(i).send(msg);
			clts.get(i).send(msg,p);
		}
    }
   
}
