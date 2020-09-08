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
@JsonIgnoreProperties({"graduates", "courses"})
public class Tutor {
    //导师类
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer selectRange;//选择学生范围，例如前几名
    private Integer numberOfStudentRequired;//实际需要多少学生
    //    private String pwd;//后台权限验证密码
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId
    private User user;

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp " +
            "on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;

    @Column(unique = true)
    @OneToMany(mappedBy = "tutor" ,fetch = FetchType.LAZY)//2020.9.4新增加的fetch = FetchType.LAZY
    private List<Graduate> graduates;//已经拥有的学生
    @Column(unique = true)
    @OneToMany(mappedBy = "tutor",fetch = FetchType.LAZY)//2020.9.4新增加的fetch = FetchType.LAZY
    private List<Courses> courses;//已经拥有的课程

    /**
     * 自己专门放id的构造函数
     * * @param tid
     */
    public Tutor(int tid) {
        this.id = tid;
    }
}
//导师类（tutor）：
//主键（uuid，UUID），姓名（name,String），已选学生列表(students，List<Student>)，
// 选择学生范围-前几名（selectRange,int）,
// 需要总的学生数（numberOfStudentRequired，int），密码（pwd,String）;