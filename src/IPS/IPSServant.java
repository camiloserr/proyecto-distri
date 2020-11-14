package IPS;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class IPSServant extends UnicastRemoteObject implements IIPS {

    private int vacunaA;
    private int vacunaB;
    private int vacunaC;

    protected IPSServant() throws RemoteException {
        super();
        setVacunaA( 10 );
        setVacunaB( 10 );
        setVacunaC( 10 );

    }

    @Override
    public String darVacuna(int i) {
        return "toma tu vacuna #"+i;
    }

    public List<Boolean> pedirVacunas(int vA, int vB, int vC )
    {
        boolean darA = false;
        boolean darB = false;
        boolean darC = false;
        List< Boolean > vlist = new ArrayList<>();

        if( getVacunaA() - vA < 0 )
        {
            darA = true;
            setVacunaA( getVacunaA() - vA );
        }
        if( getVacunaB() - vB < 0 )
        {
            darB = true;
            setVacunaB( getVacunaB() - vA );
        }
        if( getVacunaC() - vC < 0 )
        {
            darC = true;
            setVacunaC( getVacunaC() - vA );
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
        return vacunaA;
    }

    public void setVacunaB(int vacunaA) {
        this.vacunaA = vacunaA;
    }

    public int getVacunaC() {
        return vacunaA;
    }

    public void setVacunaC(int vacunaA) {
        this.vacunaA = vacunaA;
    }

}
