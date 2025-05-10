package org.isandy.hope.Controller.User;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Service.AuthUser;
import org.isandy.hope.Utils.JwtUtils;
import org.isandy.hope.Utils.SaResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tammy
 * @date 2025/5/10 下午5:09
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final AuthUser authUser;

    @PostMapping("/user/register")
    public SaResult register(@RequestBody String data) {
        String username;
        String password;
        String phone;
        try {
            JSONObject parsed = JSON.parseObject(data);
            username = parsed.getString("username");
            password = parsed.getString("password");
            phone = parsed.getString("phone");
        } catch (Exception e) {
            return SaResult.error("参数错误");
        }
        int result = authUser.register(username, password, phone);
        var msg = switch (result) {
            case -1 -> "手机号已被注册";
            case -2 -> "用户名已被注册";
            default -> "注册成功";
        };
        return result == 0 ? SaResult.ok(msg) : SaResult.error(msg);
    }

    @PostMapping("/user/login")
    public SaResult login(String phone, String password) {
        int result = authUser.login(phone, password);
        var msg = switch (result) {
            case -1 -> "用户不存在";
            case -2 -> "密码错误";
            default -> "登录成功";
        };
        return result == 0 ? SaResult.ok(msg).set("token", JwtUtils.generateToken(phone)) : SaResult.error(msg);
    }

}
