package com.example.tutorselectionsystem.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.example.tutorselectionsystem.component.MyToken;
import com.example.tutorselectionsystem.entity.*;
import com.example.tutorselectionsystem.repository.GraduateRepository;
import com.example.tutorselectionsystem.repository.UserRepository;
import org.aspectj.bridge.MessageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

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
    @Autowired
    private TutorService tutorService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public Graduate addGraduate(Graduate graduate, int tid) {
        User u = userService.getUser(graduate.getUser().getNumber());
        if (u != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "当前学生已经存在");
        }
        Graduate newGraduate = new Graduate();
        User newUser = new User();
        newUser.setNumber(graduate.getUser().getNumber());
        newUser.setPassword(passwordEncoder.encode(String.valueOf(graduate.getUser().getNumber())));
        newUser.setRole(User.Role.STUDENT);
        newGraduate.setUser(newUser);
        newGraduate.setTutor(tutorService.getTutorByID(tid));
        return graduateRepository.refresh(graduateRepository.save(newGraduate));
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
     * 将排名记录在数据库中
     */
    public void saveSortAllGraduateRanging() {
        List<Graduate> graduateList = sortAllGraduateRanging();
        for (Graduate g : graduateList) {
            graduateRepository.save(g);
        }
    }

    public void deleteGraduateByNum(int num) {
        graduateRepository.deleteByNum(num);
    }

    public Graduate getGraduateByNum(int num) {
        return graduateRepository.findGraduateByNum(num).orElse(null);
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

    //在选择导师的时候，判断学生是否符合条件

    /**
     * 检查学生是否合格或者当前是否选择满了
     *
     * @param sid
     * @return
     */
    public boolean checkQualification(int sid, int tid) {
        Tutor tutor = tutorService.getTutorByID(tid);
        if (tutor.getNumberOfStudentRequired() <= tutor.getGraduates().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "抱歉，当前老师可以指导的学生数量已经达到上限，请尽快联系其它的老师！"
            );
        }
        List<Transcript> transcriptList = transcriptService.getAllByGraduateId(sid);

        for (Transcript t : transcriptList) {
            Courses c = t.getCourse();
            if (t.getGrade() < c.getFloorGroad()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "您的" + c.getCourseName() + "课程成绩达不到课程要求最低要求"
                                + c.getFloorGroad() + "分，请尽快联系其他老师！"
                );
            }
        }
        return true;
    }

//    学生符合条件后，且可以加入老师的队伍的时候，加入队伍
    public void joinTeam(Graduate graduate){
        tutorService.getTutorByID(MyToken.OwnID).getGraduates().add(graduate);
    }

    /**
     * 得到最新学生排名，并且返回有序集合；
     * @return
     */
    public List<Graduate> newRange(){
        List<Graduate> graduateList = getAllGraduate();
        for (Graduate g :graduateList){
            g.setOverallScore(calculateOverallScore(g.getId()));
            graduateRepository.save(g);
        }
        return sortAllGraduateRanging();

    }

}
