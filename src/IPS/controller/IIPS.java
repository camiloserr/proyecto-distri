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
     * esta función realiza la petición de vacunas a la eps, recive un arreglo de enteros con la petición de cada vacuna
     * y otro arreglo con el estado de la ips en la lectura
     *
     * Si el estado de la lectura no coincide con el estado de la petición, se aborta la transacción,
     * de lo contrario retorna un lista de la cantidad de vacunas que faltaron por entregar.
     *
     * @param peticion
     * @param estado
     * @return
     * @throws RemoteException
     */
    List<Integer> pedirVacunas(int[] peticion, int[] estado ) throws RemoteException;
}
