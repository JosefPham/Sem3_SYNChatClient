package Starter;

import Acquaintance.IBusiness;
import Acquaintance.IConnection;
import Acquaintance.IPresentation;
import Business.BusinessFacade;
import Connection.ConnectionFacade;
import Presentation.PresentationFacade;

/**
 *
 * @author Group 9
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
        business.injectPresentation(presentation);
        connection.injectBusiness(business);

        //Calls PresentationFacade with FXML startup command
        presentation.startApplication(args);
    }

}
