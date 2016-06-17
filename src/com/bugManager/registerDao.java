package com.bugManager;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.UUID;

/**
 * Created by soorya on 31-May-16.
 */
public class registerDao {
    public static int save(registerAction r){
        int status = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String uniqueID = UUID.randomUUID().toString();

            String sql = "SELECT email FROM users WHERE email LIKE ?";
            PreparedStatement checkUser = c.prepareStatement(sql);  //to check if user exists
            checkUser.setString(1, r.getEmail());
            ResultSet rs = checkUser.executeQuery();
            if(rs.next()){
                return 2;
            }
            PreparedStatement ps =  c.prepareStatement("INSERT into users(id, firstName, lastName, username, password, email) VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, uniqueID);
            ps.setString(2, r.getFirstName());
            ps.setString(3, r.getLastName());
            ps.setString(4, r.getUsername());
            String password = r.getUsername() + r.getPassword();

            //hashing start
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(password.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            password = hashtext;
            //hashing ending

            ps.setString(5, password);
            ps.setString(6, r.getEmail());
            status = ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("sqlException found");
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            System.out.println("classnotFoundException found");
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return status;
    }

}
