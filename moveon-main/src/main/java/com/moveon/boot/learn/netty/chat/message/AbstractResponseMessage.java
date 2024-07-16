package com.moveon.boot.learn.netty.chat.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;

/**
 * @ClassName AbstractResponseMessage
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/1 16:06
 * @Version 1.0
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractResponseMessage extends Message{

    private boolean success;
    private String reason;


}
