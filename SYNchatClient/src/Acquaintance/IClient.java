package Acquaintance;

/**
 *
 * @author Group 9
 */
public interface IClient {

    /**
     * Method for communicating with server
     * @param Object 
     */
    void send(Object o);

    ILogin receiveLogin();

    Boolean receiveBool();

    int receiveInt();

    /**
     * Starts a thread to run public chat
     */
    void startPublicThreads();

    void startPrivateThreads();

    void connectToServer();
}
