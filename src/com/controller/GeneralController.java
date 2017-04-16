package com.controller;

import com.common.result.JsonResult;
import com.common.util.DesEncryptionUtils;
import com.common.util.JsonMapUtils;
import com.interceptor.SessionInterceptor;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.model.GeneralDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizy_java on 2017/4/14.
 */
public class GeneralController extends Controller {

    private static Log logger = Log.getLog(GeneralController.class);

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

        GeneralDao general = GeneralDao.generalDao.add(map);
        renderJson(new JsonResult("success", null, "0", general, null));
    }

    public void list(){
        renderJson(new JsonResult("success", null, "0", GeneralDao.generalDao.list(), null));
    }

    public void detail(){
        Long id = getParaToLong("id");
        if(id == null){
            renderJson(new JsonResult("请传入ID", null, "1", null, null));
            return;
        }
        GeneralDao general = GeneralDao.generalDao.detail(id);
        try{
            String  content = DesEncryptionUtils.decrypt(new String(general.getStr("content").getBytes("iso8859-1"),"utf-8"));
            general.put("content",content);
        }catch (Exception e){
            renderJson(new JsonResult("解析文章内容出错", null, "1", null, null));
            return;
        }

        renderJson(new JsonResult("success", null, "0", general, null));

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
            renderJson(new JsonResult("请传入项目名称", null, "1", null, null));
            return;
        }

        GeneralDao general = GeneralDao.generalDao.update(map);
        renderJson(new JsonResult("success", null, "0", general, null));
    }

    public void delete(){
        Long id = getParaToLong("id");
        if(id == null){
            renderJson(new JsonResult("请选择要删除的数据", null, "1", null, null));
            return;
        }
        boolean isDelete = GeneralDao.generalDao.delete(id);
        if(isDelete){
            renderJson(new JsonResult("success", null, "0", null, null));
        }else{
            renderJson(new JsonResult("删除数据失败", null, "1", null, null));
        }

    }

    @Clear(SessionInterceptor.class)
    public void getList(){
        List<GeneralDao> generalDaos = GeneralDao.generalDao.list();
        for(GeneralDao general:generalDaos){
            try{
                String longText =DesEncryptionUtils.decrypt(new String(general.getStr("content").getBytes("iso8859-1"),"utf-8"));
                general.set("content",longText);

            }catch (Exception e){
                renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
                return;
            }
        }
        renderJson(new JsonResult("success", null, "0", generalDaos, null));
    }

}
