import java.rmi.RemoteException;
import java.rmi.server.*;
import java.sql.*;
import java.util.ArrayList;

public class Admin extends UnicastRemoteObject implements IAdmin{

    public Connection con;
    public ArrayList<String> listeClass = new ArrayList<String>();

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
    public boolean insertUser(String username, String password, String profile, String classe) throws RemoteException {
        String db = "jdbc:mysql://localhost:3306/elearning";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, "root", "");          
        }catch(Exception e){
            e.printStackTrace();
        }
        String query = "insert into user values (?,?,?,?)";
        try{
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, profile);
            pstmt.setString(4, classe);
            return pstmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<String> listOfClasses() throws RemoteException {
        String db = "jdbc:mysql://localhost:3306/elearning";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(db, "root", "");          
        }catch(Exception e){
            e.printStackTrace();
        }
        String query = "select * from class";
        try{
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet resultset = pstmt.executeQuery();
            while (resultset.next()) {
                listeClass.add(resultset.getString("className")); // add it to the result
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return listeClass;
    }    

    
    
}
