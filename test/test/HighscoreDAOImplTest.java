package test;

import dao.DAOException;
import dao.HighscoreDAO;
import dao.Highscore;
import dao.HighscoreDAOImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;


/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
/**
 *
 * @author durankose
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HighscoreDAOImplTest {
    private HighscoreDAO dao = null;
    private Highscore highScore = null;
    
    @Before
    public void setUp() {
        dao = getConnection();
        highScore = new Highscore("test",10);
    }
    
    
    
    @Test
    public void test1AddScore() throws DAOException   {
        System.out.println("Executing first test");
        
        dao.addScore(highScore);
        
        Assert.assertNotNull(dao.getScore(highScore.getSnumber()));
        
        
    }
    
    @Test
    public void test2GetAllScores() throws Exception {
        System.out.println("Executing second test");
        List<Highscore> scores = dao.getAllScores();
        
        Assert.assertNotNull(scores);
        Assert.assertFalse(scores.isEmpty());
        
        for (Highscore score : scores) {
            System.out.println("Studienummer: " + score.getSnumber() + " Score: " + score.getScore());
        }
    }
    
    @Test
    public void test3UpdateScore() throws DAOException   {
        System.out.println("Executing third test");
        highScore.setScore(20);
        dao.updateScore(highScore);
        int updatedScore = dao.getScore("test");
        
        Assert.assertNotNull(dao);
        
        Assert.assertEquals(20, updatedScore);
        
    }
    
    
    @Test
    public void test4DeleteScore() throws DAOException {
        System.out.println("Executing fourth test");
        dao.deleteScore(highScore);
        
        //Assert.assertEquals(20,dao.getScore("test"));
        
    }
    
    private HighscoreDAO getConnection() {
        HighscoreDAO dao = new HighscoreDAOImpl("jdbc:mysql://duko.mynetgear.com:3306/highscore", "hemmeligbruger", "hemmeligkode");
        return dao;
    }
    /*
    @AfterClass
    public static void cleanUp() throws DAOException{
    dao.deleteScore(highScore);
    }
    */
    
    
    
}
