package IPS.persistence;

import java.util.List;

public interface IIPSPersistence {

    /**
     * lee el archivo de configuración de la IPS
     */
    List<Integer> readConfigFile();
}
