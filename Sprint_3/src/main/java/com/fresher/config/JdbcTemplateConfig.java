package com.fresher.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.fresher"})
public class JdbcTemplateConfig {
}