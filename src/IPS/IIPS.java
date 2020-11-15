package IPS;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IIPS extends Remote {

    String darVacunaActuales() throws RemoteException;
    List<Boolean> pedirVacunas(int vA, int vB, int vC ) throws RemoteException;
}
