package com.example.tutorselectionsystem.service;

import com.example.tutorselectionsystem.entity.Tutor;
import com.example.tutorselectionsystem.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;

    /**
     * 添加一名教师，并且返回当前添加的教师
     * @param tutor
     * @return
     */
    public Tutor addTutor(Tutor tutor){
        tutorRepository.save(tutor);
        return  tutor;
    }

    /**
     * 更新教师的相关数据
     * @param tutor
     * @return
     */
    public Tutor updateSelectRange(Tutor tutor){

        tutorRepository.save(tutor);
        return tutor;
    }

    /**
     * 基于指定id查询教师
     * @param id
     * @return
     */
    public Tutor getTutorByID(Integer id){
       return tutorRepository.findById(id).orElse(null);
    }
    /**
     * 基于指定姓名查询教师
     * @param name
     * @return
     */
    public Tutor getTutorByName(String name){
        return tutorRepository.findByName(name).orElse(null);
    }







}
