package com.liruicong.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liruicong.commonutils.JwtUtils;
import com.liruicong.commonutils.MD5;
import com.liruicong.educenter.entity.UcenterMember;
import com.liruicong.educenter.entity.vo.RegisterVo;
import com.liruicong.educenter.mapper.UcenterMemberMapper;
import com.liruicong.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liruicong.servicebase.exceptionhandler.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author liruicong
 * @since 2021-07-05
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    //登录的方法
    @Override
    public String login(UcenterMember member) {
        //获取登录手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();
        //手机号和密码非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new GuliException(20001, "登陆失败");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        UcenterMember mobileMember = this.getOne(wrapper);
        //判断查出来的对象是否成功
        if(mobileMember == null){//没有这个手机号
            throw new GuliException(20001, "手机号错误，登陆失败");
        }

        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new GuliException(20001, "密码错误，登陆失败");
        }

        //判断用户是否禁用
        if(mobileMember.getIsDeleted()){
            throw new GuliException(20001, "用户禁用，登陆失败");
        }

        //登陆成功
        //生成token字符串，使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());

        return jwtToken;
    }

    //注册
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册的数据
        String email = registerVo.getEmail();//邮箱
        String mobile = registerVo.getMobile();//手机号
        String nickName = registerVo.getNickname();//昵称
        String password = registerVo.getPassword();//密码
        //非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email) || StringUtils.isEmpty(nickName)){
            throw new GuliException(20001, "登陆失败");
        }
        //TODO 短信/邮箱验证

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        int count = this.count(wrapper);
        if(count > 0){
            throw new GuliException(20001, "手机号已注册，不可重复注册");
        }

        //数据添加到数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickName);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);//用户不禁用
        member.setEmail(email);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }

    //根据openid判断
    @Override
    public UcenterMember getOpendIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        UcenterMember member = this.getOne(wrapper);
        return member;
    }
}
