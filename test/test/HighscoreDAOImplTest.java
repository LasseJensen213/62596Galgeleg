package test;

import dao.DAOException;
import dao.HighscoreDAO;
import dao.Highscore;
import dao.HighscoreDAOImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
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
        HighscoreDAO dao = new HighscoreDAOImpl("jdbc:mysql://duko.mynetgear.com:3306/highscore", "hemmeligbruger", "hemmeligkode");
        List<Highscore> scores = dao.getAllScores();

        Assert.assertNotNull(scores);
        Assert.assertFalse(scores.isEmpty());

        for (Highscore score : scores) {
            System.out.println("Studienummer: " + score.getSnumber() + " Score: " + score.getScore());
        }
    }
    
    @Test
    public void testAddScore()   {
        try {
            HighscoreDAO dao = new HighscoreDAOImpl("jdbc:mysql://duko.mynetgear.com:3306/highscore", "hemmeligbruger", "hemmeligkode");
            Highscore highscore = new Highscore("s147153", 40);
            dao.addScore(highscore);
            
            List<Highscore> scores = dao.getAllScores();
            
            Assert.assertNotNull(scores);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
        
    }

}
