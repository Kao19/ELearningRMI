import java.rmi.*;
import java.util.ArrayList;

public interface ILogin extends Remote {
    public Boolean login(String username, String password) throws RemoteException;
    public Boolean loginAdmin(String username, String password) throws RemoteException;
    public Boolean isProfessor(String username) throws RemoteException;
    public ArrayList<String> listeClassesEtudiant(String u, String p) throws RemoteException;
    public String[] profETclasse(String u, String p) throws RemoteException;
}
