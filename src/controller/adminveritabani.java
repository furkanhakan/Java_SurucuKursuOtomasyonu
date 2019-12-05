/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;

/**
 *
 * @author FurkanHakan
 */
public class adminveritabani {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/surucukursuotomasyonu?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
    static final String USER = "root";
    static final String PASS = "6255"; 
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    
    public boolean giris(String username, String password){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM admin";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
                    return true;
                }
            }
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
        return false;
    }
    
    public void ekle(int id,String ad,String parola){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql="INSERT INTO admin VALUES(?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            psmt.setString(2, ad);
            psmt.setString(3, parola);
            psmt.execute();
            System.out.println("Başarılı");
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
    public void listele(){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM admin";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id")+" - "+rs.getString("username")+" - "+rs.getString("password"));
            }
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
    public void sil(int id){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "DELETE FROM admin VALUES id = "+id;
            System.out.println(stmt.executeUpdate(sql));
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
