package com.moveon.boot.service.system.impl;

import com.moveon.boot.core.security.context.AuthenticationContextHolder;
import com.moveon.boot.entity.system.SysUser;
import com.moveon.boot.service.system.SysLoginService;
import com.moveon.boot.util.security.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysLoginServiceImpl
 * @Description TODO
 * @Author Moveon
 * @Date 2023/11/14 10:43
 * @Version 1.0
 **/
@Service
public class SysLoginServiceImpl implements SysLoginService {


    @Override
    public void validate(SysUser sysUser) {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
        match(password, sysUser.getPassword());
    }

    protected boolean match(String rowPassword, String encodePassword) {
        return SecurityUtils.matchesPassword(rowPassword, encodePassword);
    }
}
