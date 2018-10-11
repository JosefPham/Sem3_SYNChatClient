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

    SSLSocket serverSocket;
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
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            this.serverSocket = (SSLSocket) factory.createSocket("10.126.37.220", port);
            serverSocket.startHandshake();
            System.out.println("Handshake completed");
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

            
            if (inputStream.hasNextLine()) {
                System.out.println(inputStream.nextLine());
            }

            // System.out.println(keyboard.nextLine());
            outputStream.println(keyboard.nextLine());

            str = inputStream.nextLine();
            System.out.println(str);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

//    public static void main(String[] args) {
//        Client client = new Client();
//
//        client.connectToServer();
//        client.chat();
 public static void main(String args[])
    {
        try
        {
        //Mo 1 client socket den server voi so cong va dia chi xac dinh
        SSLSocketFactory factory=(SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslsocket=(SSLSocket) factory.createSocket("10.126.37.220",8080);

        //Tao luong nhan va gui du lieu len server
        DataOutputStream os=new DataOutputStream(sslsocket.getOutputStream());
        DataInputStream is=new DataInputStream(sslsocket.getInputStream());

        //Gui du lieu len server
        String str="helloworld";
        os.writeBytes(str);

        //Nhan du lieu da qua xu li tu server ve
        String responseStr;
        if((responseStr=is.readUTF())!=null)
        {
            System.out.println(responseStr);
        }

        os.close();
        is.close();
        sslsocket.close();
        }
        catch(UnknownHostException e)
        {
             e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    }


