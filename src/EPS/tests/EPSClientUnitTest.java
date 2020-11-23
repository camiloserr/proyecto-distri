package EPS.tests;

import EPS.controller.EPSClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EPSClientUnitTest {

    //Prueba que la EPS esté en capacidad de hacer un ingreso exitoso
    @Test
    public void makeLogInSuccesfully(){
        EPSClient epsClient = new EPSClient();

        try{
            assertEquals(true, epsClient.authLogIn("coovema", "hola"));
        }catch (Exception e){
            assertTrue(false);
        }
    }

    //Prueba que la EPS esté en capacidad de registrarse exitosamente
    @Test
    public void makeSignUpSuccesfully(){
        EPSClient epsClient = new EPSClient();
        try{
            assertEquals(true, epsClient.authSignUp("coovema", "hola"));
        }catch (Exception e){
            assertTrue(false);
        }
    }

    //Prueba que la EPS esté en capacidad de hacer una solicitud exitosamente
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
