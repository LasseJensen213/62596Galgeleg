/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.entity.Highscore;
import java.util.List;

/**
 *
 * @author durankose
 */
public interface HighscoreDAO {
    public List<Highscore> getAllScores();
    public Highscore addScore(Highscore highScore);
    public Highscore getScore(String sNumber);
    public Highscore updateScore(Highscore highScore);
    public Highscore deleteScore(Highscore highScore);
}
