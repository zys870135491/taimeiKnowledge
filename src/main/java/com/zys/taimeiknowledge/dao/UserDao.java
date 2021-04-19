package com.zys.taimeiknowledge.dao;

public interface UserDao {

    void out(String firstName,Integer money);

    void in(String toName,Integer money);

}
