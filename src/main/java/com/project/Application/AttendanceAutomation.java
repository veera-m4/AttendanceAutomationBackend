package com.project.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages="com.*")
@EnableJpaRepositories(basePackages="com.*")
@EnableTransactionManagement
@EntityScan(basePackages="com.*")
public class AttendanceAutomation
{

    public static void main(String[] args)
    {

        SpringApplication.run(AttendanceAutomation.class, args);
    }
}
