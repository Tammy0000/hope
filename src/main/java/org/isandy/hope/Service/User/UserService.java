package org.isandy.hope.Service.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Dao.HopeUserRepository;
import org.isandy.hope.Dao.HopeUserStatusRepository;
import org.isandy.hope.Entity.User.HopeUser;
import org.isandy.hope.Service.AuthUser;
import org.isandy.hope.Utils.RandomLongGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Tammy
 * @date 2025/4/28 下午5:14
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements AuthUser {

    private final HopeUserRepository hopeUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final HopeUserStatusRepository hopeUserStatusRepository;

    @Override
    public int register(String username, String rawPassword, String phone) {
        boolean existsByUsername = hopeUserRepository.existsByUsername(username);
        boolean existsByPhone = hopeUserRepository.existsByPhone(phone);
        if (existsByPhone) return -1;
        if (existsByUsername) return -2;
        String encode = bCryptPasswordEncoder.encode(rawPassword);
        HopeUser user = new HopeUser();
        user.setUsername(username)
                .setPhone(phone)
                .setPassword(encode);
        var userId = 0L;
        do {
            userId = RandomLongGenerator.generate(5);
        } while (hopeUserRepository.existsByUserId(userId));
        user.setUserId(userId);
        hopeUserRepository.save(user);
        return 0;
    }

    @Override
    public int login(String phone, String password) {
        boolean existsByPhone = hopeUserRepository.existsByPhone(phone);
        // 判断手机号是否存在
        if (!existsByPhone) return -1;
        HopeUser byPhone = hopeUserRepository.findByPhone(phone);
        boolean matches = bCryptPasswordEncoder.matches(password, byPhone.getPassword());
        // 密码是否匹配
        if (!matches) return -2;
        //  判断用户是否被封禁
        if (hopeUserStatusRepository.existsByUserIdAndIsBan(byPhone.getId(), true)) return -3;
        return 0;
    }

    @Override
    public void logout(String phone) {
        AuthUser.super.logout(phone);
    }

    @Override
    public int editPassword(String phone, String oldPassword, String newPassword) {
        // 判断用户是否存在
        boolean existsByPhone = hopeUserRepository.existsByPhone(phone);
        if (!existsByPhone) return -1;

        HopeUser byPhone = hopeUserRepository.findByPhone(phone);
        //  判断密码是否正确
        if (!bCryptPasswordEncoder.matches(oldPassword, byPhone.getPassword())) return -2;
        byPhone.setPassword(bCryptPasswordEncoder.encode(newPassword));
        hopeUserRepository.save(byPhone);
        return 0;
    }

    @Override
    public int editPhone(String oldPhone, String newPhone, String Password) {
        boolean o = hopeUserRepository.existsByPhone(oldPhone);
        boolean n = hopeUserRepository.existsByPhone(newPhone);
        //  判断旧手机号是否存在
        if (!o) return -1;
        //  判断新手机号是否存在
        if (n) return -2;
        //判断输入的旧手机号是否正确
        HopeUser hopeUser = hopeUserRepository.findByPhone(oldPhone);
        if (!bCryptPasswordEncoder.matches(Password, hopeUser.getPassword())) return -3;
        hopeUser.setPhone(newPhone);
        hopeUserRepository.save(hopeUser);
        return 0;
    }
}
