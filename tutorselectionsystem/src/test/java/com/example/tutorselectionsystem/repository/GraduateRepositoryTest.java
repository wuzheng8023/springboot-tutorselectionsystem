package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Graduate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class GraduateRepositoryTest {
    @Autowired
    private GraduateRepository graduateRepository;

    @Test
    public void test_addGraduate() {
        Graduate graduate = new Graduate();
        graduate.setName("张三");
        Graduate graduate1 = new Graduate();
        graduate1.setName("45646");
        Graduate graduate2 = new Graduate();
        graduate2.setName("111111111111");
        graduateRepository.save(graduate);
        graduateRepository.save(graduate1);
        graduateRepository.save(graduate2);



    }
    @Test
    public void test_findGraduateByid(){
    log.debug("{}",graduateRepository.findGraduateById(1));

    }
}
