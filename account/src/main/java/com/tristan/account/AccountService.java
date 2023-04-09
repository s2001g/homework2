package com.tristan.account;

import com.tristan.dao.AccountDAO;
import com.tristan.po.Account;
import io.seata.core.context.RootContext;

import java.io.IOException;
import java.sql.SQLException;


public class AccountService {

    AccountDAO dao;
    public Account createAccount() throws IOException {
        dao = new AccountDAO();
        Account record = AccountManager.readomAccount();
//        AccountManager.rabbitMQProvider(record);
        return dao.createAccount(record);
    }

    public Account selectAccount(Integer id) throws IOException {
        dao = new AccountDAO();
        return dao.selectAccount(id);
    }

    public Account updatePoints(Integer accountId, Integer points) throws IOException {
        System.out.println("updateServiceSeata全局事务id=================>{}"+ RootContext.getXID());
        dao = new AccountDAO();
        Account account = dao.selectAccount(accountId);
        account.setPoints(account.getPoints() + points);
        try {
            dao.updateAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
