package com.model;

import com.common.util.DateUtils;
import com.jfinal.plugin.activerecord.IBean;
import com.model.bean.General;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizy_java on 2017/4/14.
 */
public class GeneralDao extends General<GeneralDao> implements IBean {

    public static final GeneralDao generalDao = new GeneralDao();

    public GeneralDao add(Map map){
        GeneralDao genaral = new GeneralDao();
        genaral.set("name",map.get("name"));
        genaral.set("level",map.get("level"));
        genaral.set("content",map.get("content"));
        genaral.set("created_date", DateUtils.currentDatetime());
        genaral.save();
        return genaral;
    }

    public  List<GeneralDao> list(){
        Map<String,Object> resultData = new HashMap<String,Object>();
        String selectSql = "select * from gov_general order by level asc ";
        return generalDao.find(selectSql);
    }

    public GeneralDao detail(long id){
        return generalDao.findById(id);
    }

    public GeneralDao update(Map map){
        GeneralDao general = new GeneralDao();
        general.set("id",map.get("id"));
        general.set("name",map.get("name"));
        general.set("level",map.get("level"));
        general.set("content",map.get("content"));
        general.set("updated_date", DateUtils.currentDatetime());
        general.update();
        return general;
    }

    public boolean delete(long id){
        return generalDao.deleteById(id);
    }
}
