/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Acquaintance.IManagement;

/**
 *
 */
public class Management implements IManagement {
    
    ClientSystem client = ClientSystem.getInstance();
    
    int action;
    int userID;
    String newPw;
    String pw;
    String string; //Either mail or password
    
    public Management(int action, int userID, String pw, String string) {
        this.action = action;
        this.userID = client.getCurrentUser().getUserID();
        this.pw = pw;
        this.string = string;
    }

    public int getAction() {
        return action;
    }

    public int getUserID() {
        return userID;
    }

    public String getPw() {
        return pw;
    }

    public String getString() {
        return string;
    }
    
    

}
