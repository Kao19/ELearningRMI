import java.rmi.RemoteException;
import java.rmi.server.*;
import java.sql.*;

public class Admin extends UnicastRemoteObject implements IAdmin{

    public Connection con;

    protected Admin() throws RemoteException {
        super();
    }

    @Override
    public boolean addClasse(String name) throws RemoteException {
        String db = "jdbc:mysql://localhost:3306/elearning";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, "root", "");          
        }catch(Exception e){
            e.printStackTrace();
        }
        String query = "insert into class values (?)";
        try{
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            return pstmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addProf(String username, String password) throws RemoteException {
        String db = "jdbc:mysql://localhost:3306/elearning";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, "root", "");          
        }catch(Exception e){
            e.printStackTrace();
        }
        String query = "insert into user values (?,?,'professeur')";
        try{
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            if(pstmt.execute()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addetudiant(String username, String password) throws RemoteException {
        String db = "jdbc:mysql://localhost:3306/elearning";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, "root", "");          
        }catch(Exception e){
            e.printStackTrace();
        }
        String query = "insert into user values (?,?,'etudiant')";
        try{
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            if(pstmt.execute()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }    

    
    
}
