/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardlayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jarno
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class AccountsConnection {

    private Connection c;
    private Statement s;
    private ResultSet r;
    private String q;
    
    private int accountid;
    private int cardnumber;
    private int updateRes;

    public AccountsConnection(String url, String user, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public ResultSet queryDatabase(String query) {
        try {
            s = c.createStatement();
            r = s.executeQuery(query);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return r;
    }

    public int getAccountNumber() {
        try {
            r.first();            
            return r.getInt("accountid");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public String getOwnerId() {
        try {
            r.first();
            String x = Integer.toString(r.getInt("ownerid"));
            return x;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public int getPin() {
        try {
            r.first();
            return r.getInt("pin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getCredit() {
        try {
            r.first();
            return r.getInt("credit");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean getActive() {
        try {
            r.first();
            int x = r.getInt("active");
            if (x == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getCardNumber(String x) {
        try {
            r.first();
            return r.getInt("cardnumber");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int updateTable(String update) {
        try {
            updateRes = s.executeUpdate(update);
            System.out.println("\n "+updateRes);
            return updateRes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
}
