package com.moveon.boot.service.system.impl;

import com.moveon.boot.core.security.context.AuthenticationContextHolder;
import com.moveon.boot.entity.model.LoginUser;
import com.moveon.boot.entity.system.SysUser;
import com.moveon.boot.service.entity.impl.AutoBaseServiceImpl;
import com.moveon.boot.service.system.SysUserService;
import com.moveon.boot.util.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/28 14:26
 * @Version 1.0
 **/
@Service
public class SysUserServiceImpl extends AutoBaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean checkUserExist(String username) {
        SysUser user = new SysUser();
        user.setUsername(username);
        SysUser user1 = get(user);
        return (user1 != null);
    }
}
