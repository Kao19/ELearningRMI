import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class MyClient {
	
	private HelloInterface remoteServer;
	private final String SERVER_URL="rmi://127.0.0.1/MyServer";
	
	public static void main(String[] args) throws RemoteException {
		MyClient mc=new MyClient();
        mc.connect();
		
	}
	private void connect() throws RemoteException {
        try{
            remoteServer=(HelloInterface)Naming.lookup(SERVER_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

		while(true){     
            Scanner sc = new Scanner(System.in);
            String msg = sc.nextLine();
            String res = remoteServer.helloTo(msg);
            System.out.println(res);
        }
	}

}