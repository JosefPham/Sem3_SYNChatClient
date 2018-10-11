/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author Group 9
 */
public class Login {
    
    private final String hMail;
    private final String hPW;
    
    public Login(String mail, String pw) {
        hMail = mail;
        hPW = pw;
    }
    
    protected int login(int result) {
        return 1;
    }
}
