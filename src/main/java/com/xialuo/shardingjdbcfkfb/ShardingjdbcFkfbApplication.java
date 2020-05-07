package com.xialuo.shardingjdbcfkfb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xialuo.shardingjdbcfkfb.dao")
public class ShardingjdbcFkfbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingjdbcFkfbApplication.class, args);
	}

}
