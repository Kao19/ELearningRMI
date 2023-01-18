import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

public class BroadcastTableauBlanc extends UnicastRemoteObject implements IBroadcastTableauBlanc{
    
    private ArrayList<ITableauBlac> tableau;
    private ArrayList<Pixel> p;

    public BroadcastTableauBlanc() throws RemoteException{
        tableau = new ArrayList<ITableauBlac>();
        p = new ArrayList<Pixel>();
    }

    @Override
    public void diffuserContenu(Pixel p) throws RemoteException {
        // TODO Auto-generated method stub
        this.p.add(p);

        for(int i=0; i<tableau.size(); i++){
            tableau.get(i).afficherContenu(p);
        }
    }

    @Override
    public void enregistrerContenu(ITableauBlac tab) throws RemoteException {
        // TODO Auto-generated method stub
        for(int i=0;i<p.size();i++){
            tab.afficherContenu(p.get(i));
        }
        this.tableau.add(tab);
    }

    

}
