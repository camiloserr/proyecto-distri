package IPS.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IIPS extends Remote {
    /**
     * esta función informa la cantidad de vacunas que acctualmente hay en la ips
     * @return
     * @throws RemoteException
     */
    int[] darVacunaActuales() throws RemoteException;

    /**
     * esta función realiza la petición de vacunas a la eps, recive el parámetro para cada vacuna
     * y retorna un lista de la cantidad de vacunas que faltaron por entregar.
     * @param vA
     * @param vB
     * @param vC
     * @return
     * @throws RemoteException
     */
    List<Integer> pedirVacunas(int vA, int vB, int vC ) throws RemoteException;
}
