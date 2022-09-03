package com.lars.store.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lars.store.entity.User;
import com.lars.store.mapper.UserMapper;
import com.lars.store.service.IUserService;
import com.lars.store.service.ex.InsertException;
import com.lars.store.service.ex.UsernameDuplicateException;
import com.lars.store.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String usaername = user.getUsername();
        //判断用户是否存在
        User result = userMapper.findByUsername(usaername);

        if (ObjectUtil.isNotEmpty(result)) {
            throw new UsernameDuplicateException("注册的用户名[" + usaername + "]已被占用");
        }

        //创建时间
        Date now = new Date();
        //加密密码
        String md5Password = Md5Util.createMd5Password(user.getPassword()).getPassword();
        String salt = Md5Util.createMd5Password(user.getPassword()).getSalt();

        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setCreatedTime(now);
        user.setCreatedUser(usaername);
        user.setModifiedTime(now);

        Integer rows = userMapper.insert(user);

        if (rows != 1) {
            throw new InsertException("添加用户数据出现异常,请联系管理员");
        }

    }
}
