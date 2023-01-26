import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class ELearningGUI extends UnicastRemoteObject implements IELearning{
    
    JFrame fenetre;

    JPanel panelBoard;
    Color couleurEcriture = Color.black;
    IBoard broad;
    Color[] listeCouleurs = { Color.black, Color.white, Color.blue, Color.green, Color.red };


    public ELearningGUI(IBoard broad) throws RemoteException{

        this.broad = broad;
        panelBoard = new JPanel();
        panelBoard.setBackground(Color.white);
        panelBoard.setPreferredSize(new Dimension(200,400));

        JPanel colors = afficherListesCouleur();

        JPanel p = new JPanel();
        p.setLayout( new BorderLayout() );
        p.add( panelBoard, BorderLayout.CENTER);
        p.add( colors, BorderLayout.SOUTH);
        
        fenetre = new JFrame();                               
        fenetre.add(p, BorderLayout.WEST);
        fenetre.setVisible( false );
        fenetre.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);

        panelBoard.addMouseMotionListener(new MouseMotionListener(){

            @Override
            public void mouseDragged(MouseEvent e) {
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

    public void setVisibleFrame(JPanel p1){
        fenetre.setVisible(true);
        fenetre.add(p1, BorderLayout.EAST);
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
    }

    public JPanel afficherListesCouleur(){
        final JPanel tmp = new JPanel(){
            public void paint(Graphics g){
                super.paint(g);
                for (int i = 0; i < listeCouleurs.length; i++) {
                    int dx = (int) ((200.0/listeCouleurs.length) * i);
                    int tx = (int) ((200.0/listeCouleurs.length));
                    g.setColor( listeCouleurs[i] );
                    if ( ! listeCouleurs.equals(listeCouleurs[i]) )
                        g.fillRect(dx, 10, tx, 20);
                    else
                        g.drawRect(dx, 10, tx, 20);
                }
            }  
        };
        tmp.setPreferredSize(new Dimension(200,30));
    
        tmp.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                couleurEcriture = listeCouleurs[(int) ((e.getX())/(200./listeCouleurs.length))];
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
        Graphics graphic = panelBoard.getGraphics();
        graphic.setColor(p.c);
        graphic.fillRect(p.x, p.y, 7, 7);
    }
}
