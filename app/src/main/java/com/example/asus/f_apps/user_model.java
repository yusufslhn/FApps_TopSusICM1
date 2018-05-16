package com.example.asus.f_apps;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;


/**
 * Created by ASUS on 16/05/2018.
 */

public class user_model implements Serializable{
    private String username;
    private String email;
    private String password;
    private String repassword;

    public user_model(String username, String email, String password, String repassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }

    public user_model(String s, String s1, String s2) {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return " "+username+"\n" +
                " "+email;
    }
}


