package GCC;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IGCC extends Remote {

    String sayHello() throws RemoteException;
    List<Boolean> pedirVacunas(int vA, int vB, int vC, int servP) throws  RemoteException;
}
