package com.model;

import com.common.util.DateUtils;
import com.jfinal.plugin.activerecord.IBean;
import com.model.bean.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizy_java on 2017/4/14.
 */
public class ManpowerDao extends Project<ManpowerDao> implements IBean {

    public static final ManpowerDao manpowerDao = new ManpowerDao();

    public ManpowerDao add(Map map){
        ManpowerDao manpowerDao = new ManpowerDao();
        manpowerDao.set("name",map.get("name"));
        manpowerDao.set("title",map.get("title"));
        manpowerDao.set("content",map.get("content"));
        manpowerDao.set("created_date", DateUtils.currentDatetime());
        manpowerDao.save();
        return manpowerDao;
    }

    public ManpowerDao list(){
        String sql = " select * from gov_manpower ";
        return manpowerDao.findFirst(sql);
    }


    public ManpowerDao detail(long id){
        return manpowerDao.findById(id);
    }

    public ManpowerDao update(Map map){
        ManpowerDao manpowerDao = new ManpowerDao();
        manpowerDao.set("id",map.get("id"));
        manpowerDao.set("name",map.get("name"));
        manpowerDao.set("title",map.get("title"));
        manpowerDao.set("content",map.get("content"));
        manpowerDao.set("updated_date", DateUtils.currentDatetime());
        manpowerDao.update();
        return manpowerDao;
    }
}
