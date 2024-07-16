package com.moveon.boot.learn.netty.chat.message;

/**
 * @ClassName PingMessage
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/15 15:37
 * @Version 1.0
 */
public class PingMessage extends Message{
    @Override
    public int getMessageType() {
        return PingMessage;
    }
}
