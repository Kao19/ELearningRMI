import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class MainServer {
    public static void main(String[] args) throws RemoteException{

        Login log = new Login();

        Plateform plateform = new Plateform();

        LocateRegistry.createRegistry(1099);
        try {
            
            Naming.rebind("login", log);

            Naming.rebind("launchPlateform", plateform);

            System.out.println("objet distant cree");
        } catch (MalformedURLException e) {
            // TODO: handle exception
        }
    }
}
