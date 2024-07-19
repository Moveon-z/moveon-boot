package com.moveon.boot.learn.netty.person.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Response
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/19 16:17
 * @Version 1.0
 */
@Data
public abstract class Response implements Serializable {

    private int sequenceId;

    private int messageType;
}
