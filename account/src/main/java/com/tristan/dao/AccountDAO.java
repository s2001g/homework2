package com.tristan.dao;

import com.tristan.po.Account;
import io.seata.core.context.RootContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class AccountDAO  {
    private static AccountMapper accountMapper;
    public AccountDAO() throws IOException {
        if(accountMapper == null) {
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            //2.创建SqlSessionFactory工厂
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            //3.使用工厂生产SqlSession对象
            SqlSession session = factory.openSession(false);
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

    public Account selectAccount(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    public void updateAccount(Account account) throws SQLException {
        System.out.println("updateDAOSeata全局事务id=================>{}"+ RootContext.getXID());
        int i = accountMapper.updateByPrimaryKey(account);
        if(i == 0) throw new SQLException("NO DATA UPDATED");
    }
}
