package com.company.model.entity;

import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.user.User;

public class Selection {
    private int userId;
    private int tariffId;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTariffId() {
        return tariffId;
    }
    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }


}
