package com.liruicong.dao;

import com.liruicong.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 在MyBatis中，针对CRUD一共有四个注解
 * @Select @Insert @Update @Delete
 */
public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
