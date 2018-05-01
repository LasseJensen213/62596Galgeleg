/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao.impl;

import dao.HighscoreDAO;
import dao.entity.Highscore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author durankose
 */
public class HighscoreDAOImpl implements HighscoreDAO{
    
    
    private String db_url;
    private String db_user;
    private String db_pass;
    private final String db_driver = "com.mysql.jdbc.Driver";
    /*
    private final String db_url = "jdbc:mysql://duko.mynetgear.com:3306/highscore";
    private final String db_driver = "com.mysql.jdbc.Driver";
    private final String db_user = "hemmeligbruger";
    private final String db_pass = "hemmeligkode";
    */
    
    
    
    public HighscoreDAOImpl(String db_url, String db_user, String db_pass) {
        this.db_url = db_url;
        this.db_user = db_user;
        this.db_pass = db_pass;
        
        
    }
    
    
    @Override
    public List<Highscore> getAllScores() {
        try {
            Class.forName(db_driver);
            Connection connection = DriverManager.getConnection(db_url,db_user,db_pass);
            
            String sql = "SELECT * FROM Scores";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next())
            {
                
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HighscoreDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HighscoreDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @Override
    public Highscore getScore(String HighScore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    @Override
    public Highscore updateScore(Highscore highScore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Highscore deleteScore(Highscore highScore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Highscore addScore(Highscore highScore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
