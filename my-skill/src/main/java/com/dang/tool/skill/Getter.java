package com.dang.tool.skill;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:
 *
 * @Date Create in 2018/4/17
 */
@Target({ElementType.TYPE})         // 表示是对类的注解
@Retention(RetentionPolicy.SOURCE)  // 表示这个注解只在编译期起作用，在运行时将不存在
public @interface Getter {
}