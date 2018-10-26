package Acquaintance;

/**
 *
 * @author Group 9
 */
public interface IClient {

    void send(Object o);

    ILogin receiveLogin();

    Boolean receiveBool();

    void startPublicThreads();

    void startPrivateThreads();
}
