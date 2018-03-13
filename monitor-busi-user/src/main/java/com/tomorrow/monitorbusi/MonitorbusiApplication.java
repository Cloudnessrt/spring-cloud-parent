package com.tomorrow.monitorbusi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@SpringBootApplication
@EnableScheduling//开启定时器
@MapperScan("com.tomorrow.monitorbusi.Dao")
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
public class MonitorbusiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorbusiApplication.class, args);
	}
}
