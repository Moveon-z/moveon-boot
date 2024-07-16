package com.moveon.boot.learn.netty.chat.message;

/**
 * @ClassName PongMessage
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/15 15:39
 * @Version 1.0
 */
public class PongMessage extends Message{
    @Override
    public int getMessageType() {
        return PongMessage;
    }
}
