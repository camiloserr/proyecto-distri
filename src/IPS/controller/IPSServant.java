package IPS.controller;

import IPS.persistence.IPSPersistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class IPSServant extends UnicastRemoteObject implements IIPS {

    private int vacunaA;
    private int vacunaB;
    private int vacunaC;
    private int minVacunas;
    private int peticion;
    private IPSPersistence persistence;

    // TODO: leer archivo para inicializar #vacuanas, puerto y nombre de la IPS


    public IPSServant() throws RemoteException {
        super();
    }

    public IPSServant(IPSPersistence persistence) throws RemoteException {
        super();
        this.persistence = persistence;
        initVariables();
    }

    private void initVariables() {

        List<Integer> values = persistence.readConfigFile();
        setMinVacunas(values.get(3));
        setPeticion(values.get(4));
        setVacunaA(values.get(0));
        setVacunaB(values.get(1));
        setVacunaC(values.get(2));
    }


    //Cuántas vacunas tiene actualmente la IPS
    @Override
    public int[] darVacunaActuales() {

        int[] vac = {vacunaA, vacunaB, vacunaC};
        return  vac;
    }

    //Hace la petición y actualización de vacunas (No sé si RMI haga los bloqueos necesarios si hay más de una EPS pidiendo)
    //Retorna una lista de las vacunas que entregó exitosamente (por ahora son o todas o ninguna de cada tipo)
    public List<Integer> pedirVacunas(int[] peticion, int[] estado )
    {

        if( !Arrays.equals(this.darVacunaActuales(), estado) )
            return null;
        int cantA = getVacunaA();
        int cantB = getVacunaB();
        int cantC = getVacunaC();
        int vA = peticion[0];
        int vB = peticion[1];
        int vC = peticion[2];
        List< Integer > vlist = new ArrayList<>();

        if( cantA - vA >= 0 )
        {
            vlist.add(0);
            setVacunaA( cantA - vA );
        }
        else {
            vlist.add(vA - cantA);
            setVacunaA(0);
        }
        if( cantB - vB >= 0 )
        {
            vlist.add(0);
            setVacunaB( cantB - vB );
        }
        else{
            vlist.add(vB - cantB);
            setVacunaB(0);
        }
        if( cantC - vC >= 0 )
        {
            vlist.add(0);
            setVacunaC( cantC  - vC );
        }
        else{
            vlist.add(vC - cantC);
            setVacunaC(0);
        }

        persistence.saveState(getVacunaA(),getVacunaB(),getVacunaC(),getMinVacunas(),getPeticion());
        return  vlist;

    }



    public int getVacunaA() {
        return vacunaA;
    }

    public void setVacunaA(int vacunaA) {
        if( vacunaA >= this.vacunaA )
            this.vacunaA = vacunaA;
        else
            this.vacunaA += peticion;
    }

    public int getVacunaB() {
        return vacunaB;
    }

    public void setVacunaB(int vacunaB) {
        if( vacunaB >= this.vacunaB )
            this.vacunaB = vacunaB;
        else
            this.vacunaB += peticion;
    }

    public int getVacunaC() {
        return vacunaC;
    }

    public void setVacunaC(int vacunaC) {
        if( vacunaC>= this.vacunaC )
            this.vacunaC = vacunaC;
        else
            this.vacunaC += peticion;
    }

    public int getMinVacunas() {
        return minVacunas;
    }

    public void setMinVacunas(int minVacunas) {
        this.minVacunas = minVacunas;
    }

    public int getPeticion() {
        return peticion;
    }

    public void setPeticion(int peticion) {
        this.peticion = peticion;
    }
}