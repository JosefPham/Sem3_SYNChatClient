/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peter
 */
public class SendMessage implements Runnable{

    private DataOutputStream output;
    private DataInputStream console;
    
      public SendMessage(DataOutputStream output, DataInputStream console) {
        this.output = output;
        this.console = console;
    }
      
      
    @Override
    public void run() {
        while(true){
            try {
                output.writeUTF(console.readLine());
                output.flush();
            } catch (IOException ex) {
                Logger.getLogger(SendMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

  
    
    
}
