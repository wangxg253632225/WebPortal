package com.controller;

import com.common.result.JsonResult;
import com.common.util.DesEncryptionUtils;
import com.common.util.JsonMapUtils;
import com.common.validator.UserValidator;
import com.exception.ServiceException;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.model.UserDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public void getList() throws Exception{
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }
        int pageNum = (Integer) map.get("pageNum");/** 第几页*/
        int pageSize = (Integer) map.get("pageSize");/** 页大小*/
        Map<String,Object> retData = UserDao.userDao.getUserList(pageNum,pageSize);
        renderJson(new JsonResult("success", null, "0", retData, null));
    }


    public void addUser() throws Exception {
        Map<String,Object> map = JsonMapUtils.getRequestObject(this.getRequest());

        String username = (String) map.get("username");
        String password = (String) map.get("password");
        if (StrKit.isBlank(username) || StrKit.isBlank(password)) {
            throw new ServiceException("用户名或密码不能为空");
        } else {
            int count = UserDao.userDao.findOneRecord(username);
            if (count > 0) {
                renderJson(new JsonResult("fail", "0000", "1", null, null));
                return;
            }
            UserDao.userDao.addUser(username, password);
            renderJson(new JsonResult("success", null, "0", null, null));
        }
    }

}
