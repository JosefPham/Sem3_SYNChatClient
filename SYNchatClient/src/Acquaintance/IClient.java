package Acquaintance;

/**
 *
 * @author Group 9
 */
public interface IClient {

    void send(Object o);

    ILogin recieveLogin();

    Boolean recieveBool();

    void startPublicThreads();

    void startPrivateThreads();
}
