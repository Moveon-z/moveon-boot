package com.moveon.boot.learn.netty.test.entity;

import com.moveon.boot.learn.netty.test.common.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Message {

    private String username;

    private String password;

    private String nickname;

    @Override
    public int getMessageType() {
        return USER_CLASS_NO;
    }
}
