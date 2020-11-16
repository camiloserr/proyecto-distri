package GCC;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import IPS.IIPS;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.rmi.Naming;

public class GCCServant extends UnicastRemoteObject implements IGCC {

    private List<IIPS> services;
    private int[][] matriz = {{10,10,10} , {10,10,10} , {10,10,10,}};

    public GCCServant() throws RemoteException {
        super();
        initVariables();
    }

    /**
     * Inicializa todas las variables leyendo del archivo
     */
    private void initVariables() {

        //deberia leer esto del archivo

        String ip[] = {"localhost", "localhost", "localhost"};

        int port[] = {8881,8882,8883};

        String name[] = {"IPS1", "IPS2", "IPS3"};

        ////////////////

        services = new ArrayList<IIPS>();

        for(int i = 0 ; i <3 ; ++i) {
            IIPS servicio = null;
            try {
                servicio = (IIPS) Naming.lookup("rmi://" + ip[i] + ":" + port[i] + "/" + name[i]);

                //TODO: inicializar matriz
                services.add(servicio);

            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }
    }

    public String sayHello(){
        return "Hello, world!";
    }

    public List<Boolean> pedirVacunas(int vA, int vB, int vC, int servP)
    {
        List <Boolean> vacEntreadas = null;
        // TODO: hacer algoritmo para saber a que IPS pedirle las vacunas
        IIPS servicio = services.get(0); //Pillar servicio se cambiaría por el método de escoger la mejor ips para la solicitud.
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



}
