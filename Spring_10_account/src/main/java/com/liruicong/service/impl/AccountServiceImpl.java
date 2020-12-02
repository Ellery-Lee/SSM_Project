package com.liruicong.service.impl;

import com.liruicong.dao.IAccountDao;
import com.liruicong.domain.Account;
import com.liruicong.service.IAccountService;
import com.liruicong.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * 事务控制应该都是在业务层
 */
public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        try{
            //1、开启事务
            txManager.beginTransaction();
            //2、执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //3、提交事务
            txManager.commit();
            //4、返回结果
            return accounts;
        }catch (Exception e){
            //5、回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6、释放连接
            txManager.release();
        }
    }

    public Account findAccountById(Integer accountId) {
        try{
            //1、开启事务
            txManager.beginTransaction();
            //2、执行操作
            Account account = accountDao.findAccountById(accountId);
            //3、提交事务
            txManager.commit();
            //4、返回结果
            return account;
        }catch (Exception e){
            //5、回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6、释放连接
            txManager.release();
        }
    }

    public void saveAccount(Account account) {
        try{
            //1、开启事务
            txManager.beginTransaction();
            //2、执行操作
            accountDao.saveAccount(account);
            //3、提交事务
            txManager.commit();
        }catch (Exception e){
            //4、回滚操作
            txManager.rollback();
        }finally {
            //5、释放连接
            txManager.release();
        }
    }

    public void updateAccount(Account account) {
        try{
            //1、开启事务
            txManager.beginTransaction();
            //2、执行操作
            accountDao.updateAccount(account);
            //3、提交事务
            txManager.commit();
        }catch (Exception e){
            //4、回滚操作
            txManager.rollback();
        }finally {
            //5、释放连接
            txManager.release();
        }
    }

    public void deleteAccount(Integer accountId) {
        try{
            //1、开启事务
            txManager.beginTransaction();
            //2、执行操作
            accountDao.deleteAccount(accountId);
            //3、提交事务
            txManager.commit();
        }catch (Exception e){
            //4、回滚操作
            txManager.rollback();
        }finally {
            //5、释放连接
            txManager.release();
        }
    }

    public void transfer(String sourceName, String targetName, Float money) {
        try{
            //1、开启事务
            txManager.beginTransaction();
            //2、执行操作
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
            //2.6、更新转入账户
            accountDao.updateAccount(target);
            //3、提交事务
            txManager.commit();
        }catch (Exception e){
            //4、回滚操作
            txManager.rollback();
        }finally {
            //5、释放连接
            txManager.release();
        }
    }
}
