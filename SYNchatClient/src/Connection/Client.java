/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    private DataInputStream console;
    private DataInputStream input;
    private DataOutputStream output;
    private Thread listener;

    public Client() {
        try {
            this.ip = (InetAddress) InetAddress.getByName("10.126.33.99");
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.port = port;
        
        connectToServer();
        
        startPublicThreads();
        
        startPrivateThreads();
        
        
        
        
       // listener = new Thread(this);
       // listener.start();
        

    }

    public void connectToServer() {
        try {
            System.out.println("Connecting to " + ip + " on port " + port + "");
            this.serverSocket = new Socket(ip,port);
            console = new DataInputStream(System.in);
            input = new DataInputStream(new BufferedInputStream(serverSocket.getInputStream()));
            output = new DataOutputStream(new BufferedOutputStream(serverSocket.getOutputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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
                String text = input.readUTF(); 
                System.out.println(text);
            }
        } catch (Exception e) {
        } finally{
            listener = null;  // Sæt vores tråd til null
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
        
     
        
        Thread sendMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){ 
            try { 
                InetAddress local = InetAddress.getLocalHost();
                String msg = scan.nextLine();
                try {
                    
                    output.writeUTF(local + "   " + msg);
                    
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
                String text = input.readUTF(); 
                System.out.println(text);
            }
        } catch (Exception e) {
        } finally{
            listener = null;  // Sæt vores tråd til null
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

    /*
    public void chat() {
        String str;
        Scanner inputStream = null;
        PrintWriter outputStream = null;

        try (Scanner keyboard = new Scanner(System.in)) {
            inputStream = new Scanner(serverSocket.getInputStream());
            outputStream = new PrintWriter(serverSocket.getOutputStream(), true);

        while(!keyboard.nextLine().equalsIgnoreCase("Exit")) {
           if (inputStream.hasNextLine()) {
                System.out.println(inputStream.nextLine());
            }

            // System.out.println(keyboard.nextLine());
            outputStream.println(keyboard.nextLine());

            str = inputStream.nextLine();
            System.out.println(str); 
        }    
            

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
*/
  


   
          public static void main(String[] args) {
        
              
              
              Client client = new Client();
       
        
        
        
     //   client.chat();
 
    }   
 
    
    
  
}