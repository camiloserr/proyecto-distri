package IPS.tests;

import IPS.controller.IIPS;
import IPS.controller.IPSServant;
import IPS.persistence.IPSPersistence;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IPSServantUnitTest {

    @Test
    public void pedirVacunasSatisfactoriamente() throws RemoteException {
        IIPS ips = new IPSServant(new IPSPersistence("src/IPS/tests/testConfig.txt","src/IPS/tests/vacunasIps.txt", "src/IPS/tests/ipsData.txt"));

        List<Integer> res = ips.pedirVacunas(new int[] {5,5,5}, ips.darVacunaActuales());
        List<Integer> correctAnswer = new ArrayList<>();
        correctAnswer.add(0);
        correctAnswer.add(0);
        correctAnswer.add(0);
        assertEquals(correctAnswer, res);

    }

    @Test
    public void pedirVacunasError() throws RemoteException {
        IIPS ips = new IPSServant(new IPSPersistence("src/IPS/tests/testConfig.txt","src/IPS/tests/vacunasIps.txt", "src/IPS/tests/ipsData.txt"));
        List<Integer> res = ips.pedirVacunas(new int[] {5,5,5}, new int[] {55,57,52});

        assertNull(res);

    }
}
