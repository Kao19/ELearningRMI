import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;

import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

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
        
        
        this.setSize(800,600); 
        setLocationRelativeTo(null);
        
        b1.addActionListener(this);       
        setTitle("LOGIN FORM");         

        this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		JButton actionSource = (JButton) e.getSource();

        if(actionSource.equals(b1)){
            
            String urlLogin = "rmi://127.0.0.1/login";
            try {
                ILogin log = (ILogin) Naming.lookup(urlLogin);
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
            // String urlPlateform = "rmi://127.0.0.1/launchPlateform";
            
            // IBoard plt = (IBoard) Naming.lookup(urlPlateform);
           
            // ELearningGUI gui = new ELearningGUI(plt);
            // gui.setVisibleFrame();
            // plt.enregistrerContenu(gui);


            panelRoom = new JTextPane();
            panelRoom.setBackground(Color.LIGHT_GRAY);
            panelRoom.setPreferredSize(new Dimension(400,600));
            
            input = new JTextField(35);
            input.setBounds(20,40,200,30);

            JPanel p = new JPanel();
            p.setLayout( new BorderLayout() );
            p.add( panelRoom, BorderLayout.CENTER);
            p.add( input, BorderLayout.SOUTH);

            chatroom = new JFrame();                               
            chatroom.setContentPane(p);
            chatroom.pack();
            chatroom.setVisible( true );
            chatroom.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            chatroom.setLocationRelativeTo(null);
            chatroom.setResizable(false);
     
            
            String urlChat = "rmi://127.0.0.1/CHAT";
            IChat client = new Chat(textField1.getText(),panelRoom);
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
                            // Chat.panelRoom.add(new JTextArea(input.getText()));
                            // StyledDocument doc = Chat.panelRoom.getStyledDocument();
                            // try {
                            //     doc.insertString(doc.getLength(), input.getText() + "\n", null);
                            // } catch (BadLocationException e1) {
                            //     e1.printStackTrace();
                            // }
                            try {
                                server.sendToALL(input.getText(),panelRoom);
                            } catch (RemoteException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }   
                    }
                });

            
            
            
        } catch (Exception excep) {
            // TODO: handle exception
        }
    }


    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        MainClt cl = new MainClt();
    }
}
