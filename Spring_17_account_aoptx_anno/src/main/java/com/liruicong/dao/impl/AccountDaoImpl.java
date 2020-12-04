package com.liruicong.dao.impl;

import com.liruicong.dao.IAccountDao;
import com.liruicong.domain.Account;
import com.liruicong.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户的持久层实现类
 */
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private QueryRunner runner;

    @Autowired
    private ConnectionUtils connectionUtils;

    public List<Account> findAllAccount() {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from account", new BeanListHandler<Account>(Account.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Account findAccountById(Integer accountId) {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from account where id = ?", new BeanHandler<Account>(Account.class), accountId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"insert into account(username, money) values(?, ?)", account.getUsername(), account.getMoney());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"update account set username = ?, money = ? where id = ?", account.getUsername(), account.getMoney(), account.getId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"delete from account where id = ?", accountId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Account findAccountByName(String accountName) {
        try {
            List<Account> accounts = runner.query(connectionUtils.getThreadConnection(),"select * from account where username = ?", new BeanListHandler<Account>(Account.class), accountName);
            if(accounts == null || accounts.size() == 0){
                return null;
            }
            if(accounts.size() > 1){
                throw new RuntimeException("结果集不唯一，数据有问题");
            }
            return accounts.get(0);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
