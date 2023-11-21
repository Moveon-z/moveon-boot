package com.moveon.boot.service.security.impl;

import com.moveon.boot.entity.model.LoginUser;
import com.moveon.boot.entity.system.SysUser;
import com.moveon.boot.service.system.SysLoginService;
import com.moveon.boot.service.system.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/29 20:06
 * @Version 1.0
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Resource(name = "sysLoginServiceImpl")
    private SysLoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = new SysUser();
        user.setUsername(username);
        SysUser sysUser = userService.get(user);
        if (Objects.isNull(sysUser)) {
            throw new RuntimeException("Not find this user!");
        }
        loginService.validate(sysUser);
        // 把数据封装成userDetails返回
        LoginUser loginUser = new LoginUser(sysUser);
        return loginUser;
    }
}
