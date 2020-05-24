package com.example.tutorselectionsystem.service;


import com.example.tutorselectionsystem.entity.Transcript;
import com.example.tutorselectionsystem.repository.TranscriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TranscriptService {
    @Autowired
    private TranscriptRepository transcriptRepository;

    /**
     * 依据学生id查找其名下全部成绩项
     *
     * @param id
     * @return
     */
    public List<Transcript> getAllByGraduateId(int id) {
        return transcriptRepository.findAllTranscriptByGraduadeId(id).orElse(List.of());
    }

    /**
     * 依据课程Id查找其名下全部成绩项
     *
     * @param id
     * @return
     */
    public List<Transcript> getAllByCourseId(int id) {
        return transcriptRepository.findAllTranscriptByCourseId(id).orElse(List.of());
    }

    /**
     * 依据课程id和学生id查询指定成绩项
     *
     * @param cid
     * @param gid
     * @return
     */
    public Transcript getTranscriptByGraduateIdAndCourseId(int cid, int gid) {
        return transcriptRepository.findByCourseIdAndGraduateId(cid, gid).orElse(null);
    }

    /**
     * 删除指定课程id和学生id的成绩项
     *
     * @param cid
     * @param gid
     */
    public void deleteTranscriptByGraduateIdAndCourseId(int cid, int gid) {
        transcriptRepository.deleteByCourseIdAndGraduateId(cid, gid);
    }

    /**
     * 删除指定课程id的成绩项
     *
     * @param id
     */

    public void deleteTranscriptByCourseId(int id) {
        transcriptRepository.deleteAllByCourseId(id);
    }

    /**
     * 删除指定学生id的成绩项
     *
     * @param id
     */
    public void deleteTranscriptByGraduateId(int id) {
        transcriptRepository.deleteAllByGraduateId(id);
    }

    //添加一个成绩项
    public Transcript addTranscript(Transcript transcript) {
        return transcriptRepository.refresh(transcriptRepository.save(transcript));
    }

    //删除一个成绩项
    public void deleteTranscript(int tid) {
        transcriptRepository.deleteTranscriptById(tid);
    }

}
