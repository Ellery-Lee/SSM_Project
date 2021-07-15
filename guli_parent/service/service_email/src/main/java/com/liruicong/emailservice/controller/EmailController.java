package com.liruicong.emailservice.controller;

import com.liruicong.commonutils.R;
import com.liruicong.emailservice.service.EmailService;
import com.liruicong.emailservice.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/eduemail/email")
public class EmailController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EmailService emailService;

    @GetMapping("send/{email}")
    public R sendEmail(@PathVariable String email){
        String code = redisTemplate.opsForValue().get(email);
        if(!StringUtils.isEmpty(code)){
            return R.ok();
        }
        code = RandomUtil.getSixBitRandom();
        boolean issent = emailService.send(email, code);
        if(issent){
            redisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES);
            return R.ok();
        }else {
            return R.error().message("发送邮箱验证码失败");
        }
    }
}
