/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import surucukursuotomasyonu.Anasayfa;
/**
 *
 * @author FurkanHakan
 */
public class kursiyerveritabani {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/surucukursuotomasyonu?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
    static final String USER = "root";
    static final String PASS = "6255"; 
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    
    public ArrayList sinifgetir(Object ehliyetsinifi) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM siniflar WHERE ehliyetsinifi = '" + ehliyetsinifi.toString()+"'";
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList siniflar;
            siniflar = new ArrayList ();
            while (rs.next()) {
                siniflar.add(rs.getString("sinifadi"));
            }
            return siniflar;
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }
    
    public ArrayList sinifbilgilerigetir(Object sinifadi,Object ehliyetsinifi){
        
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM siniflar WHERE sinifadi = '"+sinifadi.toString()+"' AND ehliyetsinifi = '"+ehliyetsinifi.toString()+"' ";
            ResultSet rs = stmt.executeQuery(sql);
            
            ArrayList siniflar;
            siniflar = new ArrayList();
            while (rs.next()) {
                siniflar.add(rs.getInt("sinifkapasitesi"));
                siniflar.add(rs.getInt("sinifmevcudu"));
            }
            return siniflar;
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }
    public void siniflariguncelle(String sinifadi,String isaret){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql="update siniflar SET sinifmevcudu = sinifmevcudu "+isaret+" 1 where sinifadi = '"+sinifadi+"'";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    public void ekle(String TC,String adayadi,String adaysoyadi,String aractip,String telefon,String saglikraporu,String ehliyetsinifi,String sinifadi){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql="INSERT INTO adaylar(tcno,adayadi,adaysoyadi,aractip,telefon,saglikraporu,ehliyetsinifi,sinifadi) VALUES(?,?,?,?,?,?,?,?);";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, TC);
            psmt.setString(2, adayadi);
            psmt.setString(3, adaysoyadi);
            psmt.setString(4, aractip);
            psmt.setString(5, telefon);
            psmt.setString(6, saglikraporu);
            psmt.setString(7, ehliyetsinifi);
            psmt.setString(8, sinifadi);
            psmt.execute();
            
            siniflariguncelle(sinifadi,"+");
            JOptionPane.showMessageDialog(null, "Kayıt Başarıyla Eklenmiştir","Başarılı",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            
        }
    }
    public ArrayList listele(){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM adaylar";
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList kursiyerler = new ArrayList();
            while (rs.next()) {                
                kursiyerler.add(rs.getInt("adayid"));
                kursiyerler.add(rs.getString("tcno"));
                kursiyerler.add(rs.getString("adayadi"));
                kursiyerler.add(rs.getString("adaysoyadi"));
                kursiyerler.add(rs.getString("aractip"));
                kursiyerler.add(rs.getString("telefon"));
                kursiyerler.add(rs.getString("saglikraporu"));
                kursiyerler.add(rs.getTimestamp("kayittarihi"));
                kursiyerler.add(rs.getString("ehliyetsinifi"));
                kursiyerler.add(rs.getString("sinifadi"));
            }
            return kursiyerler;
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }
    public void odemeekle(String tcno,String odemetipi,int toplamtaksit,int odenentaksit,String durum){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql="INSERT INTO odeme(tcno,odemetipi,toplamtaksit,odenentaksit,durum) VALUES(?,?,?,?,?);";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, tcno);
            psmt.setString(2, odemetipi);
            psmt.setInt(3, toplamtaksit);
            psmt.setInt(4, odenentaksit);
            psmt.setString(5, durum);
            psmt.execute();
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            
        }
    }
    public ArrayList odemedurumgetir(String tcno){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM odeme WHERE tcno = '"+tcno+"'";
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList odemedurum = new ArrayList();
            while (rs.next()) {                
                odemedurum.add(rs.getInt("toplamtaksit"));
                odemedurum.add(rs.getInt("odenentaksit"));
                odemedurum.add(rs.getString("durum"));
            }
            return odemedurum;
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }
    
    public void odemeguncelle(String tcno,int odenentaksit){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql="UPDATE odeme SET odenentaksit = odenentaksit + "+odenentaksit+" WHERE tcno = '"+tcno+"'";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, odenentaksit+" Taksit Ödendi.","Taksit Ödeme",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            
        }
    }
    public void odemedurumguncelle(String tcno){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql="UPDATE odeme SET durum = 'Odendi' WHERE tcno = '"+tcno+"'";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            
        }
    }
    public void kursiyerguncelle(String eskitc,String TC,String adayadi,String adaysoyadi,String aractip,String telefon,String saglikraporu,String ehliyetsinifi,String sinifadi,String eskisinifadi){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql="UPDATE adaylar SET tcno = ? ,adayadi = ? ,adaysoyadi = ?,aractip = ?,telefon = ?,saglikraporu = ?,ehliyetsinifi = ?,sinifadi = ? WHERE tcno = ?";
            psmt = conn.prepareStatement(sql);
            
            psmt.setString(1, TC);
            psmt.setString(2, adayadi);
            psmt.setString(3, adaysoyadi);
            psmt.setString(4, aractip);
            psmt.setString(5, telefon);
            psmt.setString(6, saglikraporu);
            psmt.setString(7, ehliyetsinifi);
            psmt.setString(8, sinifadi);
            psmt.setString(9, eskitc);
            psmt.executeUpdate();
            siniflariguncelle(sinifadi,"+");
            siniflariguncelle(eskisinifadi,"-");
            JOptionPane.showMessageDialog(null, "Kayıt Başarıyla Güncellenmiştir","Başarılı",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            
        }
    }
    public ArrayList tumsiniflarigetir(){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM siniflar";
            ResultSet rs = stmt.executeQuery(sql);
            
            ArrayList siniflar;
            siniflar = new ArrayList();
            while (rs.next()) {
                siniflar.add(rs.getInt("sinifid"));
                siniflar.add(rs.getString("ehliyetsinifi"));
                siniflar.add(rs.getString("sinifadi"));
                siniflar.add(rs.getInt("sinifkapasitesi"));
                siniflar.add(rs.getInt("sinifmevcudu"));
            }
            return siniflar;
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }
    public void sinifekle(String ehliyetsinifi,String sinifadi,int sinifkapasitesi,int sinifmevcudu){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO siniflar(ehliyetsinifi,sinifadi,sinifkapasitesi,sinifmevcudu) VALUES(?,?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, ehliyetsinifi);
            psmt.setString(2, sinifadi);
            psmt.setInt(3, sinifkapasitesi);
            psmt.setInt(4, sinifmevcudu);
            psmt.execute();
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    
    
    public void kursiyersil(int id,String sinifadi){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from adaylar where adayid = ?;";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            System.out.println(psmt);
            psmt.executeUpdate();
            siniflariguncelle(sinifadi,"-");
        } catch (Exception e) {
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    

    
    
}
