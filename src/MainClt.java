import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;

import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.filechooser.*;

public class MainClt extends JFrame implements ActionListener {

    JPanel panel;
    JTextPane panelRoom;
    JTextField input;
    JFrame chatroom;
	JFrame frame;
    JLabel userLabel, passLabel;  
    JTextField  textField1, textField2; 
    JButton b1;
    JButton login;
	JButton register;
    
    String urlLogin = "rmi://127.0.0.1/login";
    ILogin log = (ILogin) Naming.lookup(urlLogin);

	MainClt() throws MalformedURLException, RemoteException, NotBoundException{
        
        LoginForm();
        
	}


    public void LoginForm(){

		frame = new JFrame("Login");
 
      	panel = new JPanel();	
		frame.add(panel);

        userLabel = new JLabel();  
        userLabel.setText("Username");      
        
        textField1 = new JTextField(15); 
        
        passLabel = new JLabel();  
        passLabel.setText("Password");      
        
        textField2 = new JPasswordField(15);    
        
        b1 = new JButton("login");
            
        panel.add(userLabel);      
        panel.add(textField1);     
        panel.add(passLabel);      
        panel.add(textField2);     
        panel.add(b1);             
        
        
        add(panel);  
        
        
        this.setSize(640,440); 
        setLocationRelativeTo(null);
        
        b1.addActionListener(this);       
        setTitle("LOGIN FORM");         

        this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		JButton actionSource = (JButton) e.getSource();

        if(actionSource.equals(b1)){
            
            try {
                System.out.println(log.login(textField1.getText(), textField2.getText()));
                if(log.login(textField1.getText(), textField2.getText())){
                    this.setVisible(false);
                    this.plateform();   
                }
                else{
                    int input = JOptionPane.showOptionDialog(null, "login or password wrong", "fail login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    if(input == JOptionPane.OK_OPTION)
                    {
                        this.setVisible(false);
                        MainClt cl = new MainClt();
                    }
                }
            } catch (Exception ex) {
                
            }   
        }
        
    }
	
    public void plateform(){
        try {    

            String urlPlateform = "rmi://127.0.0.1/launchPlateform";
            
            IBoard plt = (IBoard) Naming.lookup(urlPlateform);    
            ELearningGUI gui = new ELearningGUI(plt);
            gui.setVisibleFrame(chatRoomGui());
            plt.enregistrerContenu(gui);
                       
        } catch (Exception excep) {
            // TODO: handle exception
        }
    }

    public JPanel chatRoomGui() throws MalformedURLException, NotBoundException, RemoteException{
        panelRoom = new JTextPane();
        panelRoom.setBackground(Color.LIGHT_GRAY);
        panelRoom.setPreferredSize(new Dimension(200,460));
        
        JLabel currentUser = new JLabel("[" + textField1.getText() + "]: ");
        
        input = new JTextField(35);
        input.setBounds(20,40,200,30);

        JButton chooseFile = new JButton("file");
        chooseFile.setPreferredSize(new Dimension(70,40));

        chooseFile.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {       
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
                j.showSaveDialog(null);
            } 
        } );

        JPanel inputPannel = new JPanel();
        inputPannel.setLayout( new BorderLayout() );
        inputPannel.add( currentUser, BorderLayout.WEST);

        if(log.isProfessor(textField1.getText())){
            inputPannel.add( input, BorderLayout.CENTER);
            inputPannel.add( chooseFile, BorderLayout.EAST);   
        }else{
            inputPannel.add( input, BorderLayout.EAST);
        }

        JPanel p = new JPanel();
        p.setLayout( new BorderLayout() );
        p.add( panelRoom, BorderLayout.CENTER);
        p.add( inputPannel, BorderLayout.SOUTH);


        String urlChat = "rmi://127.0.0.1/CHAT";
        IChat client;
        
        client = new Chat(textField1.getText(),panelRoom);    
        IChat server = (IChat) Naming.lookup(urlChat);
        server.setClient(client);
        
        input.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == 10) {
                    input.setCaretPosition(0); 
                    input.setText(null);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) {
                    try {
                        server.sendToALL(textField1.getText()+ " : " + input.getText());
                    } catch (RemoteException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }   
            }
        });
        return p;
    }

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        MainClt cl = new MainClt();
    }
}
