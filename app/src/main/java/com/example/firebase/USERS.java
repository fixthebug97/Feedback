package com.example.firebase;

import java.io.Serializable;

public class USERS implements Serializable {
  private String email;
    private String username;
    private String password;
    private String description;
    private String fullname;
    private String imageUrl;



    public USERS(){


    }

    public USERS(String email, String username, String password, String description,String fullname,String imageUrl) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.description = description;
        this.fullname=fullname;
        this.imageUrl=imageUrl;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }
}

