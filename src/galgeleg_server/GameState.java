/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg_server;

import java.rmi.Remote;
import java.util.ArrayList;

/**
 *
 * @author Jimmy
 */
public class GameState implements Remote, java.io.Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public String ordet;
    public String synligtOrd;
    public ArrayList<String> brugteBogstaver;
    public int antalForkerteBogstaver;
    public boolean sidsteBogstavVarKorrekt;
    public boolean spillertErVundet;
    public boolean SpilletErTabt;
}
