package com.myproject.fedml.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author MacroYuan
 * @className MyBatisConfig
 * @Date 2022/3/31
 * @Version 1.0
 **/
@Configuration
@MapperScan("com.myproject.fedml.mbg.mapper")
public class MyBatisConfig {
}
