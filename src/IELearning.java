import java.rmi.*;

public interface IELearning extends Remote{
    
    void afficherContenu(Pixel p) throws RemoteException;
    
	public void SendMessage() throws RemoteException;
    
}
