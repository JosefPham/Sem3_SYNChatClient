/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Acquaintance.ILogin;
import Business.Login; // outcomment
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pottemuld
 */
public class Client{

    Socket serverSocket;
    InetAddress ip;
    int port = 8080;
    private DataInputStream console; // takes input from keyboard (system in)
    private ObjectInputStream input;  // takes the stream from the server socket - incoming messages
    private ObjectOutputStream output; // outgoing messages - taken from console
    Thread sendMessage, readMessage;

    public Client() {
        try {
            this.ip = (InetAddress) InetAddress.getByName("10.126.33.99");
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.port = port;
        
        
        //outcomment
        ILogin login = new Login("Hej@Peter.dk", "12345678");
        
        
        connectToServer();
        sendLogin(login);
        recieveLogin();
        
        startPublicThreads();
        
     //   startPrivateThreads();
        

    }

    public void connectToServer() {
        try {
            System.out.println("Connecting to " + ip + " on port " + port + "");
            this.serverSocket = new Socket(ip,port);
            console = new DataInputStream(System.in);
            output = new ObjectOutputStream(serverSocket.getOutputStream());
            input = new ObjectInputStream(serverSocket.getInputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    public void sendLogin(ILogin login){
        try {
            output.writeObject(login);
            System.out.println("Sent login");
           
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ILogin recieveLogin(){
        System.out.println("Entered recievedLogin");
      while(true){
            try {
                System.out.println("Trying to recieve login info");
                ILogin recievedLogin =  (ILogin) input.readObject();
                if(recievedLogin!=null){
                    return recievedLogin;
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
      }  
       
    }
    
    
    public void startPrivateThreads(){
            
        Scanner scan = new Scanner(System.in);
        
        Thread sendMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){ 
            try { 
                InetAddress local = InetAddress.getLocalHost();
                String msg = scan.nextLine();
                try {
                    
                    output.writeUTF(":" + local + "   " + msg);
                    
                    //     System.out.println("Sending");
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } // Sæt vores tråd til null ved finally
            } catch (UnknownHostException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } // Sæt vores tråd til null ved finally
        }
            }
        });
        
        
        
        
        Thread readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
        try {
            while(true){
            //    System.out.println("HEllo");
                String text = (String) input.readObject(); // writeObject
                System.out.println(text);
            }
        } catch (Exception e) {
        } finally{
             // Sæt vores tråd til null
            try{
                output.close();
            } catch (IOException ex) {
                ex.printStackTrace ();
            }
        }
            }
        });
        
        
        sendMessage.start();
        readMessage.start();
    }
    
    
    public void startPublicThreads(){
         
        Scanner scan = new Scanner(System.in);
        
     
        
        sendMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){ 
            try { 
                InetAddress local = InetAddress.getLocalHost();
                String msg = scan.nextLine();
                try {
                    
                    output.writeObject(local + "   " + msg);
                    
                    //     System.out.println("Sending");
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } // Sæt vores tråd til null ved finally
            } catch (UnknownHostException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                sendMessage.interrupt();
            }
        }
            }
        });
        
        
        
        
        
        
        readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
        try {
            while(true){
            //    System.out.println("HEllo");
                String text =  (String) input.readObject(); 
                System.out.println(text);
            }
        } catch (Exception e) {
        } finally{
         // Sæt vores tråd til null
            try{
                readMessage.interrupt();
                output.close();
            } catch (IOException ex) {
                ex.printStackTrace ();
            }
        }
            }
        });
        
        
        sendMessage.start();
        readMessage.start();
        
    }
 
    // skal outcomments
          public static void main(String[] args) {
              Client client = new Client();
    }   
 
    
    
  
}