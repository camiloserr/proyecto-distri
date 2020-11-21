package IPS.tests;

import IPS.controller.IIPS;
import IPS.controller.IPSServant;
import IPS.persistence.IPSPersistence;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IPSServantUnitTest {

    @Test
    public void pedirVacunasSatisfactoriamente() throws RemoteException {
        IIPS ips = new IPSServant(new IPSPersistence("src/IPS/tests/testConfig.txt","src/IPS/tests/vacunasIps.txt", "src/IPS/tests/ipsData.txt"));

        List<Integer> res = ips.pedirVacunas(5,5,5);
        List<Integer> correctAnswer = new ArrayList<>();
        correctAnswer.add(0);
        correctAnswer.add(0);
        correctAnswer.add(0);
        assertEquals(res, correctAnswer);

    }
}
