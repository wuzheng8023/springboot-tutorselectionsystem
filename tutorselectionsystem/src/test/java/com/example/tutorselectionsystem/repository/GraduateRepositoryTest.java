package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Graduate;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class GraduateRepositoryTest {
    @Autowired
    private GraduateRepository graduateRepository;

    @Test
    public void test_addGraduate(){
        Graduate graduate = new Graduate();
        graduate.setName("张三");
        graduateRepository.save(graduate);

    }
}
