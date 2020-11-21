package com.liruicong.service.impl;

import com.liruicong.dao.IAccountDao;
import com.liruicong.dao.impl.AccountDaoImpl;
import com.liruicong.factory.BeanFactory;
import com.liruicong.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
//    private IAccountDao accountDao = new AccountDaoImpl();
    private IAccountDao accountDao = null;
    public void saveAccount(){
        accountDao = (IAccountDao) BeanFactory.getBean("accountDao");
        accountDao.saveAccount();
    }
}
