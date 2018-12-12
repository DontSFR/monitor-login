package cn.fyd.monitorlogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动类
 * EnableEurekaClient注解表明这是一个服务
 * @author fanyidong
 * @date 2018-12-11
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.fyd.monitorlogin.dao")
public class MonitorLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorLoginApplication.class, args);
    }
}
