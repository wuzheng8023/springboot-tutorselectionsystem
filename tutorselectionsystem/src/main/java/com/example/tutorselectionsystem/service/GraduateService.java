package com.example.tutorselectionsystem.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.example.tutorselectionsystem.entity.Courses;
import com.example.tutorselectionsystem.entity.Graduate;
import com.example.tutorselectionsystem.entity.Transcript;
import com.example.tutorselectionsystem.repository.GraduateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GraduateService {

    @Autowired
    private GraduateRepository graduateRepository;
    @Autowired
    private CourseSerivce courseSerivce;

    @Autowired
    private TranscriptService transcriptService;

    /**
     * 查询所有毕业生
     *
     * @return
     */
    public List<Graduate> getAllGraduate() {
        return graduateRepository.graduateList().orElse(List.of());
    }

    /**
     * 添加毕业生
     *
     * @param graduate
     * @return
     */
    public Graduate addGraduate(Graduate graduate) {
        return graduateRepository.save(graduate);
    }

    /**
     * 依据姓名删除学生
     *
     * @param name
     */
    public void deleteByName(String name) {
        graduateRepository.deleteByName(name);
    }

    /**
     * 依据id删除学生
     *
     * @param id
     */
    public void deleteById(Integer id) {
        graduateRepository.deleteById(id);
    }

    /**
     * 依据id查询学生
     *
     * @param id
     * @return
     */
    public Graduate getById(Integer id) {
        return graduateRepository.findGraduateById(id).orElse(null);
    }

    /**
     * 依据名字查询学生
     *
     * @param name
     * @return
     */
    public List<Graduate> getByName(String name) {
        return graduateRepository.findGraduateByName(name).orElse(List.of());
    }

    /**
     * 计算当前学生的综合成绩并且将结果存入数据库
     *
     * @param id
     * @return
     */
    public void saveAndCalculateOverallScore(int id) {
        List<Graduate> graduateList = getAllGraduate();
        for (Graduate g : graduateList) {
            g.setOverallScore(calculateOverallScore(g.getId()));
            graduateRepository.save(g);
        }
    }

    /**
     * 讲排名记录在数据库中
     */
    public void saveSortAllGraduateRanging() {
        List<Graduate> graduateList = sortAllGraduateRanging();
        for (Graduate g : graduateList) {
            graduateRepository.save(g);
        }
    }


    //非CRUD操作---------------------------------------

    /**
     * 依据id计算指定同学的综合成绩（即所选课程综合占比），仅仅计算不记入数据库
     *
     * @param id
     * @return
     */
    public float calculateOverallScore(int id) {
        Graduate graduate = graduateRepository.findGraduateById(id).get();
        List<Transcript> transcriptList = transcriptService.getAllByGraduateId(id);
        float graduateOverallScore = 0;

        for (Transcript t : transcriptList) {
            Courses temp_course = t.getCourse();
            if (temp_course.getType() == 1) { //等于1则代表是课程而不是方向
                graduateOverallScore += temp_course.getWeight() * t.getGrade();
            }
        }
        graduate.setOverallScore(graduateOverallScore);
        return graduate.getOverallScore();
    }


    /**
     * 为所有学生重新排序（学生实体类设置了名次，前端渲染加序号）
     *
     * @return
     */
    public List<Graduate> sortAllGraduateRanging() {
        List<Graduate> graduateList = getAllGraduate();
        graduateList.stream()
                .sorted(Comparator.comparing(Graduate::getOverallScore).reversed())
                .collect(Collectors.toList());

        //以下是赋予排名
        Graduate[] graduates = (Graduate[]) graduateList.toArray();
        for (int i = 0; i < graduates.length; i++) {
            if (i == 0) {
                graduates[i].setRanking(1);
            } else if (graduates[i] == graduates[i - 1]) {
                graduates[i].setRanking(graduates[i - 1].getRanking());
            } else graduates[i].setRanking(i + 1);
        }
        return graduateList;
//reversed()为降序，不添加则默认为升序
    }

}
