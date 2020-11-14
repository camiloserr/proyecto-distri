package IPS;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IIPS extends Remote {

    public String darVacuna(int i) throws RemoteException;
}
