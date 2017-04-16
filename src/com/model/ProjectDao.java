package com.model;

import com.common.util.DateUtils;
import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Page;
import com.model.bean.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizy_java on 2017/4/14.
 */
public class ProjectDao extends Project<ProjectDao> implements IBean {

    public static final ProjectDao projectDao = new ProjectDao();

    public Project add(Map map){
        ProjectDao project = new ProjectDao();
        project.set("name",map.get("name"));
        project.set("level",map.get("level"));
        project.set("content",map.get("content"));
        project.set("created_date", DateUtils.currentDatetime());
        project.save();
        return project;
    }

    public  List<ProjectDao> list(){
        Map<String,Object> resultData = new HashMap<String,Object>();
        String selectSql = "select * from gov_project order by level asc ";
        return projectDao.find(selectSql);
    }

    public ProjectDao detail(long id){
        return projectDao.findById(id);
    }

    public Project update(Map map){
        ProjectDao project = new ProjectDao();
        project.set("id",map.get("id"));
        project.set("name",map.get("name"));
        project.set("level",map.get("level"));
        project.set("content",map.get("content"));
        project.set("updated_date", DateUtils.currentDatetime());
        project.update();
        return project;
    }

    public boolean delete(long id){
        return projectDao.deleteById(id);
    }
}
