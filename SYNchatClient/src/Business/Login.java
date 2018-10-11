/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Acquaintance.ILogin;

/**
 *
 * @author Group 9
 */
public class Login implements ILogin {
    
    private final String hMail;
    private final String hPW;
    
    public Login(String mail, String pw) {
        hMail = mail;
        hPW = pw;
    }

    @Override
    public String gethMail() {
        return hMail;
    }

    @Override
    public String gethPW() {
        return hPW;
    }
    
    @Override
    public int login(int loginValue) {
        return loginValue;
    }
}
