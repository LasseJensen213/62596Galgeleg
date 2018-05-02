/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao;

import dao.HighscoreDAO;
import dao.Highscore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author durankose
 */
public class HighscoreDAOImpl implements HighscoreDAO {
    
    
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
    public List<Highscore> getAllScores() throws DAOException{
        List<Highscore> highscores = new ArrayList<Highscore>();
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet resultSet= null;
        try {
            Class.forName(db_driver);
            connection = DriverManager.getConnection(db_url,db_user,db_pass);
            
            String sql = "SELECT * FROM Scores";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while (resultSet.next())
            {
                String sNumber = resultSet.getString("SNumber");
                int score = resultSet.getInt("Score");
                Highscore highscore = new Highscore(sNumber, score);
                highscores.add(highscore);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HighscoreDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } finally {
            closeResources(connection, statement, resultSet);
        }
        
        return highscores;
    }
    
    
    @Override
    public int getScore(String sNumber) {
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet resultSet= null;
        int score=0;
        try {
            Class.forName(db_driver);
            connection = DriverManager.getConnection(db_url,db_user,db_pass);
            
            String sql = "SELECT * FROM Scores WHERE SNumber = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, sNumber);
            
            resultSet = statement.executeQuery();
            score = resultSet.getInt("Score");
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HighscoreDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HighscoreDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return score;
    }
    
    
    @Override
    public Highscore updateScore(Highscore highScore) {
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet resultSet= null;
        try {
            Class.forName(db_driver);
            connection = DriverManager.getConnection(db_url,db_user,db_pass);
            
            String sql = "SELECT * FROM Scores WHERE SNumber = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, null);
            
            resultSet = statement.executeQuery();
      
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HighscoreDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HighscoreDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(connection, statement, resultSet);
        }
        return null;
    }
    
    @Override
    public Highscore deleteScore(Highscore highScore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void addScore(Highscore highScore) throws DAOException{
        
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet resultSet= null;
        try {
            
            Class.forName(db_driver);
            
            connection = DriverManager.getConnection(db_url,db_user,db_pass);
            
            String sql = "INSERT INTO Scores(SNumber, Score) VALUES (?, ?)";
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, highScore.getSnumber());
            statement.setInt(2, highScore.getScore());
            statement.executeUpdate();
            
            
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(HighscoreDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(connection, statement, resultSet);
        }
        
        
    }
    
    private void closeResources(Connection connection, PreparedStatement statement,ResultSet resultSet) {
        //lukker alt forbindelse
        if (resultSet!=null)
        {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(HighscoreDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (statement!=null)
        {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(HighscoreDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (connection!=null)
        {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(HighscoreDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
