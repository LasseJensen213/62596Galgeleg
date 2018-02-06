/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author durankose
 */
public interface GalgelogikI extends java.rmi.Remote {

    public ArrayList<String> getBrugteBogstaver() throws java.rmi.RemoteException;

    public String getSynligtOrd() throws java.rmi.RemoteException;

    public String getOrdet() throws java.rmi.RemoteException;

    public int getAntalForkerteBogstaver() throws java.rmi.RemoteException;

    public boolean erSidsteBogstavKorrekt() throws java.rmi.RemoteException;

    public boolean erSpilletVundet() throws java.rmi.RemoteException;

    public boolean erSpilletTabt() throws java.rmi.RemoteException;

    public boolean erSpilletSlut() throws java.rmi.RemoteException;

    public void nulstil() throws java.rmi.RemoteException;

    public void opdaterSynligtOrd() throws java.rmi.RemoteException;

    public void gætBogstav(String bogstav) throws java.rmi.RemoteException;

    public void logStatus() throws java.rmi.RemoteException;

    public void hentOrdFraDr() throws Exception, java.rmi.RemoteException;

    

    
    
}
