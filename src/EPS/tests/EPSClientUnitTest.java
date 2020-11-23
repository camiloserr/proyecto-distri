package EPS.tests;

import EPS.controller.EPSClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EPSClientUnitTest {

    @Test
    public void makeLogInSuccesfully(){
        EPSClient epsClient = new EPSClient();

        try{
            assertEquals(true, epsClient.authLogIn("coovema", "hola"));
        }catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void makeSignUpSuccesfully(){
        EPSClient epsClient = new EPSClient();
        try{
            assertEquals(true, epsClient.authSignUp("coovema", "hola"));
        }catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void makeOrderSuccesfully(){
        EPSClient epsClient = new EPSClient();
        try{
            assertEquals(true, epsClient.makeOrder());
        }catch (Exception e){
            assertTrue(false);
        }
    }
}
