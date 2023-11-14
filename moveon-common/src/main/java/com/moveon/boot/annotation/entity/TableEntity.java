package com.moveon.boot.annotation.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableEntity {

    /**
     * 实体类对应的数据库表名
     * @return
     */
    String tableName();
}
