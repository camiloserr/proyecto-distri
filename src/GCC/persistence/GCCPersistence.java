package GCC.persistence;

import GCC.model.IPSInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GCCPersistence implements IGCCPersistence{

    private String configFileName;
    private String authenticationFileName;


    /**
     * inicializa los nombres de los archivos necesarios
     * @param configFileName
     * @param authenticationFileName
     */
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

    /**
     * lee el archivo para autenticar un usuario
     * @param username nombre de usuario
     * @param passHash hash de la contraseña enviada por el usuario
     * @return true si las credenciales coinciden, false de lo contrario
     */
    @Override
    public boolean authenticateUser(String username, String passHash) {
        File myObj = new File(authenticationFileName);
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] arrOfStr = line.split(":", 2);
                String user = arrOfStr[0];
                String pass = arrOfStr[1];

                if(user.equals(username)){
                    if(pass.equals(passHash)){
                        return true;
                    }
                    else{
                        return false;
                    }
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


    @Override
    public boolean newUser(String username, String passHash) {

        if(existsUser(username)){
            System.out.println("Usuario "+username+" ya existe");
            return false;
        }
        try {
            System.out.println("Usuario " + username + " ingresado");
            FileWriter myWriter = new FileWriter(authenticationFileName);
            myWriter.append(username+":"+passHash+"\n");
            myWriter.close();
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
