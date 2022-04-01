package com.company.model.dao;

public class SQL {
    private SQL(){}

    public static final String INSERT_USER = "insert into " +
            "account(login,password,role_id,account_details_id,place_id,wallet_id) " +
            "values(?,?,(select id from role where role = ?),?,?,?);";
    public static final String INSERT_USER_DETAILS = "insert into "+
            "account_details(first_name_en,last_name_en,first_name_ua,last_name_ua,email,phone) " +
            "values(?,?,?,?,?,?);";
    public static final String SELECT_USER = "select * from account where login = ?;";
    public static final String SELECT_USER_DETAILS = "select * from account_details where id = ?;";

//    public static final String SELECT_WALLET = "select * from wallet where id = ?;";

    public static final String INSERT_TARIFF = "insert into "+
            "tariff(name_en,time,description,price,service_id,tariff_status_id) "+
            "values(?,?,?,?,?,?);";
    public static final String  SELECT_TARIFF_BY_ID = "select * from tariff where id = ?;";

    public static final String SELECT_TARIFFS = "select * from tariff;";

    public static final String INSERT_PLACE = "insert into " +
            "place(street,district,city) " + "values(?,?,?);";

    public static final String SELECT_ROLE = "select role from role where id = ?;";

    public static final String SELECT_PLACE = "select * from place where id = ?;";

    public static final String INSERT_SERVICE = "insert into service(service_en) " + "values(?);";

    public static final String INSERT_WALLET = "insert into " +
            "wallet(`funds`,`change`) " + "values(?,?);";

    public static final String ADD_CHANGE = "UPDATE wallet SET funds = ? WHERE id = ?;";

    public static final String INSERT_PAYMENT= "insert into payment(account_id,tariff_id,price,time) " +
            "values(?,?,?,?);";

    public static final String SELECT_WALLET = "select * from wallet where id = ?;";

    public static final String SELECT_TARIFF_STATUS = "select * from tariff_status where id = ?;";

    public static final String INSERT_TARIFF_TO_ORDER = "insert into " +
            "tariff_to_order(name_en,description,tariff_status_id,account_id) "
            + "values(?,?,?,(select id from tariff_status where status_en = 'available'));";

    public static final String SELECT_TARIFF_BY_PRICE = "select * from tariff where price = ?;";

    public static final String SELECT_USER_PAYMENT = "select * from payment where account_id = ?;";

    public static final String SELECT_USER_PAYMENTS_LIMIT = "select * from payment where account_id = ? limit ?, ?;";

    public static final String SELECT_AMOUNT_PAYMENTS = "select count(account_id) as count from payment where account_id = ?;";

    public static final String SELECT_USER_BY_ID = "select * from account where id = ?;";

    public static final String MIN_PRICE_TARIFF = "select min(price) from service;";

    public static final String MAX_PRICE_TARIFF = "select max(price) from service;";

    public static final String SELECT_TARIFF_LIMITS = "select * from payment where account_id = ? limit ?,?;";

    public static final String INSERT_TARIFF_STATUS = "insert into tariff_status(status_en) " + "values(?);";

    public static final String SELECT_TARIFF_BY_PLACE = "select * from tariff where place_id = ?;";

    public static final String SELECT_TARIFF_BY_SERVICE = "select * from tariff where service_id = (select id from service where service_en = ?);";

    public static final String SELECT_SERVICES = "select * from service";

    public static final String SET_TARIFF_STATUS = "update tariff set tariff_status_id = ? where id = ?;";

    public static final String SELECT_TARIFF_STATUSES = "select * from tariff_status;";

    public static final String SELECT_TARIFF_BY_STATUS = "select * from tariff where tariff_status_id = (select id from tariff_status where status_en = ?);";

    public static final String SELECT_SERVICE = "select * from service where id = ?;";
}
