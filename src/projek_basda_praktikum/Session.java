/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek_basda_praktikum;

/**
 *
 * @author User
 */
public class Session {

    private static String UserID, Nama, StatusLogin, credential;

    public static void setUserID(String UserID) {
        Session.UserID = UserID;
    }

    public static String getUserID() {
        return UserID;
    }

    public static void setNama(String Nama) {
        Session.Nama = Nama;
    }

    public static String getNama() {
        return Nama;
    }

    public static void setStatusLogin(String StatusLogin) {
        Session.StatusLogin = StatusLogin;
    }

    public static String getStatusLogin() {
        return StatusLogin;
    }
    
    public static void setCredit(String credential){
        Session.credential=credential;
    }
    
    public String getCredit(){
        return credential;
    }
    
}
