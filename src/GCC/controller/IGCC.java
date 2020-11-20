package GCC.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IGCC extends Remote {

    //TODO: esto no deberian ser booleans, sino int porque puede que le respondan solo una fraccion de un tipo de vacunas
    List<Integer> pedirVacunas(int vA, int vB, int vC) throws  RemoteException;
}
