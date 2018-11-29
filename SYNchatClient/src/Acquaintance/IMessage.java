package Acquaintance;

import java.time.Instant;

/**
 *
 * @author Group 9
 */
public interface IMessage {

    int getSenderID();

    Instant getTimestamp();

    String getContext();
}
