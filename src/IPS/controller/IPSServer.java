package IPS.controller;

import IPS.controller.IPSServant;
import IPS.persistence.IPSPersistence;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class IPSServer {

    public static void main(String[] args) throws RemoteException {

        int port = 8888;
        String nombre = "IPS";


        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind(nombre, new IPSServant(new IPSPersistence("src/IPS/tests/testConfig.txt","src/IPS/tests/vacunasIps.txt")));


    }
}
