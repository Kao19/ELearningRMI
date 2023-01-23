import java.rmi.*;
import java.util.ArrayList;

import javax.swing.JTextPane;

public interface IChat extends Remote{
	public String getName() throws RemoteException;
	public void send(String s, JTextPane p) throws RemoteException;
	public void setClient(IChat c) throws RemoteException;
	public IChat getClient() throws RemoteException;
    public void sendToALL(String msg, JTextPane p) throws RemoteException;
	public JTextPane getPanelRoom() throws RemoteException;
}
