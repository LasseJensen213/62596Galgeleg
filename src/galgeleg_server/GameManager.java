/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg_server;

import galgeleg_client.Exceptions.NoInstanceOfGame;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameManager extends UnicastRemoteObject implements GalgelogikI {

    Map<String, GalgelogikImpl> listOfGames = new HashMap<>();

    public GameManager() throws java.rmi.RemoteException {
        System.out.println("BeforeThread");
        (new Thread(new CleanUpThread())).start();
        System.out.println("AfterThread");
    }

    private boolean gameInList(String identifier) {
        return listOfGames.containsKey(identifier);
    }
    private long getTimeOfLastCommunication(String identifier) throws NoInstanceOfGame {
        GalgelogikImpl returnValue;
        returnValue = listOfGames.get(identifier);

        if (returnValue == null) {
            throw new NoInstanceOfGame();
        } else {
            return returnValue.getTimeOfLastCommunication();
        }
    }

    private GalgelogikImpl getGame(String identifier) throws NoInstanceOfGame {
        System.out.println("Identifier:" + identifier);
        GalgelogikImpl returnValue;
        returnValue = listOfGames.get(identifier);

        if (returnValue == null) {
            throw new NoInstanceOfGame();
        } else {
            returnValue.updateTimeOfLastCommunication();
            return returnValue;
        }
    }

    public void addGame(GalgelogikImpl gameToBeAdded, String identifier) {
        listOfGames.put(identifier, gameToBeAdded);
    }

    @Override
    public ArrayList<String> getBrugteBogstaver(String identifier) throws RemoteException, NoInstanceOfGame {
        return getGame(identifier).getBrugteBogstaver(identifier);

    }

    @Override
    public String getSynligtOrd(String identifier) throws RemoteException, NoInstanceOfGame {
        return getGame(identifier).getSynligtOrd(identifier);
    }

    @Override
    public String getOrdet(String identifier) throws RemoteException, NoInstanceOfGame {
        return getGame(identifier).getOrdet(identifier);
    }

    @Override
    public int getAntalForkerteBogstaver(String identifier) throws RemoteException, NoInstanceOfGame {
        return getGame(identifier).getAntalForkerteBogstaver(identifier);
    }

    @Override
    public boolean erSidsteBogstavKorrekt(String identifier) throws RemoteException, NoInstanceOfGame {
        return getGame(identifier).erSidsteBogstavKorrekt(identifier);
    }

    @Override
    public boolean erSpilletVundet(String identifier) throws RemoteException, NoInstanceOfGame {
        return getGame(identifier).erSpilletVundet(identifier);
    }

    @Override
    public boolean erSpilletTabt(String identifier) throws RemoteException, NoInstanceOfGame {
        return getGame(identifier).erSpilletTabt(identifier);
    }

    @Override
    public boolean erSpilletSlut(String identifier) throws RemoteException, NoInstanceOfGame {
        return getGame(identifier).erSpilletSlut(identifier);
    }

    @Override
    public void nulstil(String identifier) throws RemoteException, NoInstanceOfGame {
        getGame(identifier).nulstil(identifier);
    }

    @Override
    public void opdaterSynligtOrd(String identifier) throws RemoteException, NoInstanceOfGame {
        getGame(identifier).opdaterSynligtOrd(identifier);
    }

    @Override
    public void gætBogstav(String identifier, String bogstav) throws RemoteException, NoInstanceOfGame {
        getGame(identifier).gætBogstav(identifier, bogstav);
    }

    @Override
    public void logStatus(String identifier) throws RemoteException, NoInstanceOfGame {
        getGame(identifier).logStatus(identifier);
    }

    @Override
    public void hentOrdFraDr(String identifier) throws Exception, RemoteException, NoInstanceOfGame {
        getGame(identifier).hentOrdFraDr(identifier);
    }

    @Override
    public void hentOrdFraDrTV(String identifier) throws Exception, RemoteException, NoInstanceOfGame {
        getGame(identifier).hentOrdFraDrTV(identifier);
    }

    @Override
    public boolean auth(String username, String password) throws Exception, RemoteException, NoInstanceOfGame {
        if (!gameInList(username)) {
            System.out.println("bitch");
            GalgelogikImpl newGame = new GalgelogikImpl(username);
            addGame(newGame, username);
            return newGame.auth(username, password);

        } else {
            return getGame(username).auth(username, password);
        }

    }
    @Override
    public GameState updateGameState (String identifier , String letter) throws java.rmi.RemoteException, NoInstanceOfGame
    {
        return getGame(identifier).updateGameState(identifier, letter);
    }
    @Override
    public GameState getGameState (String identifier) throws java.rmi.RemoteException, NoInstanceOfGame
    {
        return getGame(identifier).getGameState(identifier);
    }

    class CleanUpThread implements Runnable {
        @Override
        public void run() {
            System.out.println("We up and running");
            
            while (true) {
                System.out.println("We up and running");
                Set<String> keys = listOfGames.keySet();
                for (String s : keys) {
                    //Time 3 hours:21600000
                    try {
                        System.out.println(s);
                        System.out.println(System.currentTimeMillis() - getTimeOfLastCommunication(s));
                        if (System.currentTimeMillis() - getTimeOfLastCommunication(s) > 21600000) {
                            listOfGames.remove(s);
                            System.out.println("Removed instance of game for user: " + s);
                        }
                    } catch (NoInstanceOfGame ex) {
                        Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                try {
                        Thread.sleep(3600000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }

        }

    }

}
