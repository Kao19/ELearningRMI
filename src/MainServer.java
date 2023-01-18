import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class MainServer {
    public static void main(String[] args) throws RemoteException{
        BroadcastTableauBlanc b = new BroadcastTableauBlanc();
        LocateRegistry.createRegistry(1099);
        try {
            Naming.rebind("tableauBlanc", b);
            System.out.println("objet distant cree");
        } catch (MalformedURLException e) {
            // TODO: handle exception
        }
    }
}
