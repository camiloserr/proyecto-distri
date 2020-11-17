package GCC.persistence;

import GCC.model.IPSInfo;

import java.util.ArrayList;

public interface IGCCPersistence {


    /**
     * lee archivo de configuracion
     * @return lista con los datos de las IPS
     */
    ArrayList<IPSInfo> readConfigFile();

    /**
     * lee archivo de autenticacion
     * @param username nombre de usuario
     * @param passHash hash de la contraseña enviada por el usuario
     * @return true si las credenciales corresponden con el archivo
     */
    boolean authenticateUser(String username, String passHash);

    /**
     * recibe las credenciales y las escribe en el archivo de autenticacion
     * @param username nombre de usuario
     * @param passHash hash de la contrasena
     */
    boolean newUser(String username, String passHash);

    boolean existsUser(String username);

}
