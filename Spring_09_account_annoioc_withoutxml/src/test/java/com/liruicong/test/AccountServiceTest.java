package com.liruicong.test;

import com.liruicong.dao.IAccountDao;
import com.liruicong.domain.Account;
import com.liruicong.service.IAccountService;
import config.SpringConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试：测试配置
 * Spring整合Junit的配置
 *      1、导入Spring整合Junit的jar(坐标)
 *      2、使用Junit提供的一个注解把原有的main方法替换了，替换成Spring提供的
 *          @Runwith
 *      3、告知Spring的运行器，Spring和IOC创建是基于XML还是注解的，并且说明位置
 *          @ContextConfiguration
 *              locations:指定xml文件位置，加上classpath关键字，表示在类路径下
 *              classes:指定注解类所在的位置
 *      4、当我们使用Spring 5.x版本的时候，要求Junit的jar包必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {
//    private ApplicationContext ac;
    @Autowired
    private IAccountService as = null;

    @Test
    public void testFindAll(){
        //3、执行方法
        List<Account> accounts = as.findAllAccount();
        for(Account account : accounts){
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne(){
        //3、执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave(){
        Account account = new Account();
        account.setUsername("test anno");
        account.setMoney(12345f);
        //3、执行方法
        as.saveAccount(account);
    }

    @Test
    public void testUpdate(){
        //3、执行方法
        Account account = as.findAccountById(3);
        account.setMoney(12245f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete(){
        //3、执行方法
        as.deleteAccount(3);
    }
}
