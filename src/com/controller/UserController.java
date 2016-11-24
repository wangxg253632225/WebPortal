package com.controller;

import com.common.result.JsonResult;
import com.common.validator.UserValidator;
import com.exception.ServiceException;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.model.UserDao;

import java.util.List;


/**
 * Created by xg on 16/11/14.
 */
public class UserController extends Controller {
    private static Log logger = Log.getLog(UserController.class);


    @Before(UserValidator.class)
    public void login() throws Exception {
        String username = getPara("username");
        String password = getPara("password");

        List<UserDao> users = UserDao.userDao.getUser(username, password);
        if (users.size() == 0) {
            throw new ServiceException("用户名不存在或密码错误");
        }
        renderJson(new JsonResult("success", null, "0", null, null));
    }
}
