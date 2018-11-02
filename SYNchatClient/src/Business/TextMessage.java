/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author Sigurd E. Espersen
 */
public class TextMessage extends Message {
    
    String msg;
    
    public TextMessage(int senderID, String msg) {
        super(senderID);
        this.msg = msg;
    }
    
}
