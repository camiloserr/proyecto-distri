//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package EPS.controller;

import EPS.model.TransactionInfo;
import EPS.model.UserInfo;
import GCC.controller.IGCC;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EPSClient {
    List<TransactionInfo> transactions = new ArrayList();
    List<UserInfo> users = new ArrayList();
    VaccineManager vaccineManager;
    IGCC servicio;

    public EPSClient() {
        try {
            this.servicio = (IGCC)Naming.lookup("rmi://localhost:9999/GCC");
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

    public List<TransactionInfo> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<TransactionInfo> transaction) {
        this.transactions = transaction;
    }

    public List<UserInfo> readRequests(String path) throws IOException {
        File file = new File(path);
        ArrayList users = new ArrayList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String ln;
            while((ln = br.readLine()) != null) {
                String[] request = ln.split(" ");
                UserInfo auxUser = new UserInfo(Integer.parseInt(request[0]), request[1], request[2], request[3], Integer.parseInt(request[4]));
                users.add(auxUser);
            }

            br.close();
            return users;
        } catch (IOException var8) {
            throw var8;
        }
    }

    public String setOrder(String path) {
        try {
            this.setUsers(this.readRequests(path));
        } catch (IOException var6) {
            System.out.println("Error: " + var6);
        }

        if (this.users.size() < 10) {
            return "No hay suficientes solicitudes";
        } else {
            int[] requests = new int[]{0, 0, 0};
            Iterator var3 = this.users.iterator();

            while(true) {
                UserInfo auxUser;
                do {
                    if (!var3.hasNext()) {
                        this.vaccineManager.addVaccine(requests[0], 0);
                        this.vaccineManager.addVaccine(requests[1], 1);
                        this.vaccineManager.addVaccine(requests[2], 2);

                        try {
                            this.preOrderModifyFile(path);
                        } catch (IOException var5) {
                            System.out.println("Error: " + var5);
                        }

                        return "Orden creada exitosamente";
                    }

                    auxUser = (UserInfo)var3.next();
                } while(!auxUser.getState().equals("Pendiente") && !auxUser.getState().equals("Solicitada"));

                ++requests[auxUser.getVaccineRequested()];
            }
        }
    }

    public List<Integer> sendOrder() {
        Object orderResults = new ArrayList();

        try {
            orderResults = this.vaccineManager.makeOrder();
        } catch (Exception var3) {
            System.out.println("Error: " + var3);
        }

        return (List)orderResults;
    }

    public String usersToString() {
        String userInf = "Usuarios\n";

        UserInfo auxUser;
        for(Iterator var2 = this.users.iterator(); var2.hasNext(); userInf = userInf + " | Vacuna de tipo: " + auxUser.getVaccineRequested() + " " + auxUser.getState() + '\n') {
            auxUser = (UserInfo)var2.next();
            userInf = userInf + "ID: " + auxUser.getUniqueId();
            userInf = userInf + " | " + auxUser.getName() + " " + auxUser.getLastName();
        }

        return userInf;
    }

    public void preOrderModifyFile(String path) throws IOException {
        try {
            File file = new File(path);
            BufferedWriter wr = new BufferedWriter(new FileWriter(file, false));
            String newFile = "";

            UserInfo auxUser;
            for(Iterator var5 = this.users.iterator(); var5.hasNext(); newFile = newFile + auxUser.getUniqueId() + " " + auxUser.getName() + " " + auxUser.getLastName() + " Solicitada " + auxUser.getVaccineRequested() + '\n') {
                auxUser = (UserInfo)var5.next();
                auxUser.setState("Solicitada");
            }

            wr.write(newFile);
            wr.close();
        } catch (IOException var7) {
            throw var7;
        }
    }

    public void createTransaction(List<Integer> results, String path) {
        int[] pendings = new int[]{0, 0, 0};
        String id = String.valueOf(this.transactions.size() + 1);
        System.out.println(results.get(0));
        System.out.println(results.get(1));
        System.out.println(results.get(2));
        new ArrayList();
        Iterator var6 = this.users.iterator();

        UserInfo auxUser;
        while(var6.hasNext()) {
            auxUser = (UserInfo)var6.next();
            if (auxUser.getState() == "Solicitada") {
                System.out.println("Sip");
                ++pendings[auxUser.getVaccineRequested()];
            }
        }

        pendings[0] -= (Integer)results.get(0);
        pendings[1] -= (Integer)results.get(1);
        pendings[2] -= (Integer)results.get(2);
        System.out.println(pendings[0]);
        System.out.println(pendings[1]);
        System.out.println(pendings[2]);
        var6 = this.users.iterator();

        while(var6.hasNext()) {
            auxUser = (UserInfo)var6.next();
            System.out.println(": " + auxUser.getState());
            if (auxUser.getState() == "Solicitada" && pendings[auxUser.getVaccineRequested()] > 0) {
                auxUser.setState("Recibida");
                --pendings[auxUser.getVaccineRequested()];
            }
        }

        TransactionInfo auxTr = new TransactionInfo(id, this.users, "Completada");
        this.transactions.add(auxTr);

        try {
            this.updateUser(path);
        } catch (IOException var8) {
            System.err.println("Error" + var8);
        }

    }

    public void updateUser(String path) throws IOException {
        try {
            File file = new File(path);
            BufferedWriter wr = new BufferedWriter(new FileWriter(file, false));
            String newFile = "";

            UserInfo auxUser;
            for(Iterator var5 = this.users.iterator(); var5.hasNext(); newFile = newFile + auxUser.getUniqueId() + " " + auxUser.getName() + " " + auxUser.getLastName() + " " + auxUser.getState() + " " + auxUser.getVaccineRequested() + '\n') {
                auxUser = (UserInfo)var5.next();
            }

            wr.write(newFile);
            wr.close();
        } catch (IOException var7) {
            throw var7;
        }
    }

    public String getStrTransactions() {
        String trans = "ID Transaccion ";

        TransactionInfo auxTr;
        for(Iterator var2 = this.transactions.iterator(); var2.hasNext(); trans = trans + "Estado: " + auxTr.getState()) {
            auxTr = (TransactionInfo)var2.next();
            trans = trans + auxTr.getId() + '\n';

            UserInfo auxUser;
            for(Iterator var4 = auxTr.getUsers().iterator(); var4.hasNext(); trans = trans + "Usuario: " + auxUser.getUniqueId() + " " + auxUser.getName() + " " + auxUser.getLastName() + auxUser.getVaccineRequested() + '\n') {
                auxUser = (UserInfo)var4.next();
            }
        }

        return trans;
    }
}
