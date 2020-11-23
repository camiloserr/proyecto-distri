package EPS.persistence;

import EPS.model.UserInfo;

import java.util.List;

public interface IEPSPersistence {

    /**
     * A partir del username de la EPS que accedió al sistema, configura el path para realizar la lectura de archivos
     * @param fileName
     */
    void setFileName(String fileName);

    /**
     * Lee el archivo con la información de los usuarios de la determinada EPS que accedió al sistema
     * @return lista de usuarios con su respectiva información
     */
    List<UserInfo> readUsrFromFile();

    /**
     * Recibe una lista de usuarios y modifica el archivo existente con esta, reemplanzandola por la nueva información
     * @param newUsers
     * @return "true" en caso de que la escritura de archivos sea exitosa
     */
    boolean setUserInfo(List<UserInfo> newUsers);
}
