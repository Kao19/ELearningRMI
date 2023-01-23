import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class MyServer extends UnicastRemoteObject implements HelloInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4392839935538373377L;
	private final String SERVER_URL="MyServer";

	public MyServer() throws RemoteException {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws RemoteException {
		MyServer ms=new MyServer();
		ms.waitConnections();
	}

	private void waitConnections() {
		try {
            LocateRegistry.createRegistry(1099);
			Naming.rebind(SERVER_URL, new MyServer());
			System.out.println("[Server] Waiting for incoming calls");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String helloTo(String name) throws RemoteException {
		System.out.println("[Server] Called method helloTo() with parameter: "+name);
		System.out.println("[Server] Say hello to "+name);
		return "Say hello to "+name;
	}

	@Override
	public Integer square(Integer num) throws RemoteException {
		System.out.println("[Server] Called method square() with parameter: "+num);
		System.out.println("[Server] Square is "+num*num);
		return num*num;
	}
}