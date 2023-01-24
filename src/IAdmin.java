import java.rmi.*;

public interface IAdmin extends Remote {
    
    public boolean addClasse(String name) throws RemoteException;
    public boolean addProf(String username, String password) throws RemoteException;
    public boolean addetudiant(String username, String password) throws RemoteException;

}
