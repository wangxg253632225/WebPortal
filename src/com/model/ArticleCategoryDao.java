package com.model;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Page;
import com.model.bean.ArticleCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizy_java on 2016/11/28.
 */
public class ArticleCategoryDao extends ArticleCategory<ArticleCategoryDao> implements IBean {
    private static final long serialVersionUID = -5436299664296094413L;
    public static final ArticleCategoryDao articelCategoryDao = new ArticleCategoryDao();

    public ArticleCategoryDao addArticleCategory(Map<String,Object> map){
        ArticleCategoryDao cateDao = new ArticleCategoryDao();
        cateDao.put("cate_name",map.get("cate_name"));
        cateDao.put("level",map.get("level"));
        cateDao.put("cate_title",map.get("cate_title"));
        cateDao.put("cate_keyword",map.get("cate_keyword"));
        cateDao.put("remark",map.get("remark"));
        cateDao.put("version",map.get("version"));
        cateDao.put("parent_id",map.get("parent_id"));
        cateDao.save();
        return cateDao;
    }

    public  List<ArticleCategoryDao> getArticleCategorys(String type){
        String sql="select * from gov_article_category where cate_flag = '" +type+"'";
        return  articelCategoryDao.find(sql);
    }

}
