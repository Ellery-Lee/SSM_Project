package com.liruicong.eduorder.service;

import com.liruicong.eduorder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author liruicong
 * @since 2021-07-10
 */
public interface TOrderService extends IService<TOrder> {
    //创建订单，返回订单号
    String createOrders(String courseId, String memberIdByJwtToken);
}
