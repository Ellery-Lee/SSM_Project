package com.liruicong.test;

import com.liruicong.todo.App;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {
    @Test
    public void accountTest(){
        //得到Spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //从容器中得到AccountDao对象
        App app = ac.getBean(App.class);
        //调用方法
        app.app();
    }
}
