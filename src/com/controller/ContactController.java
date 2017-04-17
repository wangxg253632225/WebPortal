package com.controller;

import com.common.result.JsonResult;
import com.common.util.JsonMapUtils;
import com.common.util.StringUtils;
import com.exception.ServiceException;
import com.interceptor.SessionInterceptor;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.model.ContactDao;
import com.model.UserDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xg on 17/4/13.
 */
public class ContactController extends Controller {
    private static Log logger = Log.getLog(ContactController.class);

    public void getList(){

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = JsonMapUtils.getRequestObject(this.getRequest());
        } catch (Exception e) {
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }
        int pageNum = (Integer) map.get("pageNum");/** 第几页*/
        int pageSize = (Integer) map.get("pageSize");/** 页大小*/
        Map<String, Object> retData = ContactDao.contactDao.getList(pageNum, pageSize);
        renderJson(new JsonResult("success", null, "0", retData, null));
    }

    /**
     * 查询联系人信息
     */
    @Clear(SessionInterceptor.class)
    public void detail() {
        renderJson(new JsonResult("success", null, "0", ContactDao.contactDao.detail(), null));
    }


    /**
     * 查询联系人信息
     */
    public void getDetail() {
        long id = getParaToLong("id");
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("查询联系人ID不能为空");
        }
        ContactDao contact = ContactDao.contactDao.findById(id);
        renderJson(new JsonResult("success", null, "0", contact, null));
    }



    /**
     * 更新信息维护
     */
    public void updateContact(){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = JsonMapUtils.getRequestObject(this.getRequest());
        } catch (Exception e) {
            e.printStackTrace();
            renderJson(new JsonResult("参入的参数有误", null, "1", null, null));
        }
        if (StringUtils.isEmpty(map.get("username"))) {
            renderJson(new JsonResult("传入用户名为空", null, "1", null, null));
        }

        int count = ContactDao.contactDao.updateContact(map);
        if (count > 0) {
            renderJson(new JsonResult("success", null, "0", null, null));
        } else {
            renderJson(new JsonResult("更新失败", null, "1", null, null));
        }
    }

}
