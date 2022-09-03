package com.lars.store.utils;

import com.lars.store.entity.User;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class Md5Util {

    /**
     * 加密
     *
     * @param password
     * @return
     */
    public static User createMd5Password(String password) {
        String salt = UUID.randomUUID().toString().toUpperCase();
        /** 加密规则： * 1、无视原始密码的强度 * 2、使用UUID作为盐值，在原始密码的左右两侧拼接 * 3、循环加密3次 */
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        User user = new User();
        user.setPassword(password);
        user.setSalt(salt);
        return user;
    }
}
