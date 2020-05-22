package com.example.tutorselectionsystem.component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface MyInterceptor {

    AuthorityType[] value() default AuthorityType.USER;

    public enum AuthorityType {
        USER, ADMIN, SUPERADMIN
    }


}
