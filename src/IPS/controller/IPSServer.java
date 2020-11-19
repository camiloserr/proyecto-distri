package IPS.controller;

import IPS.controller.IPSServant;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class IPSServer {

    public static void main(String[] args) throws RemoteException {

        // leer archivo
        //parametros
        //puerto
        //nombre de la ips
        //numero de vacuna

        // TODO: leer del archivo
        int port = 8888;
        String nombre = "IPS";
        int vaca = 10;
        int vacb = 10;
        int vacc = 10;


        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind(nombre, new IPSServant(vaca, vacb, vacc));


    }
}
