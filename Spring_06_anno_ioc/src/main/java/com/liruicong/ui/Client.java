package com.liruicong.ui;

import com.liruicong.dao.IAccountDao;
import com.liruicong.service.IAccountService;
import com.liruicong.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //1.获取核心容器对象
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取Bean对象
        IAccountService as = (IAccountService)ac.getBean("accountService");
//        IAccountService as2 = (IAccountService)ac.getBean("accountService");
//        System.out.println(as);
//
//        IAccountDao adao = ac.getBean("accountDao", IAccountDao.class);
//        System.out.println(adao);
//        System.out.println(as == as2);
        as.saveAccount();
        ac.close();
    }
}
