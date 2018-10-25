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
public interface IConnection {
       public ILogin login(ILogin ilogin) ;

    public Boolean regBool(ILogin ilogin);

    public IClient getClient();

    public void startPublicThreads();
    
    public void startPrivateThreads();
    
    public void recievePublicMsg(String s);
    
    public void sendPublicMsg(String s);
    
    public void injectBusiness(IBusiness bus);
}
