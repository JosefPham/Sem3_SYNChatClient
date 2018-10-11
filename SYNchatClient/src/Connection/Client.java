/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Pottemuld
 */
public class Client {

    
    InetAddress ip;
    int port = 8080;

    public Client() {
        try {
            this.ip = (InetAddress) InetAddress.getByName("10.126.37.220");
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    this.port = port;
    
    }
    
    public void connectToServer(){
        try {
            System.out.println("Connecting to " + ip + " on port " + port + "");
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket("10.126.37.220", port);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    } 
        
    public static void main(String[] args) {
        Client client = new Client();
        client.connectToServer();
    }






}


