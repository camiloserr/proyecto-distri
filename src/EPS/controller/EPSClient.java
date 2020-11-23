//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package EPS.controller;

import EPS.model.UserInfo;
import EPS.persistence.EPSPersistence;
import EPS.persistence.IEPSPersistence;
import GCC.controller.IGCC;

import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class EPSClient {
    private List<UserInfo> users = new ArrayList();
    private VaccineManager vaccineManager;
    private IGCC servicio;
    private IEPSPersistence persistence = new EPSPersistence();

    public EPSClient() {
        try {
            this.servicio = (IGCC) Naming.lookup("rmi://localhost:9999/GCC");
            this.vaccineManager = new VaccineManager(this.servicio);
        } catch (Exception var2) {
            System.err.println("Client exception: " + var2.toString());
            var2.printStackTrace();
        }

    }

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

    public String getUsrStr(){
        String usersStr = "";
        for(UserInfo auxUser : users){
            usersStr += "ID: " + auxUser.getUniqueId() + " ";
            usersStr += "Nombre: " + auxUser.getName() + " " + auxUser.getLastName() + " ";
            usersStr += "Vacuna de tipo: " + auxUser.getVaccineRequested() + " " + auxUser.getState() + '\n';
        }
        return usersStr;
    }

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

    public String showTransactions(){
        String transactions = "";
        for(UserInfo auxUser : users){
            if(auxUser.getState().equals("Recibida")){
                transactions += "ID: " + auxUser.getState() + " ";
                transactions += auxUser.getName() + " " + auxUser.getLastName() + " ";
                transactions += "Vacuna de tipo: " + auxUser.getVaccineRequested() + " " + auxUser.getState() + '\n';
            }
        }
        return transactions;
    }

    public boolean authLogIn(String user, String password){
        boolean response = false;
        try{
            response = servicio.login(user, getStrHash(password));
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

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

    public String getStrHash(String word){
        byte[] encodedHash = null;
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            encodedHash = digest.digest(
                    word.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        return encodedHash.toString();
    }
}
