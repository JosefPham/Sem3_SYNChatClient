/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Starter;

import Acquaintance.IBusiness;
import Acquaintance.IConnection;
import Acquaintance.IPresentation;
import Business.BusinessFacade;
import Connection.ConnectionFacade;
import Presentation.PresentationFacade;

/**
 *
 * @author Sigurd E. Espersen
 */
public class SYNchatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        IBusiness business = BusinessFacade.getInstance();
        IConnection connection = ConnectionFacade.getInstance();
        IPresentation presentation = PresentationFacade.getInstance();

        presentation.injectBusiness(business);
        business.injectConnection(connection);
        
        //Calls PresentationFacade with FXML startup command
        presentation.startApplication(args);
    }
    
}
