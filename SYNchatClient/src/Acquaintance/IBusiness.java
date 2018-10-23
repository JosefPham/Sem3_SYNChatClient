/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

/**
 *
 * @author Sigurd E. Espersen
 */
public interface IBusiness {
    public void injectConnection(IConnection con);

    public int login(String mail, String pw);
    
    public Boolean regUser(String tmpName, String mail, String pw);
}
