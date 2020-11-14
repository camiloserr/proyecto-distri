package GCC;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGCC extends Remote {

    String sayHello() throws RemoteException;
}
