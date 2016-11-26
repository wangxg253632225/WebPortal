package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.result.JsonResult;
import com.common.validator.UserValidator;
import com.exception.ServiceException;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.model.UserDao;

import java.io.BufferedReader;
import java.io.IOException;
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

    public void getList() {
        List<UserDao> list = UserDao.userDao.getUserList();

        renderJson(new JsonResult("success", null, "0", list, null));
    }

    public void addUser() throws Exception {
//        QueryRequest requst = getRequestObject(QueryRequest.class);
        String username = getPara("username");
        String password = getPara("password");
        if (StrKit.isBlank(username) || StrKit.isBlank(password)) {
            throw new ServiceException("用户名或密码不能为空");
        } else {
            int count = UserDao.userDao.findOneRecord(username);
            if (count > 0) {
                renderJson(new JsonResult("fail","0000","1",null,null));
            }
        }
    }

    /**
     * 取Request中的数据对象
     * @param valueType
     * @return
     * @throws Exception
     */
    protected <T> T getRequestObject(Class<T> valueType) throws Exception {
        StringBuilder json = new StringBuilder();
        BufferedReader reader = this.getRequest().getReader();
        String line = null;
        while((line = reader.readLine()) != null){
            json.append(line);
        }
        reader.close();
        return JSONObject.parseObject(json.toString(), valueType);
    }
}
