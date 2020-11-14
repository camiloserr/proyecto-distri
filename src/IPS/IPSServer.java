package IPS;

import GCC.GCCServant;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class IPSServer {

    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(8888);
        registry.rebind("IPS", new IPSServant());
    }
}
