import java.rmi.*;

public interface IELearning extends Remote{
    
    void afficherContenu(Pixel p) throws RemoteException;
    
	public String SendMessage(Plateform p) throws RemoteException;
}
