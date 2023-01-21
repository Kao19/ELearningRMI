import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ELearningGUI extends UnicastRemoteObject implements IELearning{
    
    JFrame fenetre;

    JPanel panelBoard;
    Color couleurEcriture = Color.black;
    IPlateforme broad;
    Color[] listeCouleurs = { Color.black, Color.white, Color.blue, Color.green, Color.red };

    JPanel panelRoom;

    public ELearningGUI(IPlateforme broad) throws RemoteException{
        
        this.broad = broad;
        panelBoard = new JPanel();
        panelBoard.setBackground(Color.white);
        panelBoard.setPreferredSize(new Dimension(400,600));
        

        panelRoom = new JPanel();
        panelRoom.setBackground(Color.black);
        panelRoom.setPreferredSize(new Dimension(400,600));
        
        JTextField input = new JTextField(35);
        input.setBounds(20,40,200,30);


        JPanel colors = afficherListesCouleur();

        
        JPanel inputANDcolors = new JPanel();
        inputANDcolors.setPreferredSize(new Dimension(400,30));
        inputANDcolors.add( colors, BorderLayout.WEST);
        inputANDcolors.add(Box.createHorizontalStrut(77));
        inputANDcolors.add( input, BorderLayout.EAST);
        

        JPanel p = new JPanel();
        p.setLayout( new BorderLayout() );
        p.add( panelBoard, BorderLayout.CENTER);
        p.add( panelRoom, BorderLayout.EAST);
        p.add(inputANDcolors, BorderLayout.SOUTH);
        
        fenetre = new JFrame();                               
        fenetre.setContentPane(p);
        fenetre.pack();
        fenetre.setVisible( false );
        fenetre.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
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
        Graphics graphic = panelBoard.getGraphics();
        graphic.setColor(p.c);
        graphic.fillRect(p.x, p.y, 7, 7);
    }

    @Override
    public String SendMessage(Plateform pl) throws RemoteException {
        return "success";
    }

}
