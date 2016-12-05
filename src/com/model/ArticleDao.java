package com.model;

import com.common.util.DateUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Page;
import com.model.bean.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizy_java on 2016/11/29.
 */
public class ArticleDao extends Article<ArticleDao> implements IBean {
    public static final ArticleDao articleDao = new ArticleDao();

    public ArticleDao add(Map<String,Object> data){
        ArticleDao article = new ArticleDao();
        article.put("cate_id",data.get("cate_id"));
        article.put("name",data.get("name"));
        article.put("title",data.get("title"));
        article.put("author",data.get("author"));
        article.put("content",data.get("content"));
        article.set("create_date", DateUtils.currentDatetime());
        article.save();
         return article;
    }

    public Map<String,Object> getList(Map<String,Object> data) {
        Map<String,Object> resultData = new HashMap<String,Object>();
        String selectSql = "select ga.id,ga.cate_id,ga.name,ga.author,ga.title,ga.is_top,gac.cate_name";
        StringBuffer fromSql = new StringBuffer(" from gov_article ga,gov_article_category gac ")
                .append(" where ga.cate_id = gac.id ")
                .append( "and gac.cate_flag = '"+data.get("type")+"'");
        if(data.get("cate_id") != null){
            fromSql.append(" and gac.id ="+data.get("cate_id"));
        }
        Page<ArticleDao> articleDaos = articleDao.paginate((Integer)data.get("pageNum"),(Integer)data.get("pageSize"),selectSql,fromSql.toString());
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        resultData.put("pageNumber", articleDaos.getPageNumber());
        resultData.put("pageSize", articleDaos.getPageSize());
        resultData.put("totalRow", articleDaos.getTotalRow());
        resultData.put("totalPage", articleDaos.getTotalPage());
        resultData.put("firstPage", articleDaos.isFirstPage());
        resultData.put("lastPage", articleDaos.isLastPage());
        for(int i = 0 ; i < articleDaos.getList().size(); i ++ ){
            Map<String,Object>  returnMap = new HashMap();
            returnMap.put("id",articleDaos.getList().get(i).get("id"));
            returnMap.put("cate_id",articleDaos.getList().get(i).get("cate_id"));
            returnMap.put("name",articleDaos.getList().get(i).get("name"));
            returnMap.put("author",articleDaos.getList().get(i).get("author"));
            returnMap.put("title",articleDaos.getList().get(i).get("title"));
            returnMap.put("is_top",articleDaos.getList().get(i).get("is_top"));
            returnMap.put("cate_name",articleDaos.getList().get(i).get("cate_name"));
            list.add(returnMap);
        }
        resultData.put("list",list);
        return resultData;
    }

    public ArticleDao getDetail(Long id){
        return articleDao.findById(id);
    }

    public int update(Map<String,Object> data){

        StringBuffer updateSql = new StringBuffer(" update gov_article set")
                .append(" cate_id = "+ data.get("cate_id")).append(",")
                .append(" name = '"+ data.get("name")).append("',")
                .append(" title = '"+ data.get("title")).append("',")
                .append(" author = '"+ data.get("author")).append("',")
                .append(" last_update_date = '"+ DateUtils.currentDatetime()).append("',")
                .append(" content = '"+ data.get("content")).append("'")
                .append(" where id = " + data.get("id"));
        return   Db.update(updateSql.toString());
    }

    public boolean deleteByIds(String ids){
        String deleteSql = "delete from gov_article where id in ("+ids+")";
        int count = Db.update(deleteSql);
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }
}
