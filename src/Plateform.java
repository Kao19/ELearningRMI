import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Plateform extends UnicastRemoteObject implements IPlateforme {

    private ArrayList<IELearning> tableau;
    private ArrayList<Pixel> p;

    
    protected Plateform() throws RemoteException {
        tableau = new ArrayList<IELearning>();
        p = new ArrayList<Pixel>();
    }


    @Override
    public void diffuserContenu(Pixel p) throws RemoteException {
        // TODO Auto-generated method stub
        this.p.add(p);

        for (IELearning s: this.tableau) {
            try {
                s.afficherContenu(p);
            }catch (Exception e)
            {
                this.tableau.remove(s);
            }
        }
    }


    @Override
    public void enregistrerContenu(IELearning tab) throws RemoteException {
        for(Pixel s : this.p)
        {
            tab.afficherContenu(s);
        }
        this.tableau.add(tab);
    }

    
}
