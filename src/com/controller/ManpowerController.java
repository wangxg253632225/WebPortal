package com.controller;

import com.common.result.JsonResult;
import com.common.util.DesEncryptionUtils;
import com.common.util.JsonMapUtils;
import com.interceptor.SessionInterceptor;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.model.ManpowerDao;
import com.model.ProjectDao;
import com.model.bean.Manpower;
import com.model.bean.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizy_java on 2017/4/14.
 */
public class ManpowerController extends Controller {

    private static Log logger = Log.getLog(ManpowerController.class);

    public void add(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
            String longText = DesEncryptionUtils.toHexString(DesEncryptionUtils.encrypt((String)map.get("content")));
            map.put("content",longText);

        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        ManpowerDao manpower = ManpowerDao.manpowerDao.add(map);
        renderJson(new JsonResult("success", null, "0", manpower, null));
    }

    public void list(){
        renderJson(new JsonResult("success", null, "0", ManpowerDao.manpowerDao.list(), null));
    }

    public void detail(){
        Long id = getParaToLong("id");
        if(id == null){
            renderJson(new JsonResult("请传入ID", null, "1", null, null));
            return;
        }
        ManpowerDao manpower = ManpowerDao.manpowerDao.detail(id);
        try{
            String  content = DesEncryptionUtils.decrypt(new String(manpower.getStr("content").getBytes("iso8859-1"),"utf-8"));
            manpower.put("content",content);
        }catch (Exception e){
            renderJson(new JsonResult("解析文章内容出错", null, "1", null, null));
            return;
        }

        renderJson(new JsonResult("success", null, "0", manpower, null));

    }

    public void update(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
            String longText = DesEncryptionUtils.toHexString(DesEncryptionUtils.encrypt((String)map.get("content")));
            map.put("content",longText);

        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }
        if( map.get("id") == null){
            renderJson(new JsonResult("请传入ID", null, "1", null, null));
            return;
        }
        if( map.get("name") == null){
            renderJson(new JsonResult("请传入名称", null, "1", null, null));
            return;
        }
        ManpowerDao manpower = ManpowerDao.manpowerDao.update(map);
        renderJson(new JsonResult("success", null, "0", manpower, null));
    }

    @Clear(SessionInterceptor.class)
    public void getDetail(){
        ManpowerDao manpower = ManpowerDao.manpowerDao.list();
        try{
            String  content = DesEncryptionUtils.decrypt(new String(manpower.getStr("content").getBytes("iso8859-1"),"utf-8"));
            manpower.put("content",content);
        }catch (Exception e){
            renderJson(new JsonResult("解析文章内容出错", null, "1", null, null));
            return;
        }

        renderJson(new JsonResult("success", null, "0", manpower, null));
    }

}
