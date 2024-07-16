package com.moveon.boot.learn.netty.chat.server.session;

/**
 * @ClassName SessionFactory
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/15 10:09
 * @Version 1.0
 */
public abstract class SessionFactory {

    private static Session session = new SessionMemoryImpl();

    public static Session getSession() {
        return session;
    }
}
