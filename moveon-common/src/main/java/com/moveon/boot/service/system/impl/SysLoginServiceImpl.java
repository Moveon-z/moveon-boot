package com.moveon.boot.service.system.impl;

import com.moveon.boot.core.security.context.AuthenticationContextHolder;
import com.moveon.boot.entity.model.LoginUser;
import com.moveon.boot.entity.system.SysUser;
import com.moveon.boot.service.system.SysLoginService;
import com.moveon.boot.service.system.SysUserService;
import com.moveon.boot.util.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName SysLoginServiceImpl
 * @Description TODO
 * @Author Moveon
 * @Date 2023/11/14 10:43
 * @Version 1.0
 **/
@Service(value = "sysLoginServiceImpl")
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void registerUser(SysUser sysUser) {
        if (checkUserExist(sysUser.getUsername())) {
            throw new RuntimeException("该用户名已存在");
        }
        sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
        sysUserService.insert(sysUser);
    }

    @Override
    public SysUser loginUser(String username, String password) {
        Authentication authenticate = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登陆失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getSysUser().getId().toString();
        loginUser.setUsername(username);
//        loginUser.setPassword();
        return loginUser.getSysUser();
    }

    @Override
    public void validate(SysUser sysUser) {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
        match(password, sysUser.getPassword());
    }

    protected boolean match(String rowPassword, String encodePassword) {
        return SecurityUtils.matchesPassword(rowPassword, encodePassword);
    }

    public boolean checkUserExist(String username) {
        return sysUserService.checkUserExist(username);
    }
}
