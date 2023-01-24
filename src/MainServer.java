import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

public class MainServer {
    public static void main(String[] args) throws RemoteException{

        Login log = new Login();

        Board plateform = new Board();

        Chat server = new Chat("server",null);

        Admin ad = new Admin();

        LocateRegistry.createRegistry(1099);
        try {
            
            Naming.rebind("login", log);

            Naming.rebind("launchPlateform", plateform);

            Naming.rebind("CHAT", server);

            Naming.rebind("admin", ad);

            System.out.println("objet distant cree");
        } catch (MalformedURLException e) {
            // TODO: handle exception
        }
    }
}
