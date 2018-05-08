/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.rmi.Remote;

/**
 *
 * @author durankose
 */
public class HighscoreDTO implements Remote, java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String snumber;
	private transient int score;
	private String word;

	public HighscoreDTO(String word, String snumber, int score) {
		this.word = word;
		this.snumber = snumber;
		this.score = score;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSnumber() {
		return snumber;
	}

	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
