package com.company.model.entity.payment;

import com.company.model.entity.service.Service;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.tariff.TariffStatus;
import com.company.model.entity.user.User;
import com.company.model.entity.wallet.Wallet;

import java.math.BigDecimal;

public class Payment {
    private int userId;/////////////////////////////////////////
    private int tariffId;///////////////////////////////////////
    private BigDecimal price;
    private int time;

    private String firstnameEn;
    private String lastnameEn;

    private String tariffName;
    private String description;
    private String service;

    public int getUserId() {return userId;}
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTariffId() {
        return tariffId;
    }
    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public String getFirstnameEn() {return firstnameEn;}
    public void setFirstnameEn(String firstnameEn) {this.firstnameEn = firstnameEn;}

    public String getLastnameEn() {return lastnameEn;}
    public void setLastnameEn(String lastnameEn) {this.lastnameEn = lastnameEn;}

    public String getTariffName() {return tariffName;}
    public void setTariffName(String tariffName) {this.tariffName = tariffName;}

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getService() {return service;}
    public void setService(String service) {this.service = service;}
}
