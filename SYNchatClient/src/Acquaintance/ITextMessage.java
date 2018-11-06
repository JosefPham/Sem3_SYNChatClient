/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

import java.util.Date;

/**
 *
 * @author Sigurd E. Espersen
 */
public interface ITextMessage {

    int getSenderID();

    Date getTimestamp();
    
    String getContext();
}
