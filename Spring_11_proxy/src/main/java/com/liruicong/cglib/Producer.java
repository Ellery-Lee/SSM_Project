package com.liruicong.cglib;

import com.liruicong.poxy.IProducer;

/**
 * 一个生产者
 */
public class Producer {
    /**
     * 销售
     * @param money
     */
    public void saleProduct(float money){
        System.out.println("拿到钱销售产品" + money);
    }

    /**
     * 售后
     * @param money
     */
    public void afterService(float money){
        System.out.println("提供售后服务并拿到钱" + money);
    }
}
