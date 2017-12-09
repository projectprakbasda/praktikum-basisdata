/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek_basda_praktikum;

/**
 *
 * @author ASUS
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDuser {
    private String username, nama, alamat, telepon, password, credential, gender; 
    private Connection CRUDkoneksi;
    private PreparedStatement CRUDpsmt;
    private Statement CRUDstat;
    private ResultSet CRUDhasil;
    private String CRUDquery;

    public CRUDuser() {
        try {
            KoneksiMysql connection = new KoneksiMysql();
            CRUDkoneksi = connection.getKoneksi();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
   
    public void save(String username, String nama, String password, String alamat, String telepon, String gender, String credential) {
        CRUDquery = "insert into user values (?, ?, ?, ?, ?, ?, ?)";
        System.out.println(CRUDquery);
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, username);
            CRUDpsmt.setString(2, nama);
            CRUDpsmt.setString(3, telepon);
            CRUDpsmt.setString(4, gender);
            CRUDpsmt.setString(5, alamat);
            CRUDpsmt.setString(6, credential);
            CRUDpsmt.setString(7, password);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ubahData(String username, String nama, String password, String alamat, String telepon, String gender, String credential) {
        CRUDquery = "update user set username=?, nama=?, password=?, telepon=?, alamat=? where username=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, username);
            CRUDpsmt.setString(2, nama);
            CRUDpsmt.setString(3, password);
            CRUDpsmt.setString(4, telepon);
            CRUDpsmt.setString(5, alamat);
            CRUDpsmt.setString(6, credential);
            CRUDpsmt.setString(7, gender);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void hapusData(int id_barang) {
        CRUDquery = "delete from user where username=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, username);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public ResultSet cariData(String key) {
        CRUDquery = "select * from user where username like '%"+key+"%' or nama like '%"+key+"%'";
        try {
            CRUDstat = CRUDkoneksi.createStatement();
            CRUDhasil = CRUDstat.executeQuery(CRUDquery);
        } catch (Exception e) {

        }
        return CRUDhasil;
    }
}
