package com.fresher;


import com.fresher.service.PersonService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootJDBCTemplateApp
{
    public static void main(String[] args) throws Exception {
        ApplicationContext appContext = SpringApplication.run(SpringBootJDBCTemplateApp.class, args);

        //BeanUtil.getAllBeans(appContext);
//
//        PersonService service = appContext.getBean(PersonService.class);
//        service.testPersonRepo();
    }
}