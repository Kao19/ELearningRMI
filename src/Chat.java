import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import java.io.*;

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

	public void send(String s) throws RemoteException{
		System.out.println(s);
		JTextArea msg = new JTextArea(s);
		Chat.panelRoom.add(msg);
		StyledDocument doc = Chat.panelRoom.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), s + "\n", null);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
    
	Connection con;
	String className = "";
    public void sendToALL(String msg) throws RemoteException{
		String[] splitMsg = msg.split(":", 2);
		String db = "jdbc:mysql://localhost:3306/elearning";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, "root", "");          
        }catch(Exception e){
            e.printStackTrace();
        }
        String query = "SELECT class FROM elearning.user WHERE username = '" + splitMsg[0].trim() + "'";
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                className = rs.getString("class");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
		for (int i = 0; i < clts.size(); i++) {
            System.out.println(msg);
			//clts.get(i).send(msg);
			String query2 = "SELECT class FROM elearning.user WHERE username = '" + clts.get(i).getName() + "' AND class = '" + className + "'";
			try{
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery(query2);
				if(rs2.next()){
					clts.get(i).send(msg);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
    }

	public byte[] downloadFile(String fileName)
	{
		try
		{
			File file = new File(fileName);
			byte buffer[] = new byte[(int)file.length()];
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
			input.read(buffer,0,buffer.length);
			input.close();
			sendToALL("professeur a envoye ce document: " + file.getName());
			return(buffer);
		}
		catch(Exception e)
		{
			System.out.println("FileImpl: "+e.getMessage());
			e.printStackTrace();
			return(null);
		}
	}
   
}
