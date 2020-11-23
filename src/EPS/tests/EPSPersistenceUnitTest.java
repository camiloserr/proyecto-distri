package EPS.tests;

import EPS.model.UserInfo;
import EPS.persistence.EPSPersistence;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EPSPersistenceUnitTest {

    //Prueba que la unidad de persistencia escriba correctamente el nuevo archivo
    @Test
    public void writeFileCorrectly(){
        EPSPersistence epsPersistence = new EPSPersistence();
        epsPersistence.setFileName("coovema");
        List<UserInfo> auxUsers = new ArrayList<>();
        UserInfo auxUser = new UserInfo(1, "Test", "Subject", "Pendiente", 0);
        auxUsers.add(auxUser);
        try{
            assertEquals(true, epsPersistence.setUserInfo(auxUsers));
        }catch (Exception e){
            assertTrue(false);
        }
    }

    //Prueba que la unidad de persistencia realiza la lectura de archivos correctamente
    @Test
    public void readFileCorrectly(){
        boolean first = true;
        boolean second = true;
        List<UserInfo> auxUsers = new ArrayList<>();
        UserInfo auxUser = new UserInfo(1, "Test", "Subject", "Pendiente", 0);
        auxUsers.add(auxUser);
        EPSPersistence epsPersistence = new EPSPersistence();
        epsPersistence.setFileName("coovema");
        try{
            List<UserInfo> realOne = epsPersistence.readUsrFromFile();
            if(realOne.size() == auxUsers.size()){
                for(int i = 0 ; i < realOne.size() ; ++i){
                    UserInfo auxA = realOne.get(i);
                    UserInfo auxB = auxUsers.get(i);
                    if(auxA.getUniqueId() == auxB.getUniqueId() && auxA.getName().equals(auxB.getName()) &&
                            auxA.getLastName().equals(auxB.getLastName()) && auxA.getState().equals(auxB.getState()) &&
                            auxA.getVaccineRequested() == auxB.getVaccineRequested()){
                    }
                    else{
                        second = false;
                    }
                }
            }
            assertEquals(first, second);
        }catch (Exception e){
            System.err.println("Error: " + e);
        }

    }
}
