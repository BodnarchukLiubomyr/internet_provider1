package com.company.model.testDao;

import com.company.model.dao.UserDAO;
import com.company.model.entity.place.Place;
import com.company.model.entity.user.Role;
import com.company.model.entity.user.User;
import com.company.model.entity.user.UserDetails;
import com.company.model.entity.wallet.Wallet;

import java.math.BigDecimal;
import java.sql.SQLException;

public class testUser {
    public static void main(String[] args) throws SQLException {
        User user = new User();
        UserDetails details = new UserDetails();
        Place place = new Place();
        Wallet wallet = new Wallet();
        user.setId(5);
        user.setLogin("liubomur");
        user.setPassword("admin");
        user.setRole(Role.ADMIN);
        user.setDetails(details);
        user.setPlace(place);
        user.setWallet(wallet);
        details.setFirstnameEn("tom");
        details.setLastnameEn("rob");
        details.setFirstnameUa("ddgdfgddfg");
        details.setLastnameUa("dcsdfdfsdf");
        details.setEmail("liubomyr@gmail.com");
        details.setPhone("9809857734");
        place.setStreet("dfsdfd");
        place.setDistrict("ddsfsdf");
        place.setCity("fsdfsdfdf");
        wallet.setFunds(new BigDecimal (20));
        wallet.setChange(new BigDecimal(0));
        UserDAO dao = new UserDAO();
        dao.insertUser(user);
//        dao.getUserDetails(5);
//        dao.selectPlace(5);
//        System.out.println(dao.getUser("liubomur").getPassword());
    }
}
