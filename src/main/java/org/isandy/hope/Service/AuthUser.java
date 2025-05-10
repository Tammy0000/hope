package org.isandy.hope.Service;

/**
 * @author Tammy
 * @date 2025/4/28 下午5:06
 */
public interface AuthUser {
    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param phone 手机号
     * @param phone 手机号
     * @return 注册结果
     * @description: 0 注册成功，-1 手机号已存在，-2 用户名已经存在
     */
    // todo 注册
    default int register(String username, String password, String phone) {return 0;}

    /**
     * 登录
     * @param phone 手机号
     * @param password 密码
     * @return 登录结果
     * @description: 0 登录成功，-1 手机号不存在， -2 密码错误
     */
    // todo 登录
    default int login(String phone, String password) {return 0;}

    /**
     * 登出
     * @param phone 手机号
     */
    default void logout(String phone) {}

    /**
     * @description: 修改密码
     * @param phone 手机号
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 修改结果
     */
    //  todo 修改密码
    default int editPassword(String phone, String oldPassword, String newPassword) {return 0;}

    /**
     * @description: 修改手机号
     * @param oldPhone 旧手机号
     * @param newPhone 新手机号
     * @param Password 密码
     * @return 修改结果
     */
    default int editPhone(String oldPhone, String newPhone, String Password) {return 0;}
}
