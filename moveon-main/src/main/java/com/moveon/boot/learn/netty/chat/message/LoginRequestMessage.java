package com.moveon.boot.learn.netty.chat.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName LoginRequestMessage
 * @Description TODO
 * @Author huangzh
 * @Date 2024/6/24 16:09
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class LoginRequestMessage extends Message{
    private String username;
    private String password;

    @Override
    public int getMessageType() {
        return LoginRequestMessage;
    }
}
