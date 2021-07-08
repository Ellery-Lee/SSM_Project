package com.liruicong.educenter.service;

import com.liruicong.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liruicong.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author liruicong
 * @since 2021-07-05
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    //登录的方法
    String login(UcenterMember member);
    //注册
    void register(RegisterVo registerVo);
    //根据openid判断
    UcenterMember getOpendIdMember(String openid);
}
