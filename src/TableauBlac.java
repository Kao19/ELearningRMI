import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TableauBlac extends UnicastRemoteObject implements ITableauBlac{
    
    JPanel panel;
    JFrame fenetre;
    Color couleurEcriture = Color.black;
    IBroadcastTableauBlanc broad;
    Color[] listeCouleurs = { Color.black, Color.white, Color.blue, Color.green, Color.red };

    public TableauBlac(IBroadcastTableauBlanc broad) throws RemoteException{
        this.broad = broad;
        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(300,300));
        
        JPanel colors = afficherListesCouleur();
        JPanel p = new JPanel();
        p.setLayout( new BorderLayout() );
        p.add( panel, BorderLayout.CENTER);
        p.add( colors, BorderLayout.SOUTH);
        
        fenetre = new JFrame();                               
        fenetre.setContentPane(p);
        fenetre.pack();
        fenetre.setVisible( false );
        fenetre.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        panel.addMouseMotionListener(new MouseMotionListener(){

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
                Pixel pix = new Pixel();
                pix.x = e.getX();
                pix.y = e.getY();
                pix.c = couleurEcriture;
                try {
                    broad.diffuserContenu(pix);
                } catch (RemoteException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

        });
    }

    public void setVisibleFrame(){
        fenetre.setVisible(true);
    }

    public JPanel afficherListesCouleur(){
        final JPanel tmp = new JPanel(){
            public void paint(Graphics g){
                for (int i = 0; i < listeCouleurs.length; i++) {
                    int dx = (int) ((300.0/listeCouleurs.length) * i);
                    int tx = (int) ((300.0/listeCouleurs.length));
                    g.setColor( listeCouleurs[i] );
                    if ( ! listeCouleurs.equals(listeCouleurs[i]) )
                        g.fillRect(dx, 0, tx, 20);
                    else
                        g.drawRect(dx, 0, tx, 20);
                }
            }  
        };
        tmp.setPreferredSize(new Dimension(300,20));
    
        tmp.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                couleurEcriture = listeCouleurs[(int) ((e.getX())/(300./listeCouleurs.length))];
                tmp.repaint();   
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

        });
        return tmp;
    }

    @Override
    public void afficherContenu(Pixel p) throws RemoteException {
        // TODO Auto-generated method stub
        Graphics graphic = panel.getGraphics();
        graphic.setColor(p.c);
        graphic.fillRect(p.x, p.y, 7, 7);
    }
}
