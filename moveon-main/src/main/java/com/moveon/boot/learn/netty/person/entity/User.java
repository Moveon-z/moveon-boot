package com.moveon.boot.learn.netty.person.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/19 15:38
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends Response{

    private String username;

    private String password;

    private String nickname;
}
