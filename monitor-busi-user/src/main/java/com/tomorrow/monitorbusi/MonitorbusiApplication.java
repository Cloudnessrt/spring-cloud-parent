package com.tomorrow.monitorbusi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEurekaClient
@SpringBootApplication
@EnableScheduling//开启定时器
@MapperScan("com.tomorrow.monitorbusi.Dao")
public class MonitorbusiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorbusiApplication.class, args);
	}
}
