package com.nhnacademy.springjpa.config;

import com.nhnacademy.springjpa.controller.ControllerBase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//디스패처 컨테이너가 초기화 될 때
@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = ControllerBase.class)
public class WebConfig implements WebMvcConfigurer {
}
