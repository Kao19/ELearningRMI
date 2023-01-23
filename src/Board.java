import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Board extends UnicastRemoteObject implements IBoard {

    private ArrayList<IELearning> tableau;
    private ArrayList<Pixel> p;

    
    protected Board() throws RemoteException {
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
