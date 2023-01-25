import java.rmi.*;
import java.io.*;
import java.util.*;

public interface IAdmin extends Remote {
    
    public boolean addClasse(String name) throws RemoteException;
    public boolean insertUser(String username, String password, String profile, String classe) throws RemoteException;
    public ArrayList<String> listOfClasses() throws RemoteException;
}
