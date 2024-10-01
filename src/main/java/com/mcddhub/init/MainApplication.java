package com.mcddhub.init;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * MainApplication
 *
 * @version 1.0.0
 * @author: caobaoqi1029
 * @date: 2024/10/1 11:51
 */
@EnableScheduling
@MapperScan("com.mcddhub.init.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
//@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
