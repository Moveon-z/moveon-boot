package com.moveon.boot.learn.netty.chat.service;

import com.moveon.boot.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginServiceImpl
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/1 15:59
 * @Version 1.0
 */
public class LoginServiceImpl implements LoginService{

    public static final Map<String, String> userMap = new HashMap<String, String>() {{
        put("moveon", "moveon");
        put("zhangsan", "123");
        put("lisi", "321");
    }};
    @Override
    public boolean login(String username, String password) {
        String username1 = userMap.get(username);
        if (StringUtils.isBlank(username1)) {
            return false;
        }
        return password.equals(userMap.get(username));
    }
}
