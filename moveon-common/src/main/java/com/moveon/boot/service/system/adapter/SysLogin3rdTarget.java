package com.moveon.boot.service.system.adapter;

/**
 * @ClassName SysLogin3rdTarget
 * @Description TODO
 * @Author Moveon
 * @Date 2023/11/14 19:56
 * @Version 1.0
 **/
public interface SysLogin3rdTarget {

    /**
     * gitee第三方登陆
     * @param code
     * @param state
     * @return
     */
    String loginByGitee(String code, String state);
}
