package com.zys.taimeiknowledge.service;

import com.zys.taimeiknowledge.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TransactionalService {

    @Resource
    private UserDao userDao;
    @Resource
    private TransactionalService transactionalService;

    public void laoda(String firstName,String toName,Integer money){
        userDao.out(firstName,money);
        try {
            transactionalService.xiaodi(toName,money);
        }catch (Exception e){
            e.printStackTrace();
        }
        int x =10;
        if(x == 10){
            throw  new RuntimeException("出错了");
        }
    }

    public void xiaodi(String toName,Integer money){
        userDao.in(toName,money);
        int x =10;
        if(x == 10){
            throw  new RuntimeException("出错了");
        }
    }

}
