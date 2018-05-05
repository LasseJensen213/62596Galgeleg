/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author durankose
 */
public interface HighscoreDAO {
    public List<HighscoreDTO> getAllScores() throws DAOException;
    public void addScore(HighscoreDTO highScore) throws DAOException;
    public int getScore(String sNumber) throws DAOException;
    public void updateScore(HighscoreDTO highScore) throws DAOException;
    public void deleteScore(HighscoreDTO highScore) throws DAOException;
}
