/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg_server;

import galgeleg_server.GalgelogikI;
import galgeleg_server.GalgelogikImpl;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.Registry;

/**
 *
 * @author durankose
 */
public class GalgeServer {

    public static void main(String[] arg) throws Exception {
        
       
    

        //java.rmi.registry.LocateRegistry.createRegistry(1151);
        GalgelogikI gm = new GameManager();
        //GalgelogikI k = new GalgelogikImpl();
        //Naming.rebind("rmi://[::]/galgetjeneste", k);
        
        System.setProperty("java.rmi.server.hostname", "ubuntu4.saluton.dk");
	//Naming.rebind("rmi://ubuntu4.saluton.dk:1151/galgetjeneste", gm);
        
        //Naming.rebind("rmi://localhost/galgetjeneste", gm);
        java.rmi.registry.LocateRegistry.createRegistry(1151); // start rmiregistry i server-JVM
        Naming.rebind("rmi://ubuntu4.saluton.dk:1151/galgetjeneste", gm);
        

        System.out.println("Galgeleg tjeneste registreret.");

        //this must start seperately from the server
        //GalgeLogin galgeLogin = new GalgeLogin();
        //galgeLogin.setVisible(true);
    }

}
