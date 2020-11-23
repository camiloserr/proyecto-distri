package GCC.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IGCC extends Remote {

    List<Integer> pedirVacunas(int vA, int vB, int vC) throws  RemoteException;

    /**
     * revisa que coincida el user y el password en la persistencia
     * @param user
     * @param password
     * @return true si coinciden
     */
    boolean login(String user, String password) throws RemoteException;

    /**
     * Mete a un usuario y su contraseña a la base de datos
     * @param user
     * @param password
     * @return true si lo logra registrar al usuario
     */
    boolean register(String user, String password) throws RemoteException;

}
