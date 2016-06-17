package com.bugManager;

import com.opensymphony.xwork2.ActionSupport;

import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.security.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;

public class registerAction extends ActionSupport {
    private String firstName, lastName, username, password, email;
    private String repeatPassword;

    public String execute() throws Exception {
        int status = registerDao.save(this);
        System.out.println("registerAction: " + status);
        if(status == 1)
            return "success";
        else if(status == 2)
            return "userExists";
        else
            return "error";

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void validate(){

        if(firstName == null || firstName.trim().equals("") || firstName.isEmpty()) {
            System.out.println("firstname error");
            addFieldError("firstName", "Please enter your first name");
        }

        if(lastName == null || lastName.trim().equals(""))
            addFieldError("lastName", "Please enter your last name");

        if(username == null || username.trim().equals(""))
            addFieldError("username", "Please enter a username");

        if(password.trim().equals("") || password == null)
            addFieldError("password", "Please enter a password");

        else if(password.length() < 8)
            addFieldError("password", "Password should be more than or equal to 8 characters");

        if(email == null || email.trim().equals(""))
            addFieldError("email", "Please enter your email");
    }
}
