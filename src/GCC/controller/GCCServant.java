package GCC.controller;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import GCC.model.IPSInfo;
import GCC.persistence.GCCPersistence;
import GCC.persistence.IGCCPersistence;
import IPS.controller.IIPS;

import java.rmi.Naming;

public class GCCServant extends UnicastRemoteObject implements IGCC {

    // Lista de los servicios (IIPS) que van a proveer las vacunas al GCC
    private List<IIPS> services;
    // Mantiene la informacion de las IPS y sabe si el servidor esta activo o no
    private List<IPSInfo> activeServices;
    //persistencia
    private IGCCPersistence persistence;

    public GCCServant() throws RemoteException {
        super();
        persistence = new GCCPersistence("config.txt", "authentication.txt");
        initVariables();

    }

    /**
     * Inicializa todas las variables leyendo del archivo
     */
    private void initVariables() {

        //lee los datos de las ips del archivo de configuracion
        activeServices = persistence.readConfigFile();


        services = new ArrayList<>();

        for(int i = 0 ; i <activeServices.size() ; ++i) {
            IIPS servicio;
            IPSInfo  ips = activeServices.get(i);
            try {
                //inicializa el servicio con los datos obtenidos del archivo de config
                servicio = (IIPS) Naming.lookup("rmi://" + ips.getIp() + ":" + ips.getPort() + "/" + ips.getName());
                ips.setActive(true);
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
                int[] response = servicio.darVacunaActuales();
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
