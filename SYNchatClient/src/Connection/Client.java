package Connection;

import Acquaintance.IClient;
import Acquaintance.ILogin;
import Acquaintance.IUser;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Group 9
 */
public class Client implements IClient {

    private Socket serverSocket;
    private InetAddress ip;
    int port = 8080;
    private DataInputStream console; // takes input from keyboard (system in)
    private ObjectInputStream input;  // takes the stream from the server socket - incoming messages
    private ObjectOutputStream output; // outgoing messages - taken from console
    private Thread sendMessage, readMessage = null;
    private boolean isPublicChatting = false;

    public Client() {
        try {
            this.ip = (InetAddress) InetAddress.getByName("10.123.3.31");
        } catch (UnknownHostException ex) {
            System.out.println("Couldn't connect to ip...");
        }
        this.port = port;

        connectToServer();
    }

    @Override
    public void connectToServer() {
        try {
            System.out.println("Connecting to " + ip + " on port " + port + "");
            this.serverSocket = new Socket(ip, port);
            console = new DataInputStream(System.in);
            output = new ObjectOutputStream(serverSocket.getOutputStream());
            input = new ObjectInputStream(serverSocket.getInputStream());

        } catch (IOException ex) {
            System.out.println("Server closed...");
        }

    }

    @Override
    public void send(Object o) {
        if (o.toString().equals("!SYN!-PublicChat-!SYN!")) {
            isPublicChatting = !isPublicChatting;
        }
        try {
            output.writeObject(o);

        } catch (IOException ex) {
            System.out.println("Couldn't send object...");
        }
    }

    @Override
    public ILogin receiveLogin() {
        while (true) {
            try {
                ILogin receivedLogin = (ILogin) input.readObject();
                if (receivedLogin != null) {
                    return receivedLogin;
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public Boolean receiveBool() {
        while (true) {
            try {
                Boolean receivedBool = (Boolean) input.readObject();
                if (receivedBool != null) {
                    return receivedBool;
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public int receiveInt() {
        while (true) {
            try {
                int receivedInt = (int) input.readObject();
                if (receivedInt >= 0) {
                    return receivedInt;
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
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
                        String text = (String) input.readObject(); // writeObject
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
        if (readMessage == null) {
            readMessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (isPublicChatting) {
                            Object obj;
                            if ((obj = input.readObject()) != null) {
                                if (obj instanceof ConTextMessage) {
                                    ConTextMessage msg = (ConTextMessage) obj;
                                    ConnectionFacade.getInstance().receivePublicMsg(msg);
                                }
                                if (obj instanceof Map) {
                                    ConnectionFacade.getInstance().userMap((Map) obj);
                                }
                                if (obj instanceof IUser) {
                                    ConnectionFacade.getInstance().publicUser((IUser) obj);
                                }
                            }

                        }
                        ConnectionFacade.getInstance().userMap(new HashMap<>());
                    } catch (Exception e) {
                        System.out.println("Got kicked off public thread...");
                    } finally {
                        try {
                            readMessage.interrupt();
                            readMessage = null;
                            output.close();
                        } catch (IOException ex) {
                            System.out.println("Logged off public thread...");
                        }
                    }
                }
            });
            readMessage.start();
        }
    }
}
