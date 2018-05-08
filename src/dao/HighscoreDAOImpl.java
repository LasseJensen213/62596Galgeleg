/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao;

import dao.HighscoreDAO;
import dao.HighscoreDTO;
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
    
    
	/*
    private String db_url;
    private String db_user;
    private String db_pass;
    private final String db_driver = "com.mysql.jdbc.Driver";
    */
    private final String db_url = "jdbc:mysql://duko.mynetgear.com:3306/highscore";
    private final String db_driver = "com.mysql.jdbc.Driver";
    private final String db_user = "hemmeligbruger";
    private final String db_pass = "hemmeligkode";
    
    
    
    /*
    public HighscoreDAOImpl(String db_url, String db_user, String db_pass) {
        this.db_url = db_url;
        this.db_user = db_user;
        this.db_pass = db_pass;
        
        
    }
    */
    
    public HighscoreDAOImpl() {
    	
    }
    
    @Override
    public List<HighscoreDTO> getAllScores() throws DAOException{
        List<HighscoreDTO> highscores = new ArrayList<HighscoreDTO>();
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet resultSet= null;
        try {
            
            connection = getConnection();
            
            String sql = "CALL getAllHighscores()";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while (resultSet.next())
            {
            	String word = resultSet.getString("word");
                String sNumber = resultSet.getString("sNumber");
                int score = resultSet.getInt("score");
                HighscoreDTO highscore = new HighscoreDTO(word, sNumber, score);
                highscores.add(highscore);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } finally {
            closeResources(connection, statement, resultSet);
        }
        
        return highscores;
    }
    
    
    
    

    
    @Override
    public void addScore(HighscoreDTO highScore) throws DAOException{
        
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet resultSet= null;
        try {
            
            connection = getConnection();
            
            String sql = "CALL addNewHighscore(?,?,?)";
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, highScore.getWord());
            statement.setString(2, highScore.getSnumber());
            statement.setInt(3, highScore.getScore());
            statement.executeUpdate();
            
            
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } finally {
            closeResources(connection, statement, resultSet);
        }
        
        
    }
    
    private Connection getConnection() throws DAOException {
        try {
            Class.forName(db_driver);
            
            return DriverManager.getConnection(db_url,db_user,db_pass);
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ClassNotFoundException ex) {
            throw new DAOException(ex);
        }
        
        
    }
    
    private void closeResources(Connection connection, PreparedStatement statement,ResultSet resultSet) throws DAOException {
        //lukker alt forbindelse
        if (resultSet!=null)
        {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        }
        if (statement!=null)
        {
            try {
                statement.close();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        }
        if (connection!=null)
        {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        }
    }
    
}
