package com.example.tutorselectionsystem;

import com.example.tutorselectionsystem.repository.BaseRepository;
import com.example.tutorselectionsystem.repository.Impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class TutorselectionsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorselectionsystemApplication.class, args);
    }
    //因为系统启动的时候需要初始化管理员账号同时需要对账号密码加密，所以一开始就需要先把加密组件加入进去；
    @Bean
    public PasswordEncoder getPasswordEnocoder(){
        return  new BCryptPasswordEncoder();
    }
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
