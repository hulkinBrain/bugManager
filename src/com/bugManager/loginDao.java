package com.bugManager;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.Servlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Map;

/**
 * Created by soorya on 31-May-16.
 */
public class loginDao{


    static int authenticate(loginAction la) {
        int status = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");

            String passwordHash;

            //hashing start
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            String passwordPreHash = la.getUsername() + la.getPassword();
            m.update(passwordPreHash.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            passwordHash = hashtext;
            //hashing ending
            PreparedStatement ps = c.prepareStatement("SELECT id, password FROM users WHERE password LIKE ?");
            ps.setString(1, passwordHash);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String id = rs.getString("id");
                la.setUserId(id);
                status = 1;
            }
            c.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception found");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound Exception Found");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return status;
    }
}
