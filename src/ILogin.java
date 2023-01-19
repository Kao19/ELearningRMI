import java.rmi.*;

public interface ILogin extends Remote {
    public Boolean login(String username, String password) throws RemoteException;
}
