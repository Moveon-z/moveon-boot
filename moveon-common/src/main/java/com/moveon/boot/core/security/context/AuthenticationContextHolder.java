package com.moveon.boot.core.security.context;

import org.springframework.security.core.Authentication;

/**
 * @ClassName AuthenticationContextHolder
 * @Description TODO
 * @Author Moveon
 * @Date 2023/11/14 10:30
 * @Version 1.0
 **/
public class AuthenticationContextHolder {

    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext() {
        return contextHolder.get();
    }

    public static void setContext(Authentication context) {
        contextHolder.set(context);
    }

    public static void clearContext() {
        contextHolder.remove();
    }
}
