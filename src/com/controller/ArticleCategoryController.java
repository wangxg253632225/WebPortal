package com.controller;

import com.common.result.JsonResult;
import com.common.util.JsonMapUtils;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.model.ArticleCategoryDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizy_java on 2016/11/28.
 */
public class ArticleCategoryController extends Controller {
    private static Log logger = Log.getLog(ArticleCategoryController.class);

    public void getList(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
        }

        if(getPara("type") == null){
            renderJson(new JsonResult("请传入类型", null, "1", null, null));
        }
        String type = getPara("type");
        List<ArticleCategoryDao> daos = ArticleCategoryDao.articelCategoryDao.getArticleCategorys(type);
        renderJson(new JsonResult("success", null, "0", daos, null));
    }

    public void addArticleCategory(){
        Map<String,Object> map =  new HashMap<String,Object>();
        try{
            map = JsonMapUtils.getRequestObject(this.getRequest());
        }catch (Exception e){
            renderJson(new JsonResult("参入的参数有误", null, "1", null, null));
        }

        if(map.get("cate_name") == null){
            renderJson(new JsonResult("请传入分类名称", null, "1", null, null));
        }

        ArticleCategoryDao cateDao = ArticleCategoryDao.articelCategoryDao.addArticleCategory(map);
        renderJson(new JsonResult("success", null, "0", cateDao, null));

    }
}
