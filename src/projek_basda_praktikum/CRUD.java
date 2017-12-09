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

public class CRUD {
    private String nama, harga; 
    private int id_barang, stock;
    private Connection CRUDkoneksi;
    private PreparedStatement CRUDpsmt;
    private Statement CRUDstat;
    private ResultSet CRUDhasil;
    private String CRUDquery;

    public CRUD() {
        try {
            KoneksiMysql connection = new KoneksiMysql();
            CRUDkoneksi = connection.getKoneksi();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    
    public void setID(int id_barang) {
        this.id_barang = id_barang;
    }

    public int getID() {
        return id_barang;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getHarga (){
        return harga;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public ResultSet tampilData() {
        CRUDquery = "select * from barang";
        try {
            CRUDstat = CRUDkoneksi.createStatement();
            CRUDhasil = CRUDstat.executeQuery(CRUDquery);
        } catch (Exception e) {

        }
        return CRUDhasil;
    }

    public void simpanData(int id_barang, String nama,  String harga, int stock) {
        CRUDquery = "insert into barang values (?, ?, ?, ?)";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, id_barang);
            CRUDpsmt.setString(2, nama);
            CRUDpsmt.setString(3, harga);
            CRUDpsmt.setInt(4, stock);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ubahData(int id_barang, String nama,  String harga, int stock) {
        CRUDquery = "update barang set nama=?, harga=?, stock=? where id_barang=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setString(1, nama);
            CRUDpsmt.setString(2, harga);
            CRUDpsmt.setInt(3, stock);
            CRUDpsmt.setInt(4, id_barang);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void hapusData(int id_barang) {
        CRUDquery = "delete from barang where id_barang=?";
        try {
            CRUDpsmt = CRUDkoneksi.prepareStatement(CRUDquery);
            CRUDpsmt.setInt(1, id_barang);
            CRUDpsmt.executeUpdate();
            CRUDpsmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public ResultSet cariData(String key) {
        CRUDquery = "select * from barang where id_barang like '%"+key+"%' or nama like '%"+key+"%'";
        try {
            CRUDstat = CRUDkoneksi.createStatement();
            CRUDhasil = CRUDstat.executeQuery(CRUDquery);
        } catch (Exception e) {

        }
        return CRUDhasil;
    }
}
