/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Acquaintance.IUser;
import java.util.List;

/**
 *
 */
public class User extends IUser {
    
    String email;
    String password;
    String firstName;
    String lastName;
    int userID;
    boolean isBanned;
    int reports;
    List<Chat> chats;

    public User(String firstName, String lastName, String email, String passwordString) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = passwordString;
    }

    public User(int userID, String firstName, String lastName, String email, String password, boolean isBanned, int reports, List<Chat> chats) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isBanned = isBanned;
        this.reports = reports;
        this.chats = chats;
    }
    
//    public boolean registerNewUser(String firstName, String lastName, String email, String password) {
//        if(*mail is not already registered*){
//            if true, register user
//            User currentUser = new User(firstName, lastName, email, password);
//        }
//        *Call server in order to create userID and such*
//        
//        When server has registered the user with all info return true
//        return true;
//        }
//        else {
//            return false;
//        }
    }


