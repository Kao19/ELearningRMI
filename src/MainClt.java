import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class MainClt {
    public static void main(String[] args) {
        String url = "rmi://127.0.0.1/tableauBlanc";
        try {
            IBroadcastTableauBlanc broad = (IBroadcastTableauBlanc) Naming.lookup(url);
            TableauBlac tab = new TableauBlac(broad);
            broad.enregistrerContenu(tab);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
