package IPS.persistence;



import IPS.model.IPSData;

import java.util.List;

public interface IIPSPersistence {

    /**
     * lee el archivo de configuración de la IPS
     */
    List<Integer> readConfigFile();

    /**
     * Guarda en un archivo el estado actual de la IPS
     * @param vacunaA
     * @param vacunaB
     * @param vacunaC
     * @param minVacunas
     * @param peticion
     */
    void saveState(int vacunaA, int vacunaB, int vacunaC, int minVacunas, int peticion);

    /**
     * lle la información de la ips. Nombre y puerto.
     * @return
     */
    IPSData readIPSFile();

}
