package com.nonobank.ui.activity.Utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by edison on 16/7/5.
 */
public class SQLUtils {

    private String CONNECTION_URL;
    private String user;
    private String password;
    private java.sql.Statement stmt;
    private java.sql.Connection conn;
    public String userId;
    public String FileAuditId;
    public String id;

    public SQLUtils(String conn_url, String user, String password) {
        this.CONNECTION_URL = conn_url;
        this.user = user;
        this.password = password;
    }


    public void SelectINuserInfo(String SQL) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(CONNECTION_URL, user, password);
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {

            stmt = conn.createStatement();
//            SQL = "select id from user_info where mobile_num='13918304603'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                userId = rs.getString(1);
                System.out.println("成功"+ userId);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


    public void SelectINBorrrow(String SQL) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(CONNECTION_URL, user, password);
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {

            stmt = conn.createStatement();
//            SQL = "select id from user_info where mobile_num='13918304603'";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                id = rs.getString(1);
                System.out.println("成功"+ id);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateInborrowsArchive(String SQL) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(CONNECTION_URL, user, password);
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            stmt = conn.createStatement();
            System.out.println("userId: "+ userId);
//            String sql = "update borrows_archive set is_audit='1' where user_id="+userId;
//            System.out.println("sql: "+sql);
            stmt.executeUpdate(SQL);
            stmt.close();
            conn.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateInborrows(String SQL) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(CONNECTION_URL, user, password);
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
            conn.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


    public void DeleteInborrows(String SQL) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(CONNECTION_URL, user, password);
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            stmt = conn.createStatement();
            stmt.execute(SQL);
            stmt.close();
            conn.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertInAdminWorkRemark(String SQL) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(CONNECTION_URL, user, password);
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            stmt = conn.createStatement();
            stmt.execute(SQL);
            stmt.close();
            conn.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertInUserFileAuditInfo(String SQL) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(CONNECTION_URL, user, password);
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            stmt = conn.createStatement();
            stmt.execute(SQL);
            stmt.close();
            conn.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertInUserFileInfo(String SQL) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(CONNECTION_URL, user, password);
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            stmt = conn.createStatement();
            stmt.execute(SQL);
            stmt.close();
            conn.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


    public void SelectINuserFileAuditInfo(String SQL) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(CONNECTION_URL, user, password);
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                FileAuditId = rs.getString(1);
                System.out.println("成功"+ userId);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteInUserInfo(String SQL) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(CONNECTION_URL, user, password);
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            stmt = conn.createStatement();
            stmt.execute(SQL);
            stmt.close();
            conn.close();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
}

