package com.company.model.entity.wallet;

import com.company.model.entity.user.User;

import java.math.BigDecimal;

public class Wallet {
    private int id;
    private BigDecimal funds;
    private BigDecimal change;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getFunds() {
        return funds;
    }
    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public BigDecimal getChange() {return change;}
    public void setChange(BigDecimal change) {this.change = change;}
}
