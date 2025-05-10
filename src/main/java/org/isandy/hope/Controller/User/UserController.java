package org.isandy.hope.Controller.User;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Service.AuthUser;
import org.isandy.hope.Utils.FieldValidator;
import org.isandy.hope.Utils.JsonBodyExtractor;
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
        String username = JsonBodyExtractor.extract(data, "username", String.class);
        String password = JsonBodyExtractor.extract(data, "password", String.class);
        String phone = JsonBodyExtractor.extract(data, "phone", String.class);
        FieldValidator validator = FieldValidator.create()
                .notEmpty(username, "username", "用户名不能为空")
                .notEmpty(password, "password", "密码不能为空")
                .notEmpty(phone, "phone", "手机号不能为空");
        if (validator.hasErrors()) {
            return SaResult.error(validator.firstErrorMessage());
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
