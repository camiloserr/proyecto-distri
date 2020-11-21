package IPS.persistence;



import IPS.model.IPSData;

import java.util.List;

public interface IIPSPersistence {

    /**
     * lee el archivo de configuración de la IPS
     */
    List<Integer> readConfigFile();
    void saveState(int vacunaA, int vacunaB, int vacunaC, int minVacunas, int peticion);
    IPSData readIPSFile();

}
