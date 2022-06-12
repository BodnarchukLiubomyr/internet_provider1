package com.company.model.entity.suggestion;

import com.company.model.entity.service.Service;
import com.company.model.entity.tariff.TariffStatus;

import java.math.BigDecimal;

public class UserSuggestion {
    private int id;
    private String name_en;
    private int time;
    private BigDecimal price;
    private String description;
    private Service service;

    public UserSuggestion() {
        price = new BigDecimal(0);
        service = new Service();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }
    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Service getService() {
        return service;
    }
    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "UserSuggestion{" +
                "name_en='" + name_en + '\'' +
                '}';
    }
}
