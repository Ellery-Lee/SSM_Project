package com.liruicong.eduorder.service.impl;

import com.liruicong.commonutils.ordervo.CourseWebVoOrder;
import com.liruicong.commonutils.ordervo.UcenterMemberOrder;
import com.liruicong.eduorder.client.EduClient;
import com.liruicong.eduorder.client.UcenterClient;
import com.liruicong.eduorder.entity.TOrder;
import com.liruicong.eduorder.mapper.TOrderMapper;
import com.liruicong.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liruicong.eduorder.utils.OrderNoUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author liruicong
 * @since 2021-07-10
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;
    //创建订单，返回订单号
    @Override
    public String createOrders(String courseId, String memberId) {
        //通过远程调用根据用户id获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);
        //通过远程调用根据课程id获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        //创建Order对象，向order对象里面设置需要的数据
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId);//课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());

        order.setStatus(0);//支付状态 0未支付 1支付
        order.setPayType(1);//支付类型，微信1
        this.save(order);
        //返回订单号
        return order.getOrderNo();
    }
}
