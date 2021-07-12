package com.example.loginapplication;

public class Storage {
     String Username;
     String Password;
     String Email;
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    Storage(String username, String password, String email){
         this.Username = username;
         this.Password = password;
         this.Email = email;
     }

}
