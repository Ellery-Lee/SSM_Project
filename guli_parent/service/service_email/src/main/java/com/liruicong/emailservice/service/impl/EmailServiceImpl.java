package com.liruicong.emailservice.service.impl;

import com.liruicong.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    //发送邮箱验证码服务
    @Override
    public boolean send(String email, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Bootcamp注册验证码");
            message.setFrom("623568934@qq.com");
            message.setTo(email);
            message.setText("你的注册验证码为：" + code);
            mailSender.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
