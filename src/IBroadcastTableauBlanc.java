import java.rmi.*;

public interface IBroadcastTableauBlanc extends Remote{
    void diffuserContenu(Pixel p) throws RemoteException;
    void enregistrerContenu(ITableauBlac tab) throws RemoteException;
}
