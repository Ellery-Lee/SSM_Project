package com.liruicong.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liruicong.commonutils.JwtUtils;
import com.liruicong.commonutils.R;
import com.liruicong.eduorder.entity.TOrder;
import com.liruicong.eduorder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author liruicong
 * @since 2021-07-10
 */
@RestController
@RequestMapping("/eduorder/order")
//@CrossOrigin
public class TOrderController {
    @Autowired
    private TOrderService orderService;

    //1、生成订单的方法
    @PostMapping("createOrder/{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request){
        //创建订单，返回订单号
        String orderNo = orderService.createOrders(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderId", orderNo);
    }
    //2、根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderId);
        TOrder order = orderService.getOne(wrapper);
        return R.ok().data("item", order);
    }
    //根据课程id和用户id查询订单表中状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId, @PathVariable String memberId){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId).eq("member_id", memberId).eq("status", 1);//支付状态1代表已经支付
        int count = orderService.count(wrapper);
        return count > 0 ? true : false;
    }
}

