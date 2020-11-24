package GCC.controller;

import GCC.persistence.GCCPersistence;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GCCServer {

    public static void main(String[] args) throws RemoteException {

        //publico mi servicio
        Registry registry = LocateRegistry.createRegistry(9999);
        registry.rebind("GCC", new GCCServant(new GCCPersistence("src/GCC/persistence/config.txt", "src/GCC/persistence/authentication.txt")));
    }
}
