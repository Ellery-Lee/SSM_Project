package com.liruicong.service.impl;

import com.liruicong.dao.IAccountDao;
import com.liruicong.dao.impl.AccountDaoImpl;
import com.liruicong.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao = new AccountDaoImpl();
    public AccountServiceImpl(){
        System.out.println("对象创建了");
    }
    public void saveAccount(){
        accountDao.saveAccount();
    }
}
