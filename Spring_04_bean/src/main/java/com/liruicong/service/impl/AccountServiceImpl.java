package com.liruicong.service.impl;

import com.liruicong.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
    public AccountServiceImpl(){
        System.out.println("对象创建了");
    }
    public void saveAccount(){
        System.out.println("service中的saveAccount方法执行了");
    }
    public void init(){
        System.out.println("对象初始化");
    }
    public void destroy(){
        System.out.println("对象销毁了");
    }
}
