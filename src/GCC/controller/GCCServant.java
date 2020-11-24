package GCC.controller;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import GCC.model.IPSInfo;
import GCC.persistence.GCCPersistence;
import GCC.persistence.IGCCPersistence;
import IPS.controller.IIPS;
import java.rmi.Naming;
import java.util.concurrent.ThreadLocalRandom;

public class GCCServant extends UnicastRemoteObject implements IGCC {

    // Lista de los servicios (IIPS) que van a proveer las vacunas al GCC
    private List<IIPS> services;
    // Mantiene la informacion de las IPS y sabe si el servidor esta activo o no
    private List<IPSInfo> activeServices;
    //persistencia
    private IGCCPersistence persistence;
    //mapa con los usuarios
    private Map<String, String> usuarios;

    public GCCServant(GCCPersistence persistence) throws RemoteException {
        super();
        this.persistence = persistence;
        initVariables();
        usuarios = null;
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
                System.out.println("IPS: " + ips.getName() + " encontrada");
                ips.setActive(true);
                services.add(servicio);

            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }
    }

    public List<Integer> pedirVacunas(int vA, int vB, int vC)
    {
        List <Integer> vacEntregadas = null;
        int[] solicitudDeVacunas = {vA,vB,vC};
        IIPS servicio = escogerIPS(solicitudDeVacunas);
        if( servicio != null )
        {
            try
            {
                // si no se encuentran todas las vacunas, solo se encvian las vacunas encontradas
                // la EPS debe tener en cuanta las vacunas que no se pudieron entregar para pedirlas de nuevo despues
                vacEntregadas = servicio.pedirVacunas(new int[]{vA,vB,vC}, servicio.darVacunaActuales());

                // la transaccion fue cancelada porque el estado no era igual a cuando se leyo
                if(vacEntregadas == null){
                    System.out.println("transaccion cancelada: estado no concistente");
                    // retorna la peticion porque no pudo devolver ninguna vacuna
                    return null;

                }
                System.out.println("Vacunas han sido pedidas");
            }catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }

        return vacEntregadas;

    }

    @Override
    public boolean login(String user, String password) throws RemoteException {
        System.out.println("Haciendo LOGIN");
        if(usuarios == null){
            usuarios = persistence.getUsers();
        }

        return usuarios.containsKey(user);
    }


    @Override
    public boolean register(String user, String password) {
        System.out.println("Haciendo REG");
        if(usuarios == null){
            usuarios = persistence.getUsers();
        }
        if(usuarios.containsKey(user)){
            System.out.println("Usuario "+user+" ya existe");
            return false;
        }
        usuarios.put(user, password);
        return persistence.newUser(user, password);
    }


    private IIPS escogerIPS(int[] vac){
        ArrayList< IIPS > serviciosQuePuedenResponder = new ArrayList<>();
        for(int i = 0 ; i < services.size() ; ++i){
            IIPS ips = services.get(i);
            int[] diffs = {-1,-1,-1};
            try {
                int vacunasDisponibles = 0;
                boolean puede = false;
                int[] diffsActuales = {0,0,0};

                // pide las vacunas actuales a la IPS
                int[] vacunasActuales = ips.darVacunaActuales();
                /*System.out.println("La IPS "+activeServices.get(i).getName()+" tiene " +
                        vacunasActuales[0] + " de A, " + vacunasActuales[1] + " de B y " +
                        vacunasActuales[2] + " de C");*/


                for(int j = 0 ; j<3 ; j++){
                    diffsActuales[j] = vacunasActuales[j] - vac[j];
                    if(diffsActuales[j] >= 0){
                        vacunasDisponibles+=diffsActuales[j];
                     }
                }

                serviciosQuePuedenResponder.add( ips );

            }
            catch(RemoteException e){
                System.out.println("La IPS " + activeServices.get(i).getName() + " esta caida");
                activeServices.get(i).setActive(false);
            }
        }
        return serviciosQuePuedenResponder.get(new Random().nextInt( serviciosQuePuedenResponder.size()));
    }
}
