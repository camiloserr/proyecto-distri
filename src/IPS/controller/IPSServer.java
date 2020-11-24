package IPS.controller;

import IPS.model.IPSData;
import IPS.persistence.IPSPersistence;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class IPSServer {

    public static void main(String[] args) throws RemoteException {

        //int port = 8888;
        //String nombre = "IPS";
        IPSPersistence persistence = new IPSPersistence("src/IPS/tests/vacunasIps.txt","src/IPS/tests/vacunasIps.txt", "src/IPS/tests/ipsData.txt");

        IPSData ips = persistence.readIPSFile();

        System.setProperty("java.rmi.server.hostname","25.96.126.0");
        System.out.println("Registrando IPS...");
        Registry registry = LocateRegistry.createRegistry(ips.getPort());
        registry.rebind(ips.getName(), new IPSServant(persistence));
        System.out.println("=======IPS Registrada=======");


    }
}
