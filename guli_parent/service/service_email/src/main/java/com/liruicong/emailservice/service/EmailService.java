package com.liruicong.emailservice.service;

/**
 * 邮箱服务
 *
 * @author liruicong
 * @since 2021-07-15
 */
public interface EmailService {
    //发送邮箱验证码服务
    boolean send(String email, String code);
}
