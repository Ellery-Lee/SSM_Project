package com.liruicong.test;

import com.liruicong.dao.IUserDao;
import com.liruicong.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 测试MyBatis的CRUD操作
 */
public class MyBatisTest {
    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws Exception{
        //1.读取配置文件，生成字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.获取dao的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        //5.执行查询所有方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
        //6.释放资源
        sqlSession.close();
        in.close();
    }
}
