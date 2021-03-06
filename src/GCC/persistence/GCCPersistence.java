package GCC.persistence;

import GCC.model.IPSInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GCCPersistence implements IGCCPersistence{

    private String configFileName;
    private String authenticationFileName;



    public GCCPersistence(String configFileName, String authenticationFileName) {
        this.configFileName = configFileName;
        this.authenticationFileName = authenticationFileName;
    }

    @Override
    public ArrayList<IPSInfo> readConfigFile() {
        ArrayList<IPSInfo> res = new ArrayList<>();
        Scanner myReader = null;

        try {
            System.out.println("Leyendo archivo de configuracion..." + configFileName);
            File myObj = new File(configFileName);
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                IPSInfo ips = new IPSInfo();
                // quita espacios en blanco

                // para el nombre
                String data = myReader.nextLine();
                data.trim();
                // quita el ":"
                ips.setName(data.substring(0, data.length()-1));


                //para la ip
                data = myReader.nextLine();
                data.trim();
                String[] arrOfStr = data.split(":", 2);

                // quita el ":"
                ips.setIp(arrOfStr[1].trim());

                // para el puerto
                data = myReader.nextLine();
                data.trim();
                arrOfStr = data.split(":", 2);

                ips.setPort(Integer.parseInt(arrOfStr[1].trim()));
                ips.setActive(false);
                res.add(ips);

                System.out.println(ips);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo");
            e.printStackTrace();
        }
        finally {
            myReader.close();
        }

        return res;
    }

    @Override
    public Map<String, String> getUsers() {

        Map<String, String> usuarios = new HashMap<>();
        File myObj = new File(authenticationFileName);
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] arrOfStr = line.split(":", 2);
                String user = arrOfStr[0];
                String pass = arrOfStr[1];

                usuarios.put(user, pass);


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            myReader.close();
        }
        return usuarios;
    }



    @Override
    public boolean newUser(String username, String passHash) {

        try {
            //System.out.println("Usuario " + username + " ingresado");
            Writer output = new BufferedWriter(new FileWriter(authenticationFileName, true));

            output.append(username+":"+passHash+"\n");
            output.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean existsUser(String username){
        File myObj = new File(authenticationFileName);
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] arrOfStr = line.split(":", 2);
                String user = arrOfStr[0];

                if(user.equals(username)){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            myReader.close();
        }
        return false;
    }
}
