package com.moveon.boot.service.system;

import com.moveon.boot.entity.system.SysUser;

/**
 * @ClassName SysLoginService
 * @Description TODO
 * @Author Moveon
 * @Date 2023/11/14 10:38
 * @Version 1.0
 **/
public interface SysLoginService {

    /**
     * 注册用户
     * @param sysUser
     */
    void registerUser(SysUser sysUser);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    SysUser loginUser(String username, String password);

    /**
     * 密码验证
     * @param sysUser
     */
    void validate(SysUser sysUser);
}
