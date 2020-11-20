package IPS.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IIPS extends Remote {

    int[] darVacunaActuales() throws RemoteException;
    List<Integer> pedirVacunas(int vA, int vB, int vC ) throws RemoteException;
}
