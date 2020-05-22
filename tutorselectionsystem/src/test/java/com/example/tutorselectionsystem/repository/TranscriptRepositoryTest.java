package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Transcript;
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
public class TranscriptRepositoryTest {
    @Autowired
    private TranscriptRepository transcriptRepository;

    @Test
    public void test_addTranscript(){
        Transcript transcript = new Transcript();
        transcript.setGrade(Float.valueOf(12));
        transcriptRepository.save(transcript);
    }
}
