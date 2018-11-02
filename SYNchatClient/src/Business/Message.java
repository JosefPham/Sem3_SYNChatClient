/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Sigurd E. Espersen
 */
public abstract class Message implements Serializable {

    int senderID;
    Date timestamp;

    public Message(int senderID) {
        this.senderID = senderID;
        timestamp = new Date();
    }

    public int getSenderID() {
        return senderID;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    
    
}
