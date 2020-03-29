package com.example.tutorselectionsystem;

import com.example.tutorselectionsystem.repository.BaseRepository;
import com.example.tutorselectionsystem.repository.Impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class TutorselectionsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorselectionsystemApplication.class, args);
    }

}
