package com.bapp.subscriptionbuddy;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mustafa
 */
public class User {
    private String username;
    private String password;
    private int credits;
    
    User(String username , String password, int credits) {
        this.username = username;
        this.password = password;
        this.credits = credits;
    }
    
    public int getCredits() {
        return this.credits;
    }
    
    public void setCredits(int amount) {
        this.credits = amount;
    }
    
    public String getUsername(){
        return this.username;
    }
   
}
