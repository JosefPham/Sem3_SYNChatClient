/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Acquaintance.IBusiness;
import Acquaintance.IConnection;
import Acquaintance.ILogin;
import Acquaintance.IPresentation;
import Connection.ConnectionFacade;

/**
 *
 * @author Sigurd E. Espersen
 */
public class BusinessFacade implements IBusiness {

    private IConnection Icon;
    private IPresentation Ipres;

    private static BusinessFacade instance = null;

    private BusinessFacade() {
    }

    public static BusinessFacade getInstance() {
        if (instance == null) {
            instance = new BusinessFacade();
        }
        return instance;
    }

    @Override
    public void injectPresentation(IPresentation pres) {
        this.Ipres = pres;
    }

    @Override
    public void injectConnection(IConnection con) {
        this.Icon = con;
    }

    protected ILogin login(ILogin ilogin) {
        return Icon.login(ilogin);
    }

    @Override
    public int login(String mail, String pw) {
        return ClientSystem.getInstance().Login(mail, pw);
    }

    @Override
    public Boolean regUser(String tmpName, String mail, String pw) {
        return ClientSystem.getInstance().regUser(tmpName, mail, pw);
    }

    protected Boolean regBool(ILogin ilogin) {
        return Icon.regBool(ilogin);
    }

    @Override
    public void publicThreads() {
        Icon.startPublicThreads();
    }

    @Override
    public void privateThreads() {
        Icon.startPrivateThreads();
    }

    @Override
    public void recievePublicMsg(String s) {
        System.out.println("businessfacade");
        Ipres.recievePublicMsg(s);
    }

    @Override
    public void sendPublicMsg(String s) {
        Icon.sendPublicMsg(s);
    }

}
