package org.elnino.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"org.elnino.springcloud.dao"})
public class MyBatisConfig {
}
