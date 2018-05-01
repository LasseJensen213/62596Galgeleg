package test;


import dao.HighscoreDAO;
import dao.entity.Highscore;
import dao.impl.HighscoreDAOImpl;
import java.util.List;
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
    public void testGetAllScores() throws Exception{
         HighscoreDAO dao = new HighscoreDAOImpl("jdbc:mysql://duko.mynetgear.com:3306/highscore","hemmeligbruger","hemmeligkode");
         List<Highscore> scores = dao.getAllScores();
         
         Assert.assertNotNull(scores);
         
         for (Highscore score: scores){
             System.out.println("Studienummer: " + score.getSnumber()+" Score: "+score.getScore());
         }
    }
    
}
