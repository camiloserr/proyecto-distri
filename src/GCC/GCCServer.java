package GCC;

import IPS.IIPS;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class GCCServer {

    public static void main(String[] args) throws RemoteException {

        //public mi servicio
        Registry registry = LocateRegistry.createRegistry(9999);
        registry.rebind("GCC", new GCCServant());

        //consumo IPS

        //La parte de consumir la pasé a las funciones remotas

        /*
        try {
            IIPS servicio = (IIPS) Naming.lookup("rmi://localhost:8888/IPS");
            String response = servicio.darVacunaActuales();
            System.out.println("response: " + response);
            List <Boolean> vacEntreadas = servicio.pedirVacunas(2,2,2);

            System.out.println( vacEntreadas.toString() );
            response = servicio.darVacunaActuales();
            System.out.println( response );


        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

         */
    }
}
