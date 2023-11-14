package com.moveon.boot.entity.system;

import com.moveon.boot.annotation.entity.TableEntity;
import com.moveon.boot.annotation.entity.TableField;
import com.moveon.boot.entity.BaseEntity;

import java.util.Date;

/**
 * @ClassName SysUser
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/22 09:50
 * @Version 1.0
 **/
@TableEntity(tableName = "sys_user")
public class SysUser extends BaseEntity {

    /* 部门id */
    @TableField
    private String deptId;

    /* 用户名 */
    @TableField
    private String username;

    /* 名称 */
    @TableField
    private String name;

    /* 用户密码 */
    @TableField
    private String password;

    /* 用户类型 */
    @TableField
    private String userType;

    /* 邮箱 */
    @TableField
    private String email;

    /* 性别 */
    @TableField
    private String sex;

    /* 手机号 */
    @TableField
    private String phoneNumber;

    /* 头像地址 */
    @TableField
    private String avatar;

    /* 用户状态 */
    @TableField
    private String status;

    /* 最后登陆ip */
    @TableField
    private String loginIp;

    /* 最后登陆时间 */
    @TableField
    private Date loginTime;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
