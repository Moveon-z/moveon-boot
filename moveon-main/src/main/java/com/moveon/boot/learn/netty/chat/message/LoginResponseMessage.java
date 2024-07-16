package com.moveon.boot.learn.netty.chat.message;

/**
 * @ClassName LoginResponseMessage
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/1 16:05
 * @Version 1.0
 */
public class LoginResponseMessage extends AbstractResponseMessage{

    public LoginResponseMessage(boolean success, String reason) {
        super(success, reason);
    }
    @Override
    public int getMessageType() {
        return LoginResponseMessage;
    }
}
