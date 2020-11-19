package IPS.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class IPSServant extends UnicastRemoteObject implements IIPS {

    private int vacunaA;
    private int vacunaB;
    private int vacunaC;


    // TODO: leer archivo para inicializar #vacuanas, puerto y nombre de la IPS
    protected IPSServant() throws RemoteException {
        super();
        setVacunaA( 10 );
        setVacunaB( 10 );
        setVacunaC( 10 );

    }

    protected IPSServant(int a, int b , int c) throws RemoteException {
        super();
        setVacunaA( a );
        setVacunaB( b );
        setVacunaC( b );

    }


    //Cuántas vacunas tiene actualmente la IPS
    @Override
    public int[] darVacunaActuales() {

        int[] vac = {vacunaA, vacunaB, vacunaC};
        return  vac;
    }

    //Hace la petición y actualización de vacunas (No sé si RMI haga los bloqueos necesarios si hay más de una EPS pidiendo)
    //Retorna una lista de las vacunas que entregó exitosamente (por ahora son o todas o ninguna de cada tipo)
    public List<Boolean> pedirVacunas(int vA, int vB, int vC )
    {
        boolean darA = false;
        boolean darB = false;
        boolean darC = false;
        int cantA = getVacunaA();
        int cantB = getVacunaB();
        int cantC = getVacunaC();
        List< Boolean > vlist = new ArrayList<>();

        if( cantA - vA >= 0 )
        {
            darA = true;
            setVacunaA( cantA - vA );
        }
        if( cantB - vB >= 0 )
        {
            darB = true;
            setVacunaB( cantB - vB );
        }
        if( cantC - vC >= 0 )
        {
            darC = true;
            setVacunaC( cantC  - vC );
        }

        vlist.add(darA);
        vlist.add(darB);
        vlist.add(darC);

        return  vlist;

    }



    public int getVacunaA() {
        return vacunaA;
    }

    public void setVacunaA(int vacunaA) {
        this.vacunaA = vacunaA;
    }

    public int getVacunaB() {
        return vacunaB;
    }

    public void setVacunaB(int vacunaA) {
        this.vacunaB = vacunaA;
    }

    public int getVacunaC() {
        return vacunaC;
    }

    public void setVacunaC(int vacunaA) {
        this.vacunaC = vacunaA;
    }

}
