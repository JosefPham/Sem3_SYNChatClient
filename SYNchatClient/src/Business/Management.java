/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 */
public class Management {
    
    ClientSystem client = ClientSystem.getInstance();
    
    int action;
    int userID;
    String newPw;
    String oldPw;
    String mail;

    
    //Method for creating an object of Management for verifyPw purposes
    public Management(int action, String oldPw) {
        this.action = action;
        this.userID = client.getCurrentUser().getUserID();
        this.oldPw = oldPw;
    }

    //Method for creating an object of Management for changeMail purposes
    public Management(int action, String oldPw, String mail) {
        this.action = action;
        this.oldPw = oldPw;
        this.mail = mail;
    }

}
