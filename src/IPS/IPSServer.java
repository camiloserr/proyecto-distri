package IPS;

import GCC.GCCServant;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class IPSServer {

    public static void main(String[] args) throws RemoteException {
        //Registry registry = LocateRegistry.createRegistry(8888);
        //registry.rebind("IPS", new IPSServant());


        //Registry reg = null;
        int port = 8880;
        for(int i = 0 ; i < 4 ; i++)
        {
            Registry registry = LocateRegistry.createRegistry( port );
            port++;
            String ipsName = "IPS" + (i+1);
            registry.rebind(ipsName, new IPSServant() );
        }



    }
}
