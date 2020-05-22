package com.example.tutorselectionsystem.service;

import com.example.tutorselectionsystem.entity.Graduate;
import com.example.tutorselectionsystem.repository.GraduateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GraduateService {

    @Autowired
    private GraduateRepository graduateRepository;

    /**
     * 查询所有毕业生
     * @return
     */
    public List<Graduate> getAllGraduate(){
        return graduateRepository.graduateList().orElse(List.of());
    }

    /**
     * 添加毕业生
     * @param graduate
     * @return
     */
    public Graduate addGraduate(Graduate graduate){
        return graduateRepository.save(graduate);
    }

    /**
     * 依据姓名删除学生
     * @param name
     */
    public void deleteByName(String name){
        graduateRepository.deleteByName(name);
    }

    /**
     * 依据id删除学生
     * @param id
     */
    public void deleteById(Integer id){
        graduateRepository.deleteById(id);
    }

    /**
     * 依据id查询学生
     * @param id
     * @return
     */
    public Graduate getById(Integer id){
        return graduateRepository.findGraduateById(id).orElse(null);
    }

    /**
     * 依据名字查询学生
     * @param name
     * @return
     */
    public List<Graduate> getByName(String name){
        return graduateRepository.findGraduateByName(name).orElse(List.of());
    }


}
