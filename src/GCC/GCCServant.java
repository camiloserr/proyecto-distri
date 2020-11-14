package GCC;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GCCServant extends UnicastRemoteObject implements IGCC {

    public GCCServant() throws RemoteException {
        super();
    }

    public String sayHello() throws RemoteException {
        return "Hello, world!";
    }


}
