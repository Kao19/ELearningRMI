import java.rmi.*;

public interface ITableauBlac extends Remote{
    void afficherContenu(Pixel p) throws RemoteException;
}
