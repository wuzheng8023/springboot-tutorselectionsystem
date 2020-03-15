package com.example.tutorselectionsystem.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class TranscriptTest {
    @Autowired
    EntityManager manager;

    @Test
    public void test_addTranscript(){
        Transcript transcript = new Transcript();
        transcript.setGrade(99);
        manager.persist(transcript);
    }

}
