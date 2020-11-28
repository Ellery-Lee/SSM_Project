package com.liruicong.test;

import com.liruicong.dao.IAccountDao;
import com.liruicong.domain.Account;
import com.liruicong.service.IAccountService;
import config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit单元测试：测试配置
 */
public class AccountServiceTest {
    @Test
    public void testFindAll(){
        //1、获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2、得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //3、执行方法
        List<Account> accounts = as.findAllAccount();
        for(Account account : accounts){
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne(){
        //1、获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2、得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //3、执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave(){
        Account account = new Account();
        account.setUsername("test anno");
        account.setMoney(12345f);
        //1、获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2、得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //3、执行方法
        as.saveAccount(account);
    }

    @Test
    public void testUpdate(){
        //1、获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2、得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //3、执行方法
        Account account = as.findAccountById(3);
        account.setMoney(12245f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete(){
        //1、获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2、得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //3、执行方法
        as.deleteAccount(3);
    }
}
