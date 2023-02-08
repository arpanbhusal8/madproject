package com.example.mad_project_actual;

public class helper {

    String email, password, phn;

    public helper(String email, String password, String phn) {
        this.email = email;
        this.password = password;
        this.phn = phn;
    }

    public helper() {
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
}




