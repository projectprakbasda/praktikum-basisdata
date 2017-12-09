/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek_basda_praktikum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class login {

    private Connection koneksi;
    private PreparedStatement psmt;
    private ResultSet dataUser;
    private String query, userID, password, pesan, credential;

    public login() {
        KoneksiMysql connection = new KoneksiMysql();
        try {
            koneksi = connection.getKoneksi();
        } catch (SQLException e) {
        }
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setCredit(String credential) {
        this.credential = credential;
    }

    public String getCredit() {
        return credential;
    }

    public String cekLogin(String userID, String password, String credential) {
        query = "SELECT nama FROM user WHERE Username=? AND pass=md5(?) AND credential =?";
        try {
            psmt = koneksi.prepareStatement(query);
            psmt.setString(1, userID);
            psmt.setString(2, password);
            psmt.setString(3, credential);

            dataUser = psmt.executeQuery();

            if (dataUser.next()) {
                Session.setUserID(userID);
                Session.setNama(dataUser.getString("nama"));
                Session.setStatusLogin("Aktif");
                Session.setCredit(credential);

                query = "INSERT INTO log_login (username) VALUES (?)";

                try {
                    psmt = koneksi.prepareStatement(query);
                    psmt.setString(1, userID);
                    psmt.executeUpdate();
                    psmt.close();
                } catch (Exception e) {
                    pesan = "gagal Simpan Login";
                }
            } else {
                pesan = "Gagal Login";
            }
        } catch (Exception e) {
            pesan = "gagal login, querry error";
        }
        return pesan;
    }

    public void Logout(String userID) {
        query = "UPDATE log_login SET waktu_logout=CURRENT_TIMESTAMP() WHERE username=? ORDER BY id DESC LIMIT 1";
        try {
            psmt = koneksi.prepareStatement(query);
            psmt.setString(1, userID);
            psmt.executeUpdate();
            psmt.close();

            koneksi.close();

            Session.setUserID(null);
            Session.setNama(null);
            Session.setCredit(null);
            Session.setStatusLogin(null);
        } catch (Exception e) {
        }

    }
}
