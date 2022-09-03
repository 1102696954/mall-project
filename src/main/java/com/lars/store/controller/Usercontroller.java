package com.lars.store.controller;

import com.lars.store.entity.User;
import com.lars.store.service.IUserService;
import com.lars.store.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class Usercontroller extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/reg", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result reg(@RequestBody User user) {
        userService.reg(user);
        return new Result<Void>(OK, null);
    }
}
