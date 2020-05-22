package com.example.tutorselectionsystem.component;

import lombok.Data;

@Data
public class MyToken {

    public enum Role {
        USER, ADMIN, SUPERADMIN
    }

    private String userName;
    private Role role;
}
