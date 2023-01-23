import java.rmi.*;

public interface ILogin extends Remote {
    public Boolean login(String username, String password) throws RemoteException;
    public Boolean isProfessor(String username) throws RemoteException;
}
