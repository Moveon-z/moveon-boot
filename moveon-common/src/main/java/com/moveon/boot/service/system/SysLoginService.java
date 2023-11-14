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
     * 密码验证
     * @param sysUser
     */
    void validate(SysUser sysUser);
}
