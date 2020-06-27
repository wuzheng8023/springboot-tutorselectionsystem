package com.example.tutorselectionsystem.service;

import com.example.tutorselectionsystem.entity.Courses;
import com.example.tutorselectionsystem.entity.Graduate;
import com.example.tutorselectionsystem.entity.Tutor;
import com.example.tutorselectionsystem.repository.CoursesRepository;
import com.example.tutorselectionsystem.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;


@Service
@Transactional
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private GraduateService graduateService;
    @Autowired
    private CourseSerivce courseSerivce;

    /**
     * 添加一名教师，并且返回当前添加的教师
     *
     * @param tutor
     * @return
     */
    public Tutor addTutor(Tutor tutor) {

        return tutorRepository.refresh(tutorRepository.save(tutor));
    }

    /**
     * 更新教师的相关数据
     *
     * @param tutor
     * @return
     */
    public Tutor updateSelectRange(Tutor tutor) {
        if (getTutorByID(tutor.getId()) != null) {
            return tutorRepository.save(tutor);
        }

        return null;
    }

    /**
     * 更新学生实体
     * @param graduate
     * @return
     */
    public Graduate updateGraduate(Graduate graduate){
        return    graduateService.updateGraduate(graduate);
    }

    /**
     * 基于指定id查询教师
     *
     * @param id
     * @return
     */
    public Tutor getTutorByID(Integer id) {
        return tutorRepository.findById(id).orElse(null);
    }

    /**
     * 基于指定姓名查询教师
     *
     * @param name
     * @return
     */
    public Tutor getTutorByName(String name) {
        return tutorRepository.findByName(name).orElse(null);
    }

    public void deleteTutorByNum(int num) {
        tutorRepository.deleteByNum(num);
    }

    public Tutor getTutorByNum(int num) {

        return tutorRepository.findTutorByNum(num).orElse(null);
    }


    //非CRUD操作-----------------------------------

    /**
     * 添加一名学生到老师选择的名单中
     *
     * @param tid
     * @param sid
     * @return
     */
    public Tutor addTutorStudent(int tid, int sid) {
        Graduate graduate = graduateService.getById(sid);
        Tutor tutor = getTutorByID(tid);

        if (graduate == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "当前学生不存在");
        }
        if (tutor.getNumberOfStudentRequired() <= tutor.getGraduates().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "抱歉，当前可指导的学生已经达到上限！"
            );
        }
        tutor.getGraduates().add(graduate);

        return tutor;
    }


    /**
     * 从选择名单删除一名学生
     *
     * @param tid
     * @param sid
     * @return
     */
    public Tutor deleteTutorStudent(int tid, int sid) {
        Graduate graduate = graduateService.getById(sid);
        Tutor tutor = getTutorByID(tid);

        if (graduate == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "无法删除，当前学生不存在");
        }
        Iterator<Graduate> iterator = tutor.getGraduates().iterator();
        int f = 0;
        while (iterator.hasNext()) {
            Graduate temp = iterator.next();
            if (temp.getId() == sid) {
                f = 1;
                iterator.remove();
                break;
            }
        }
        if (f == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "无法删除，当前学生不在指导列表");
        }
        return tutor;
    }


    /**
     * 添加一门课程到自己教授的课程列表
     *
     * @param tid
     * @param cid
     * @return
     */
    public Tutor addCourse(int tid, int cid) {
        Courses courses = courseSerivce.getCoursesById(cid);

        if (courses == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "当前课程不存在");
        }
        Tutor tutor = getTutorByID(tid);
        tutor.getCourses().add(courses);
        return tutor;
    }


    /**
     * 删除一个自己教授的课程，但是课程实体本身存在，只是剔除去名单
     *
     * @param tid
     * @param cid
     * @return
     */
    public Tutor deleteCourse(int tid, int cid) {

        Courses courses =courseSerivce.getCoursesById(cid);

        if (courses == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "抱歉，这门课程不存在");
        }
        Tutor tutor = getTutorByID(tid);
        Iterator<Courses> iterator = tutor.getCourses().iterator();
        int f = 0;
        while (iterator.hasNext()) {
            Courses temp = iterator.next();
            if (temp.getId() == cid) {
                f = 1;
                iterator.remove();
                break;
            }
        }
        if (f == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "抱歉您并没有教授这门课程");
        }
        return tutor;
    }


}
