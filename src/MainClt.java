import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;

import javax.swing.event.*;

public class MainClt extends JFrame implements ActionListener {

    JPanel GlobalPanel;
    JPanel panel;
	JFrame GlobalFrame;
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
                    this.tableau();   
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
	
    public void tableau(){
        try {    
            String urlPlateform = "rmi://127.0.0.1/launchPlateform";
            
            IPlateforme plt = (IPlateforme) Naming.lookup(urlPlateform);
           
            ELearningGUI plateform = new ELearningGUI(plt);
            plateform.setVisibleFrame();
            plt.enregistrerContenu(plateform);
            
        } catch (Exception excep) {
            // TODO: handle exception
        }
    }


    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        MainClt cl = new MainClt();
    }
}
