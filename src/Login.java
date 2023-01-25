import java.rmi.RemoteException;
import java.rmi.server.*;
import java.sql.*;

public class Login extends UnicastRemoteObject implements ILogin{

    public Connection con;

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
    
}
