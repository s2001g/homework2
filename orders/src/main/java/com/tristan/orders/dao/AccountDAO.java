package com.tristan.orders.dao;


import com.tristan.orders.po.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class AccountDAO {
    private static AccountMapper accountMapper;
    public AccountDAO() throws IOException {
        if(accountMapper == null) {
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            //2.创建SqlSessionFactory工厂
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            //3.使用工厂生产SqlSession对象
            SqlSession session = factory.openSession(true);
            //4.使用SqlSession创建Dao接口的代理对象
            accountMapper = session.getMapper(AccountMapper.class);
        }


    }

    public Account createAccount(Account account) throws IOException {
        int result = accountMapper.insert(account);
        if(result == 1){
            return account;
        }
        return null;
    }

    public Account selectAccountFromDB(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }
    public Account selectAccountFromRedis(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }
}
