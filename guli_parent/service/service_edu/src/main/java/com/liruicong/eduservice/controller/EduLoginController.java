package com.liruicong.eduservice.controller;

import com.liruicong.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * 模拟前端登录
 * @author liruicong
 * @since 2021-06-15
 */
//@CrossOrigin
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {
    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token", "admin");
    }
    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic4.zhimg.com%2F50%2Fv2-3d259dde90d4f5dd09fb8b2a8589df1f_hd.jpg&refer=http%3A%2F%2Fpic4.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626335531&t=8d4a8ff5813369a3be7954f6e3b633d6");
    }
}
