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
    public void registerUser(SysUser sysUser) {
        SysUser user = new SysUser();
        user.setUsername(sysUser.getUsername());
        SysUser getUserByUsername = get(user);
        if (getUserByUsername != null) {
            throw new RuntimeException("该用户名已存在");
        }
        sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
        insert(sysUser);
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
}
