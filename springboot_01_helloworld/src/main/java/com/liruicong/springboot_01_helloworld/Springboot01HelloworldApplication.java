package com.liruicong.springboot_01_helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//本身就是Spring的一个组件

//主程序入口
@SpringBootApplication
public class Springboot01HelloworldApplication {
    //SpringBootApplication
    public static void main(String[] args) {
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }

}
