//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package EPS.controller;

import EPS.model.UserInfo;
import EPS.persistence.EPSPersistence;
import EPS.persistence.IEPSPersistence;
import GCC.controller.IGCC;

import java.math.BigInteger;
import java.rmi.Naming;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class EPSClient {
    private List<UserInfo> users = new ArrayList();
    private VaccineManager vaccineManager;
    private IGCC servicio;
    private IEPSPersistence persistence = new EPSPersistence();

    /**
     * Inicializa la EPS. Se conecta a la interfaz del GCC (IGCC). Inicializa el gestor de vacunas
     */
    public EPSClient() {
        try {
            //System.setProperty("java.rmi.server.hostname","25.96.80.182:9999/GCC");
            System.out.println("Buscando GCC");
            this.servicio = (IGCC) Naming.lookup("rmi://25.96.80.182:9999/GCC");
            System.out.println("Llegué acá");
            this.vaccineManager = new VaccineManager(this.servicio);
        } catch (Exception var2) {
            System.err.println("Client exception: " + var2.toString());
            var2.printStackTrace();
        }

    }

    /**
     * Getter de la lista de usuarios
     * @return
     */
    public List<UserInfo> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    public void initPersistence(String fileName){
        persistence.setFileName(fileName);
    }

    public void getUsrList(String name){
        persistence.setFileName(name);
        try {
            users = persistence.readUsrFromFile();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Devuelve una cadena con la información de los usuarios.
     * @return cadena con la información (ID, NOMBRE, APELLIDO, ESTADO DE SOLICITUD, VACUNA SOLICITADA) de los usuarios
     */
    public String getUsrStr(){
        String usersStr = "";
        for(UserInfo auxUser : users){
            usersStr += "ID: " + auxUser.getUniqueId() + " ";
            usersStr += "Nombre: " + auxUser.getName() + " " + auxUser.getLastName() + " ";
            usersStr += "Vacuna de tipo: " + auxUser.getVaccineRequested() + " " + auxUser.getState() + '\n';
        }
        return usersStr;
    }

    /**
     * Hace la orden de las vacunas pendientes en caso de que se necesiten más de 10 vacunas. Modifica el estado de los
     * usuarios que hayan recibido la vacuna. En caso de no ser una transacción finalizada, se aborta.
     * @return Devuelve true en caso de haber completado la transacción
     */
    public boolean makeOrder(){
        int[] prepareOrder = {0, 0, 0};
        int pedingAmount = 0;
        for(UserInfo auxUser : users){
            if(auxUser.getState().equals("Pendiente")){
                pedingAmount += 1;
            }
        }
        if(9 < pedingAmount){
            for(UserInfo auxUser : users){
                if(!auxUser.getState().equals("Recibida")){
                    prepareOrder[auxUser.getVaccineRequested()] += 1;
                }
            }
            vaccineManager.addVaccine(prepareOrder[0], 0);
            vaccineManager.addVaccine(prepareOrder[1], 1);
            vaccineManager.addVaccine(prepareOrder[2], 2);
            try{
                List<Integer> response = vaccineManager.makeOrder();
                if(response != null){
                    changeUsrState(response);
                }
                else{
                    vaccineManager.resetOrder();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Cambia el estado de petición de vacunas de "Pendiente" a "Recibida".
     * @param receivedVacs indica la vacunas recibidas
     */
    public void changeUsrState(List<Integer>receivedVacs){
        int[] requested = {0, 0, 0};
        for(UserInfo auxUser : users){
            requested[auxUser.getVaccineRequested()] += 1;
        }
        requested[0] -= receivedVacs.get(0);
        requested[1] -= receivedVacs.get(1);
        requested[2] -= receivedVacs.get(2);

        for(UserInfo auxUser : users){
            if(0 < requested[auxUser.getVaccineRequested()]){
                auxUser.setState("Recibida");
                requested[auxUser.getVaccineRequested()] -= 1;
            }
        }
        persistence.setUserInfo(users);
    }

    /**
     * Devuelve una cadena con la información de los usuarios que han recibido la vacuna
     * @return una cadena indicando la información de los usuarios que han recibido la vacuna solicitada
     */
    public String showTransactions(){
        String transactions = "";
        for(UserInfo auxUser : users){
            if(auxUser.getState().equals("Recibida")){
                transactions += "ID: " + auxUser.getUniqueId() + " ";
                transactions += auxUser.getName() + " " + auxUser.getLastName() + " ";
                transactions += "Vacuna de tipo: " + auxUser.getVaccineRequested() + " " + auxUser.getState() + '\n';
            }
        }
        return transactions;
    }

    /**
     * Se encarga de hacer el log in en la cuenta especficada
     * @param user nombre de usuario con el que se realizó el registro
     * @param password contraseña con la que se realizó el registro
     * @return "true" en caso de ingreso exitoso y "false" en caso de ingreso no exitoso
     */
    public boolean authLogIn(String user, String password){
        boolean response = false;
        try{
            response = servicio.login(user, getStrHash(password));
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Se encarga de registrar a un nuevo usuario
     * @param user nombre de usuario con el que se desea hacer el registro
     * @param password contraseña con la que se desea hacer el registro
     * @return "true" en caso de registro exitoso y "false" en caso de registro no exitoso
     */
    public boolean authSignUp(String user, String password){
        boolean response = false;
        try{
            response = servicio.register(user, getStrHash(password));
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Dada una palabra, se extrae su respectivo hash a partir del algoritmo SHA-256
     * @param word palabra a convertir en un hash
     * @return se devuelve el hash obtenido como una cadena
     */
    public String getStrHash(String word){
        String encodedHash = "";
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(word.getBytes("utf8"));
            encodedHash = String.format("%040x", new BigInteger(1, digest.digest()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return encodedHash;
    }
}
