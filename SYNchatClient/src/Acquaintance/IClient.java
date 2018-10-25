/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

/**
 *
 * @author Peter
 */
public interface IClient {
     void send(Object o);
     ILogin recieveLogin();
    Boolean recieveBool();
    void startPublicThreads();
    void startPrivateThreads();
}
