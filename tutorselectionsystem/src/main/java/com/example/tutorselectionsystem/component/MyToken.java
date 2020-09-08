package com.example.tutorselectionsystem.component;

import com.example.tutorselectionsystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyToken {

    // 按照约定，以“authorization”为键存放信息到Header中，这里使用常量，尽可能减少硬编码
    public static final String AUTHORIZATION = "authorization";
    public static final String UID = "uid";
    public static final String ROLE = "role";
    public static final int OwnID = 2017123456;//记录了唯一的老师的num,方便全局使用
    private Integer uid; //id
    private User.Role role; //角色身份
}
