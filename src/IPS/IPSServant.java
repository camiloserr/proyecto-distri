package IPS;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IPSServant extends UnicastRemoteObject implements IIPS {

    protected IPSServant() throws RemoteException {
        super();
    }

    @Override
    public String darVacuna(int i) {
        return "toma tu vacuna #"+i;
    }
}
