package com.moveon.boot.service.system;

import com.moveon.boot.entity.system.SysUser;
import com.moveon.boot.service.entity.BaseService;

/**
 * @ClassName SysUserService
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/28 14:24
 * @Version 1.0
 **/
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    boolean checkUserExist(String username);
}
