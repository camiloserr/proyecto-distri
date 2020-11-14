package server;


import java.rmi.Naming;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServant extends UnicastRemoteObject implements IHello {

    public HelloServant() throws RemoteException {
        super();
    }

    public String sayHello() throws RemoteException {
        System.out.println("Buena la rata");
        return "Hello, world!";
    }


}
