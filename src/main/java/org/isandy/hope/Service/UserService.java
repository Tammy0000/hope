package org.isandy.hope.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Tammy
 * @date 2025/4/28 下午5:14
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements AuthUser{
    @Override
    public int register(String username, String rawpassword, String phone) {
        return AuthUser.super.register(username, rawpassword, phone);
    }

    @Override
    public int login(String phone, String password) {
        return AuthUser.super.login(phone, password);
    }

    @Override
    public void logout(String phone) {
        AuthUser.super.logout(phone);
    }

    @Override
    public int editPassword(String phone, String oldPassword, String newPassword) {
        return AuthUser.super.editPassword(phone, oldPassword, newPassword);
    }
}
