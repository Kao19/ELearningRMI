import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.io.*;

import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.filechooser.*;

public class MainClt extends JFrame implements ActionListener {

    JTextPane panelRoom;
    JTextField input;
    JLabel userLabel, passLabel;  
    JTextField  textField1, textField2; 
    JButton b1;
    
    String urlLogin = "rmi://127.0.0.1/login";
    ILogin log = (ILogin) Naming.lookup(urlLogin);

    JButton normalUser;
	JButton admin;
    JFrame adminOrLogin;
    JPanel aOrL;

	MainClt() throws MalformedURLException, RemoteException, NotBoundException{
        
        adminOrLogin = new JFrame("first page");

        aOrL = new JPanel();	
        adminOrLogin.add(aOrL);

        this.normalUser = new JButton("normal user");
        aOrL.add(normalUser);

		this.admin = new JButton("admin");
        aOrL.add(admin);


		this.normalUser.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                adminOrLogin.setVisible(false);       
                LoginForm();
            } 
        } );

        this.admin.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {  
                adminOrLogin.setVisible(false);     
                AdminForm();
            } 
        } );
		
		
		adminOrLogin.setSize(640,440);
		adminOrLogin.setLocationRelativeTo(null);
		adminOrLogin.setVisible(true);
		adminOrLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	}

    public void AdminForm(){

        JFrame frame = new JFrame("Admin frame");
 
      	JPanel panel = new JPanel();	
		frame.add(panel);

        JLabel classLabel = new JLabel();  
        classLabel.setText("classe:");      
        JTextField classe = new JTextField(15); 
        JButton addClasse = new JButton("ajouter classe");
        
        
        JLabel profLabel = new JLabel();  
        profLabel.setText("login professeur:");      
        JTextField profLogin = new JPasswordField(10); 
        JLabel profLabel2 = new JLabel();  
        profLabel2.setText("password professeur:");      
        JTextField profPassword = new JPasswordField(10); 
        JButton addProf = new JButton("ajouter professeur");   
        
        
        JLabel etudiantLabel = new JLabel();  
        etudiantLabel.setText("Login etudiant:");      
        JTextField etudiantLogin = new JPasswordField(10); 
        JLabel etudiantLabel2 = new JLabel();  
        etudiantLabel2.setText("Password etudiant:");      
        JTextField etudiantPassword = new JPasswordField(10);    
        JButton addetudiant = new JButton("ajouter etudiant");
            
        panel.add(classLabel);      
        panel.add(classe);
        panel.add(addClasse); 
        panel.add(new JLabel("                                                                                                                                                                                               "));

        panel.add(profLabel); 
        panel.add(profLogin);     
        panel.add(profLabel2);     
        panel.add(profPassword); 
        panel.add(addProf);
        panel.add(new JLabel("                                                                                                                                                                                               "));


        panel.add(etudiantLabel);
        panel.add(etudiantLogin);     
        panel.add(etudiantLabel2);     
        panel.add(etudiantPassword);                
        panel.add(addetudiant);
        panel.add(new JLabel("              "));
 

        JButton jb = new JButton("Lister");            
        panel.add(jb);
        
        add(panel);  
        
        
        this.setSize(640,440); 
        setLocationRelativeTo(null);
        
        addClasse.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String urlAdmin= "rmi://127.0.0.1/admin";
                try {
                    IAdmin iad = (IAdmin) Naming.lookup(urlAdmin);
                    if(iad.addClasse(classe.getText())){
                        JOptionPane.showOptionDialog(null, "probleme d'insertion", "problem", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    }else{
                        JOptionPane.showOptionDialog(null, "information bien inserte", "success", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    }
                } catch (MalformedURLException | RemoteException | NotBoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            
        });
        
        addProf.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String urlAdmin= "rmi://127.0.0.1/admin";
                try {
                    IAdmin iad = (IAdmin) Naming.lookup(urlAdmin);
                    if(iad.addProf(profLogin.getText(), profPassword.getText())){
                        JOptionPane.showOptionDialog(null, "probleme d'insertion", "problem", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    }else{
                        JOptionPane.showOptionDialog(null, "information bien inserte", "success", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    }
                } catch (MalformedURLException | RemoteException | NotBoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            
        });

        addetudiant.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String urlAdmin= "rmi://127.0.0.1/admin";
                try {
                    IAdmin iad = (IAdmin) Naming.lookup(urlAdmin);
                    if(iad.addetudiant(etudiantLogin.getText(), etudiantPassword.getText())){
                        JOptionPane.showOptionDialog(null, "probleme d'insertion", "problem", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    }else{
                        JOptionPane.showOptionDialog(null, "information bien inserte", "success", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    }
                } catch (MalformedURLException | RemoteException | NotBoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            
        });
        
        setTitle("ADMIN FORM");         

        setSize(640,440);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

    }


    public void LoginForm(){

		JFrame frame = new JFrame("Login");
 
      	JPanel panel = new JPanel();	
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
        
        chooseFile.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {   
                
                JFileChooser file = new JFileChooser(FileSystemView.getFileSystemView());
                file.showSaveDialog(null);
                
                if(file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File f = file.getSelectedFile();
                    byte[] filedata;
                    try {
                        filedata = server.downloadFile(f.getPath());     
                        File newfile = new File(f.getName());
                        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("C:\\Users\\HP\\Desktop\\" + newfile.getName()));
                        output.write(filedata,0,filedata.length);
                        output.flush();
                        output.close();
                    } catch (RemoteException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            } 
        } );


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
