import java.rmi.RemoteException;
import java.rmi.server.*;
import java.sql.*;
import java.util.ArrayList;

public class Login extends UnicastRemoteObject implements ILogin{

    public Connection con;
    public ArrayList<String> liste = new ArrayList<String>();

    public Login() throws RemoteException {
        super();
    }

    @Override
    public Boolean login(String username, String password) throws RemoteException {
        // TODO Auto-generated method stub
        String db = "jdbc:mysql://localhost:3306/elearning";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, "root", "");          
        }catch(Exception e){
            e.printStackTrace();
        }
        String query = "SELECT * FROM elearning.user WHERE username = '" + username + "' AND password = '" + password + "'";
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean loginAdmin(String username, String password) throws RemoteException {
        // TODO Auto-generated method stub
        String db = "jdbc:mysql://localhost:3306/elearning";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, "root", "");          
        }catch(Exception e){
            e.printStackTrace();
        }
        String query = "SELECT * FROM elearning.admin WHERE login = '" + username + "' AND password = '" + password + "'";
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Boolean isProfessor(String username) throws RemoteException {
        String db = "jdbc:mysql://localhost:3306/elearning";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, "root", "");          
        }catch(Exception e){
            e.printStackTrace();
        }
        String query = "SELECT * FROM elearning.user WHERE profile = 'professeur' and username = '" + username + "'";
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<String> listeClassesEtudiant(String u, String p) throws RemoteException {
        liste = new ArrayList<String>();
        String db = "jdbc:mysql://localhost:3306/elearning";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, "root", "");          
        }catch(Exception e){
            e.printStackTrace();
        }
        String query = "select * from elearning.user where profile = 'etudiant' and class = (select class from elearning.user where username = '" + u + "' and password = '" + p + "')";
        try{
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet resultset = pstmt.executeQuery();
            while (resultset.next()) {
                liste.add(resultset.getString("username")); // add it to the result
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return liste;
        
    }

    @Override
    public String[] profETclasse(String u, String p) throws RemoteException {
        String[] profClass = new String[2];
        String db = "jdbc:mysql://localhost:3306/elearning";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, "root", "");          
        }catch(Exception e){
            e.printStackTrace();
        }
        String query = "select * from elearning.user where profile = 'professeur' and class = (select class from elearning.user where username = '" + u + "' and password = '" + p + "')";
        try{
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet resultset = pstmt.executeQuery();
            while (resultset.next()) {
                profClass[0] = resultset.getString("username"); 
                profClass[1] = resultset.getString("class"); 
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return profClass;
        
    }
    
}
