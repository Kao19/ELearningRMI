import java.rmi.*;

public interface IBoard extends Remote{

    void diffuserContenu(Pixel p) throws RemoteException;
    
    void enregistrerContenu(IELearning tab) throws RemoteException;
}

