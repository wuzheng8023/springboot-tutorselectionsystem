package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Tutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Access;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class TutorRepositoryTest {
    @Autowired
    private TutorRepository tutorRepository;

    @Test
    public void test_addTutor(){
        Tutor tutor = new Tutor();
        tutor.setName("bo");
        tutorRepository.save(tutor);

    }

}
