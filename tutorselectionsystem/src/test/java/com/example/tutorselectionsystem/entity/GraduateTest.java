package com.example.tutorselectionsystem.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class GraduateTest {
    @Autowired
    private EntityManager manager;
    @Test
    public void test_addGraduate(){
        Graduate graduate = new Graduate();
        graduate.setName("hao");
        manager.persist(graduate);
    }
}
