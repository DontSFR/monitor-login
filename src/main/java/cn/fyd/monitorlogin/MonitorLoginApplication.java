package cn.fyd.monitorlogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 * EnableEurekaClient注解表明这是一个服务
 * @author fanyidong
 * @date 2018-12-11
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.fyd.monitorlogin.dao")
@ServletComponentScan("cn.fyd.monitorlogin.servlet")
public class MonitorLoginApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MonitorLoginApplication.class, args);
    }

    /**
     * 构建war包 部署 需要
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MonitorLoginApplication.class);
    }
}
