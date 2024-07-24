package com.moveon.boot.learn.netty.test.common;

import com.moveon.boot.learn.netty.test.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Message implements Serializable {

    private int sequenceId;

    private int messageType;

    private int serializeType;

    public abstract int getMessageType();

    private static final Map<Integer, Class<?>> classMap = new HashMap<>();

    public Class<?> getClassType(int i) {
        return classMap.get(i);
    }

    public static final int USER_CLASS_NO = 1;

    static {
        classMap.put(USER_CLASS_NO, User.class);
    }


}
