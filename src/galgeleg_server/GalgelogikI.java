/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg_server;

import galgeleg_client.Exceptions.NoInstanceOfGame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author durankose
 */
public interface GalgelogikI extends java.rmi.Remote {

    public ArrayList<String> getBrugteBogstaver(String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;

    public String getSynligtOrd(String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;

    public String getOrdet(String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;

    public int getAntalForkerteBogstaver(String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;

    public boolean erSidsteBogstavKorrekt(String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;

    public boolean erSpilletVundet(String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;

    public boolean erSpilletTabt(String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;

    public boolean erSpilletSlut(String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;

    public void nulstil(String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;

    public void opdaterSynligtOrd(String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;

    public void gætBogstav(String identifier,String bogstav) throws java.rmi.RemoteException, NoInstanceOfGame;

    public void logStatus(String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;

    public void hentOrdFraDr(String identifier) throws Exception, java.rmi.RemoteException, NoInstanceOfGame;
    
    public void hentOrdFraDrTV(String identifier) throws Exception, java.rmi.RemoteException, NoInstanceOfGame;

    public boolean auth(String username, String password) throws Exception, java.rmi.RemoteException, NoInstanceOfGame;
    
    
    public GameState updateGameState (String identifier , String letter) throws java.rmi.RemoteException, NoInstanceOfGame;
    
    public GameState getGameState (String identifier) throws java.rmi.RemoteException, NoInstanceOfGame;
    
    
}
