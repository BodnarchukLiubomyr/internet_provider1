package com.company.model.entity.user;

import com.company.model.entity.place.Place;
import com.company.model.entity.wallet.Wallet;

public class User {
    private int id;
    private String login;
    private String password;
    private Role role;
    private UserDetails details;
    private Place place;
    private Wallet wallet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }

    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }

    public Wallet getWallet() {return wallet;}
    public void setWallet(Wallet wallet) {this.wallet = wallet;}
}
