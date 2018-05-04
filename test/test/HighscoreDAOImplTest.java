package test;

import dao.DAOException;
import dao.HighscoreDAO;
import dao.Highscore;
import dao.HighscoreDAOImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
    HighscoreDAO dao = null;
    Highscore highScore = null;
    
    @Before
    public void setUp() {
        dao = getConnection();
        highScore = new Highscore("test",10);
    }
    
    @Test
    public void testGetAllScores() throws Exception {
//        HighscoreDAO dao = getConnection();
        List<Highscore> scores = dao.getAllScores();
        
        Assert.assertNotNull(scores);
        Assert.assertFalse(scores.isEmpty());
        
        for (Highscore score : scores) {
            System.out.println("Studienummer: " + score.getSnumber() + " Score: " + score.getScore());
        }
    }
    
    @Test
    public void testAddScore() throws DAOException   {
        
        dao.addScore(highScore);
        
        Assert.assertNotNull(dao.getScore(highScore.getSnumber()));
        
        
    }
    
    @Test
    public void testUpdateScore() throws DAOException   {
        
        highScore.setScore(20);
        dao.updateScore(highScore);
        int updatedScore = dao.getScore("test");
        
        Assert.assertNotNull(dao);
        
        Assert.assertEquals(20, updatedScore);
            
    }
    
    @Ignore
    public void testDeleteScore() throws DAOException {

        dao.deleteScore("test");
        Assert.assertNotNull(dao);
        
    }
    
    private HighscoreDAO getConnection() {
        HighscoreDAO dao = new HighscoreDAOImpl("jdbc:mysql://duko.mynetgear.com:3306/highscore", "hemmeligbruger", "hemmeligkode");
        return dao;
    }
    
}
