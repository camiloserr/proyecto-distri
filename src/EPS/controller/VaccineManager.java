package EPS.controller;


import GCC.controller.IGCC;
import IPS.controller.IIPS;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.rmi.RemoteException;
import java.util.List;

/**
 * proporciona una abstraccion para que la EPS pida las vacunas a el GCC
 */
public class VaccineManager {

    //el servidor al cual se le pediran las vacunas
    private IGCC gcc;

    //vacunas que se van a pedir a el gcc
    private int[] currentOrder;


    public VaccineManager(IGCC gcc){
        this.gcc = gcc;
        int[] order = {0,0,0};
        currentOrder = order;
    }

    /**
     * numero de vacunas en la transaccion
     * @param cantidad
     * @param tipo
     * @return estado actual de la transaccion
     * @throws IllegalArgumentException
     */
    public int addVaccine(int cantidad, int tipo) throws  IllegalArgumentException{
        if(tipo < 0 || tipo >= 3 || cantidad < 0){
            throw new IllegalArgumentException("Tipo de vacuna invalido");
        }
        currentOrder[tipo] += cantidad;
        return currentOrder[0] + currentOrder[1] + currentOrder[2];
    }

    public int[] getCurrentOrder(){
        return this.currentOrder;
    }

    /**
     * pide las vacunas que tiene actualmente en la lista a el GCC
     * @return arreglo con las vacunas que le llegaron
     */
    //TODO: esto deberian ser ints, no booleans
    public List<Boolean> makeOrder() throws RemoteException {
        List<Boolean> vacRecib = gcc.pedirVacunas(currentOrder[0], currentOrder[1], currentOrder[2]);
        // como el gcc tal vaz no responde con todas las vacunas, las vacunas que no fueron recibidas se mantienen
        // en la orden para ser pedidas en la proxima orden
        for(int i = 0 ; i<vacRecib.size() ; ++i){
            if(vacRecib.get(i) == true){
                currentOrder[i] = 0;
            }
        }

        return vacRecib;
    }

}
