/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pottemuld
 */
public class Client implements Runnable{

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
        listener = new Thread(this);
        listener.start();
        

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

    @Override
    public void run() {
        try {
            while(true){
                output.writeUTF(console.readLine());
                output.flush();
                String text = input.readUTF(); // has to wait for output.writeUTF
                System.out.println(text);
            }
        } catch (Exception e) {
        } finally{
            listener = null;
            try{
                output.close();
            } catch (IOException ex) {
                ex.printStackTrace ();
            }
        }
    }
}