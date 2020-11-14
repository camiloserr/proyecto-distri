package GCC;

import IPS.IIPS;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GCCServer {

    public static void main(String[] args) throws RemoteException {

        //public mi servicio
        Registry registry = LocateRegistry.createRegistry(9999);
        registry.rebind("GCC", new GCCServant());

        //consumo IPS
        try {
            IIPS servicio = (IIPS) Naming.lookup("rmi://localhost:8888/IPS");
            String response = servicio.darVacuna(3);
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
