package EPS.tests;

import EPS.controller.VaccineManager;
import GCC.controller.IGCC;
import org.junit.Assert;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VaccineManagerUnitTest {


    @Test
    public void makeOrderSuccesfully(){

        List<Boolean> res = new ArrayList<Boolean>();

        res.add(true);
        res.add(false);
        res.add(true);


        // creates a VaccineManager with a mock IGCC
        VaccineManager vm = new VaccineManager(new IGCC() {
            @Override
            public List<Boolean> pedirVacunas(int vA, int vB, int vC) throws RemoteException {


                return res;
            }
        });

        try {
            assertEquals(res, vm.makeOrder());
        } catch (RemoteException e) {
            assertTrue(false);
        }

    }


    @Test
    public  void addVaccineSuccesFully(){

        VaccineManager vm = new VaccineManager(new IGCC() {
            @Override
            public List<Boolean> pedirVacunas(int vA, int vB, int vC) throws RemoteException {
                return null;
            }
        });

        int[] res = {0,0,0};
        Assert.assertArrayEquals(vm.getCurrentOrder(), res);

        vm.addVaccine(5, 2);
        res[2] += 5;
        Assert.assertArrayEquals(vm.getCurrentOrder(), res);

        vm.addVaccine(4, 1);
        res[1]+=4;
        Assert.assertArrayEquals(vm.getCurrentOrder(), res);

        vm.addVaccine(7, 0);
        res[0]+=7;
        Assert.assertArrayEquals(vm.getCurrentOrder(), res);


    }


    @Test (expected = IllegalArgumentException.class)
    public void addVaccineIllegalArgument(){
        VaccineManager vm = new VaccineManager(new IGCC() {
            @Override
            public List<Boolean> pedirVacunas(int vA, int vB, int vC) throws RemoteException {
                return null;
            }
        });

        vm.addVaccine(7, 9);


    }
}
