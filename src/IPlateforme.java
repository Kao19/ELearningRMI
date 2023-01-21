import java.rmi.*;

public interface IPlateforme extends Remote{

    void diffuserContenu(Pixel p) throws RemoteException;
    
    void enregistrerContenu(IELearning tab) throws RemoteException;
}

