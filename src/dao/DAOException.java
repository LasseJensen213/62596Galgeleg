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
public class DAOException extends Exception{
    
    public DAOException(String errorMessage){
        super(errorMessage);
    }
    
    public DAOException(Throwable cause){
        super(cause);
    }
}

