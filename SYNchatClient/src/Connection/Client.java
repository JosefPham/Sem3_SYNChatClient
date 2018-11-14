package Connection;

import Acquaintance.IClient;
import Acquaintance.ILogin;
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
 * @author Group 9
 */
public class Client implements IClient {

    Socket serverSocket;
    InetAddress ip;
    int port = 8080;
    private DataInputStream console; // takes input from keyboard (system in)
    private ObjectInputStream input;  // takes the stream from the server socket - incoming messages
    private ObjectOutputStream output; // outgoing messages - taken from console
    Thread sendMessage, readMessage = null;

    public Client() {
        try {
            this.ip = (InetAddress) InetAddress.getByName("10.126.33.137");

        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.port = port;

        connectToServer();

        //outcomment
        //ILogin login = new Login("test@test12.dk", "12345678");
        //IUser user = new User("Peet");
        //login.setUser(user);
        //send(login);
        //    send(user);
        // receiveLogin();
//         receiveBool();
//        startPublicThreads();
        //   startPrivateThreads();
    }

    public void connectToServer() {
        try {
            System.out.println("Connecting to " + ip + " on port " + port + "");
            this.serverSocket = new Socket(ip, port);
            console = new DataInputStream(System.in);
            output = new ObjectOutputStream(serverSocket.getOutputStream());
            input = new ObjectInputStream(serverSocket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void send(Object o) {
        try {
            output.writeObject(o);
            // System.out.println("Sent login info");

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ILogin receiveLogin() {
        while (true) {
            try {
                ILogin receivedLogin = (ILogin) input.readObject();
                System.out.println("Vi læste noget o.o");
                if (receivedLogin != null) {
                    System.out.println("Fik login som ikke var null!");
                    return receivedLogin;
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Boolean receiveBool() {
        while (true) {
            try {
                Boolean receivedBool = (Boolean) input.readObject();
                System.out.println("Vi læste noget o.o");
                if (receivedBool != null) {
                    System.out.println("Fik en boolean!");
                    System.out.println(receivedBool);
                    return receivedBool;
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public int receiveInt() {
        while (true) {
            try {
                int receivedInt = (int) input.readObject();
                System.out.println("Vi læste noget o.o");
                if (receivedInt >= 0) {
                    System.out.println("Fik en int!");
                    System.out.println(receivedInt);
                    return receivedInt;
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void startPrivateThreads() {

        Scanner scan = new Scanner(System.in);

        Thread sendMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
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
                    while (true) {
                        //    System.out.println("HEllo");
                        String text = (String) input.readObject(); // writeObject
                        System.out.println(text);
                    }
                } catch (Exception e) {
                } finally {
                    // Sæt vores tråd til null
                    try {
                        output.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        sendMessage.start();
        readMessage.start();
    }

    @Override
    public void startPublicThreads() {
        if(readMessage == null){
        readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (serverSocket.isConnected()) {
                        //String text = (String) input.readObject();
                        ConTextMessage msg = (ConTextMessage) input.readObject();
                        ConnectionFacade.getInstance().receivePublicMsg(msg);
                        System.out.println(msg.getContext());
                    }
                } catch (Exception e) {
                } finally {
                    // Sæt vores tråd til null
                    try {
                        readMessage.interrupt();
                        output.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        //sendMessage.start();
        readMessage.start();
        }
    }

//    // skal outcomments
//    public static void main(String[] args) {
//        Client client = new Client();
//    }

}
