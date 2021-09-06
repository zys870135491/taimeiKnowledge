package com.zys.taimeiknowledge.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    void out(String firstName,Integer money);

    void in(String toName,Integer money);

}
