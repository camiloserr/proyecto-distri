package GCC;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import IPS.IIPS;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.rmi.Naming;

public class GCCServant extends UnicastRemoteObject implements IGCC {

    public GCCServant() throws RemoteException {
        super();
    }

    public String sayHello(){
        return "Hello, world!";
    }

    public List<Boolean> pedirVacunas(int vA, int vB, int vC)
    {
        List <Boolean> vacEntreadas = null;
        try
        {
            IIPS servicio = (IIPS) Naming.lookup("rmi://localhost:8888/IPS");
            String response = servicio.darVacunaActuales();
            System.out.println("response: " + response);
            vacEntreadas = servicio.pedirVacunas(vA,vB,vC);

            response = servicio.darVacunaActuales();
            System.out.println("response: " + response);
        }catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

        return vacEntreadas;

    }


}
