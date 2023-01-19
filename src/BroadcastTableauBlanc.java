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

        for (ITableauBlac s: this.tableau) {
            try {
                s.afficherContenu(p);
            }catch (Exception e)
            {
                this.tableau.remove(s);
            }
        }
    }

    @Override
    public void enregistrerContenu(ITableauBlac tab) throws RemoteException {
        for(Pixel s : this.p)
        {
            tab.afficherContenu(s);
        }
        this.tableau.add(tab);
    }

    

}
