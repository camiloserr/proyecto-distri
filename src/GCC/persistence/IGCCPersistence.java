package GCC.persistence;

import GCC.model.IPSInfo;

import java.util.ArrayList;
import java.util.Map;

public interface IGCCPersistence {


    /**
     * lee archivo de configuracion
     * @return lista con los datos de las IPS
     */
    ArrayList<IPSInfo> readConfigFile();

    /**
     * lee archivo de autenticacion y retorna todos los usuarios del sistema
     * @return Mapa con los usuarios del archivo de autenticacion
     */
    Map<String, String> getUsers();

    /**
     * recibe las credenciales y las escribe en el archivo de autenticacion
     * @param username nombre de usuario
     * @param passHash hash de la contrasena
     */
    boolean newUser(String username, String passHash);

    boolean existsUser(String username);

}
