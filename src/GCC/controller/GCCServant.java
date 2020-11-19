package GCC.controller;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

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
        persistence = new GCCPersistence("src/gcc/persistence/config.txt", "src/gcc/persistence/authentication.txt");
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

    public List<Boolean> pedirVacunas(int vA, int vB, int vC)
    {
        List <Boolean> vacEntregadas = null;
        int[] solicitudDeVacunas = {vA,vB,vC};
        IIPS servicio = escogerIPS(solicitudDeVacunas);
        if( servicio != null )
        {
            try
            {
                vacEntregadas = servicio.pedirVacunas(vA,vB,vC);
                System.out.println("Vacunas han sido pedidas");
            }catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }

        return vacEntregadas;

    }


    private IIPS escogerIPS(int[] vac){
        TreeMap<Integer, IIPS> serviciosQuePuedenResponder = new TreeMap<Integer, IIPS>(Collections.reverseOrder());
        for(int i = 0 ; i < services.size() ; ++i){
            IIPS ips = services.get(i);
            int[] diffs = {-1,-1,-1};
            try {
                int vacunasDisponibles = 0;
                boolean puede = false;
                int[] diffsActuales = {0,0,0};

                // pide las vacunas actuales a la IPS
                int[] vacunasActuales = ips.darVacunaActuales();
                System.out.println("La IPS "+activeServices.get(i).getName()+" tiene " +
                        vacunasActuales[0] + " de A, " + vacunasActuales[1] + " de B y " +
                        vacunasActuales[2] + " de C");


                for(int j = 0 ; j<3 ; j++){
                    diffsActuales[j] = vacunasActuales[j] - vac[j];
                    if(diffsActuales[j] >= 0){
                        vacunasDisponibles+=diffsActuales[j];
                     }
                }

                serviciosQuePuedenResponder.put(vacunasDisponibles, ips);

            }
            catch(RemoteException e){
                System.out.println("La IPS " + activeServices.get(i).getName() + " esta caida");
                activeServices.get(i).setActive(false);
            }
        }

        //TODO: ponerle el random porque ahi esta retornando la IIPS que le sobran mas vacunas
        return serviciosQuePuedenResponder.get(serviciosQuePuedenResponder.lastKey());
    }






}
