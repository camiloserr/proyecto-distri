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

    public List<Boolean> pedirVacunas(int vA, int vB, int vC, int servP)
    {
        List <Boolean> vacEntreadas = null;
        IIPS servicio = pillarServicio( servP ); //Pillar servicio se cambiaría por el método de escoger la mejor ips para la solicitud.
        if( servicio != null )
        {
            try
            {
                String response = servicio.darVacunaActuales();
                System.out.println("response: " + response);
                vacEntreadas = servicio.pedirVacunas(vA,vB,vC);

                response = servicio.darVacunaActuales();
                System.out.println("response: " + response);
            }catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }


        return vacEntreadas;

    }

    //Hace el lookup de la ips/pueto a la que se le pide las vacunas
    private IIPS pillarServicio(int servP) {

        int port = 8879 + servP;
        IIPS servicio = null;
        try
        {
            servicio = (IIPS) Naming.lookup("rmi://localhost:" + port + "/IPS" + servP );
        }catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

        return servicio;

    }


}
