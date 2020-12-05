package com.liruicong.service.impl;

import com.liruicong.dao.IAccountDao;
import com.liruicong.domain.Account;
import com.liruicong.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * 事务控制应该都是在业务层
 */
@Service("accountService")
@Transactional
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer...");
        /*
        问题：每一个数据库操作都算作一次连接，没有把所有操作打包成事务执行。
        需要使用ThreadLocal对象把Connection和当前线程绑定，从而使一个线程中只有一个能控制事务的对象
        */
        //2.1、根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2.2、根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //2.3、转出账户减钱
        source.setMoney(source.getMoney() - money);
        //2.4、转入账户加钱
        target.setMoney(target.getMoney() + money);
        //2.5、更新转出账户
        accountDao.updateAccount(source);
        int i = 1/0;
        //2.6、更新转入账户
        accountDao.updateAccount(target);
    }
}
