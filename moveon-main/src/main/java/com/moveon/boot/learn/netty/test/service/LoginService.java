package com.moveon.boot.learn.netty.test.service;

import java.util.HashMap;
import java.util.Map;

public class LoginService {

    public static final Map<String, String> userMap = new HashMap<>();

    public LoginService() {
        userMap.put("moveon", "123");
    }

    public boolean login(String username, String password) {
        if (userMap.containsKey(username)) {
            if (userMap.get(username).equals(password)) {
                return true;
            }
        }
        return false;
    }
}
