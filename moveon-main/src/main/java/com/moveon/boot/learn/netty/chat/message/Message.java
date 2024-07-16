package com.moveon.boot.learn.netty.chat.message;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Message
 * @Description TODO
 * @Author huangzh
 * @Date 2024/6/24 15:43
 * @Version 1.0
 */
@Data
public abstract class Message implements Serializable {

    public static Class<?> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }

    private int sequenceId;

    private int messageType;

    /**
     * 获取消息类型
     * @return
     */
    public abstract int getMessageType();

    public static final int LoginRequestMessage = 0;
    public static final int LoginResponseMessage = 0;
    public static final int ChatRequestMessage = 0;
    public static final int ChatResponseMessage = 0;
    public static final int GroupCreateRequestMessage = 0;
    public static final int GroupCreateResponseMessage = 0;
    public static final int GroupJoinRequestMessage = 0;
    public static final int GroupJoinResponseMessage = 0;
    public static final int GroupQuitRequestMessage = 0;
    public static final int GroupQuitResponseMessage = 0;
    public static final int GroupChatRequestMessage = 0;
    public static final int GroupChatResponseMessage = 0;
    public static final int GroupMembersRequestMessage = 0;
    public static final int GroupMembersResponseMessage = 0;
    public static final int PingMessage = 0;
    public static final int PongMessage = 0;
    private static final Map<Integer, Class<?>> messageClasses = new HashMap<>();


}
