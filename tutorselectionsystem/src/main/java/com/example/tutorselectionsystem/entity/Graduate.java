package com.example.tutorselectionsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"tutorname", "transcripts"})
public class Graduate {
    //学生类
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //    private Integer studentNumber;//学号
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId
    private User user;
    private String name;//学生姓名
    private Float overallScore;//依据各项成绩排序最终得到的综合成绩，用于排名使用
//    private Integer pwd;//学生密码，默认初始化为学号
    private Integer ranking;//当前学生排名

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp " +
            "on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;

    @ManyToOne
    private Tutor tutor;//导师姓名

    @OneToMany(mappedBy = "graduate")
    private List<Transcript> transcripts;//成绩表

    /**
     * 自己专门放id的构造函数
     * * @param tid
     */
    public Graduate(int tid) {
        this.id = tid;
    }
}
//毕业生（graduate）：(通过选课名单导入)
//主键(uuid,UUID),学号（studentNumber,int），
// 姓名（name,String），已经选课程以及方向的ID号组成的集合-外键(courseList,List<courses>,
// 老师ID-外键（teacherID，UUID）；