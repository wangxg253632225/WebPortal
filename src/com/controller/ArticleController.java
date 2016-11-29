package com.controller;

import com.common.result.JsonResult;
import com.common.util.JsonMapUtils;
import com.jfinal.core.Controller;
import com.model.ArticleDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizy_java on 2016/11/29.
 */
public class ArticleController extends Controller {

    public void add(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        String longText = (String)map.get("content");

//        ArticleDao article =  ArticleDao.articleDao.add(map);
        renderJson(new JsonResult("success", null, "0", null, null));
    }


    public void getList(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        if(map.get("pageNum") == null){
            map.put("pageNum",1);
        }
        if(map.get("pageSize") == null){
            map.put("pageSize",10);
        }
        String type = getPara("type");
        map.put("type",type);
        Map<String,Object> resultDate = ArticleDao.articleDao.getList(map);
        renderJson(new JsonResult("success", null, "0", resultDate, null));
    }

    public void getDetail(){
        Long id = getParaToLong("id");
        if(id == null){
            renderJson(new JsonResult("请传入ID", null, "1", null, null));
            return;
        }
        ArticleDao resulrDao = ArticleDao.articleDao.getDetail(id);
        renderJson(new JsonResult("success", null, "0", resulrDao, null));
    }

    public void update(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            e.printStackTrace();
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
            return;
        }

        if(map.get("id") == null){
            renderJson(new JsonResult("请参入id", null, "1", null, null));
            return;
        }

        if(map.get("name") == null){
            renderJson(new JsonResult("请参入名称", null, "1", null, null));
            return;
        }

        int count  = ArticleDao.articleDao.update(map);

        if(count > 0){
            renderJson(new JsonResult("success", null, "0", null, null));
        } else{
            renderJson(new JsonResult("更新失败", null, "1", null, null));
        }
    }
}
