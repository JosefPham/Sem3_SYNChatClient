package Acquaintance;

/**
 *
 * @author Group 9
 */
public interface IClient {

    void send(Object o);

    ILogin receiveLogin();

    Boolean receiveBool();

    int receiveInt();

    void startPublicThreads();

    void startPrivateThreads();

    public void connectToServer();
}
