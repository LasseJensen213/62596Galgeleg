/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg;

import java.rmi.Naming;

/**
 *
 * @author durankose
 */
public class GalgeServer {
    
    public static void main(String[] arg) throws Exception {
        
        java.rmi.registry.LocateRegistry.createRegistry(1099);
        
        GalgelogikI k = new GalgelogikImpl();
        Naming.rebind("rmi://localhost/galgetjeneste", k);
        System.out.println("Galgeleg tjeneste registreret.");
    }
    
}
