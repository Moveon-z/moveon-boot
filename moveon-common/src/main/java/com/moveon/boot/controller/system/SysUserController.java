package com.moveon.boot.controller.system;

import com.moveon.boot.core.Result;
import com.moveon.boot.entity.system.SysUser;
import com.moveon.boot.service.system.SysLoginService;
import com.moveon.boot.service.system.SysUserService;
import com.moveon.boot.service.system.adapter.SysLogin3rdAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SysUserController
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/28 15:51
 * @Version 1.0
 **/
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysLogin3rdAdapter sysLogin3rdAdapter;

    @PostMapping("/register")
    public Result registerUser(@RequestBody SysUser sysUser) {
        try {
            sysLogin3rdAdapter.registerUser(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("注册失败，原因：" + e.getMessage());
        }
        return Result.success("注册成功");
    }

    @PostMapping("/login")
    public Result login(@RequestBody SysUser sysUser) {
        SysUser user = sysLogin3rdAdapter.loginUser(sysUser.getUsername(), sysUser.getPassword());
        if (user != null) {
            return Result.success("登陆成功", user);
        } else {
            return Result.error("登陆失败");
        }
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody SysUser sysUser) {
        int insert = sysUserService.insert(sysUser);
        if (insert != 0) {
            return Result.success("成功");
        } else {
            return Result.error("失败");
        }
    }

    @RequestMapping("/get/{id}")
    public Result get(@PathVariable("id") long id) {
        SysUser sysUser = sysUserService.get(id);
        if (sysUser != null) {
            return Result.success("成功", sysUser);
        } else {
            return Result.error("失败");
        }
    }

    @GetMapping("/gitee")
    public Result loginByGitee(String code, String state) {
        String s = sysLogin3rdAdapter.loginByGitee(code, state);
        return Result.success(s);
    }
}
