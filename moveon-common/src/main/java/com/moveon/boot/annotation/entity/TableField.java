package com.moveon.boot.annotation.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableField {

    /**
     * 字段对应的表字段名，没写默认为驼峰命名
     * @return
     */
    String field() default "";
}
