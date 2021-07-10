package com.liruicong.eduorder.service;

import com.liruicong.eduorder.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author liruicong
 * @since 2021-07-10
 */
public interface TPayLogService extends IService<TPayLog> {
    //返回信息：包含二维码地址，还有其他信息
    Map createNative(String orderNo);
    //根据订单号查询订单支付状态
    Map<String, String> queryPayStatus(String orderNo);
    //向支付表添加记录，更新订单状态
    void updateOrderStatus(Map<String, String> map);
}
