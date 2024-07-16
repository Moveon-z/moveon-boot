package com.moveon.boot.learn.netty.chat.service;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/1 15:58
 * @Version 1.0
 */
public interface LoginService {

    /**
     * 登录操作
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);
}
