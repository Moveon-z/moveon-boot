package com.moveon.boot.service.system.adapter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.moveon.boot.entity.system.SysUser;
import com.moveon.boot.service.system.impl.SysLoginServiceImpl;
import com.moveon.boot.service.system.impl.SysUserServiceImpl;
import com.moveon.boot.util.http.HttpClientUtils;
import com.moveon.boot.util.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName SysLogin3rdAdapter
 * @Description TODO
 * @Author Moveon
 * @Date 2023/11/14 19:58
 * @Version 1.0
 **/
@Component
public class SysLogin3rdAdapter extends SysLoginServiceImpl implements SysLogin3rdTarget {

    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    @Override
    public String loginByGitee(String code, String state) {
//        if (!giteeState.equals(state)) {
//            throw new UnsupportedOperationException("Invalid state!");
//        }
        String tokenUrl = giteeTokenUrl.concat(code);
        JSONObject tokenResponse = HttpClientUtils.execute(tokenUrl, HttpMethod.POST);
        String token = String.valueOf(tokenResponse.get("access_token"));
        String userUrl = giteeUserUrl.concat(token);
        JSONObject userInfoResponse = HttpClientUtils.execute(userUrl, HttpMethod.GET);
        String userName = giteeUserPrefix.concat(String.valueOf(userInfoResponse.get("name")));
        String password = userName;

        return autoRegister3rdAndLogin(userName, password);
    }

    private String autoRegister3rdAndLogin(String username, String password) {
        // 如果第三方账号已经登陆过，则直接登陆
        if (super.checkUserExist(username)) {
            SysUser user = super.loginUser(username, password);
            return JSONObject.toJSONString(user);
        }
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setName("moveon");
        sysUser.setUserType("1");
        sysUser.setEmail("111");
        sysUser.setSex("1");
        sysUser.setStatus("1");
        sysUser.setCreateTime(new Date());
        sysUser.setCreateBy(SecurityUtils.getUserId());
        super.registerUser(sysUser);
        return JSONObject.toJSONString(super.loginUser(username, password));
    }

}
