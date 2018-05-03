package test;

import dao.DAOException;
import dao.HighscoreDAO;
import dao.Highscore;
import dao.HighscoreDAOImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
/**
 *
 * @author durankose
 */
public class HighscoreDAOImplTest {
    
    @Test
    public void testGetAllScores() throws Exception {
        HighscoreDAO dao = getConnection();
        List<Highscore> scores = dao.getAllScores();
        
        Assert.assertNotNull(scores);
        Assert.assertFalse(scores.isEmpty());
        
        for (Highscore score : scores) {
            System.out.println("Studienummer: " + score.getSnumber() + " Score: " + score.getScore());
        }
    }
    
    @Test
    public void testAddScore() throws DAOException   {
        
        HighscoreDAO dao = getConnection();
        Highscore highscore = new Highscore("s147153", 40);
        dao.addScore(highscore);
        
        List<Highscore> scores = dao.getAllScores();
        
        Assert.assertNotNull(scores);
        
        
    }
    
    @Test
    public void testUpdateScore() throws DAOException   {
        
        HighscoreDAO dao = getConnection();
        Highscore highscore = new Highscore("s147153", 20);
        dao.updateScore(highscore);
        int updatedScore = dao.getScore("s147153");
        
        Assert.assertNotNull(dao);
        
        Assert.assertEquals(20, updatedScore);
        
        
    }
    
    @Test
    public void testDeleteScore() throws DAOException {
        HighscoreDAO dao = getConnection();
        Highscore highscore = new Highscore("s147153", 20);
        dao.deleteScore(highscore);
         
    }
    
    private HighscoreDAO getConnection() {
        HighscoreDAO dao = new HighscoreDAOImpl("jdbc:mysql://duko.mynetgear.com:3306/highscore", "hemmeligbruger", "hemmeligkode");
        return dao;
    }
    
}
