package com.controller;

import com.common.result.JsonResult;
import com.common.util.DesEncryptionUtils;
import com.common.util.JsonMapUtils;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.model.ArticleDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xg on 17/4/13.
 */
public class ContactController extends Controller {
    private static Log logger = Log.getLog(UserController.class);

    public void add(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
            String longText = DesEncryptionUtils.toHexString(DesEncryptionUtils.encrypt((String)map.get("content")));
            map.put("content",longText);

        }catch (Exception e){
            e.printStackTrace();
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        ArticleDao article =  ArticleDao.articleDao.add(map);
        renderJson(new JsonResult("success", null, "0", article, null));
    }
}
