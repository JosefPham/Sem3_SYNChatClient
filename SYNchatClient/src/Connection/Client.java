/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Pottemuld
 */
public class Client {

    Socket serverSocket;
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

    public void connectToServer() {
        try {
            System.out.println("Connecting to " + ip + " on port " + port + "");

            this.serverSocket = new Socket(ip,port);
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

    public static void main(String[] args) {
        Client client = new Client();

        client.connectToServer();
        client.chat();
 
    }
}