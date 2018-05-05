/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao;

/**
 *
 * @author durankose
 */
public class HighscoreDTO {
    private String snumber;
    private int score;
    
    public HighscoreDTO(String snumber, int score) {
        this.snumber = snumber;
        this.score = score;
    }
    
    
    
    public String getSnumber() {
        return snumber;
    }
    
    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}
