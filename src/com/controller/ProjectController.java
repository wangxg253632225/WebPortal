package com.controller;

import com.common.result.JsonResult;
import com.common.util.DesEncryptionUtils;
import com.common.util.JsonMapUtils;
import com.interceptor.SessionInterceptor;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.model.ProjectDao;
import com.model.bean.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizy_java on 2017/4/14.
 */
public class ProjectController extends Controller {

    private static Log logger = Log.getLog(ProjectController.class);

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

        Project project = ProjectDao.projectDao.add(map);
        renderJson(new JsonResult("success", null, "0", project, null));
    }

    public void list(){
        renderJson(new JsonResult("success", null, "0", ProjectDao.projectDao.list(), null));
    }

    public void detail(){
        Long id = getParaToLong("id");
        if(id == null){
            renderJson(new JsonResult("请传入ID", null, "1", null, null));
            return;
        }
        ProjectDao project = ProjectDao.projectDao.detail(id);
        try{
            String  content = DesEncryptionUtils.decrypt(project.getStr("content"));
            project.put("content",content);
        }catch (Exception e){
            renderJson(new JsonResult("解析文章内容出错", null, "1", null, null));
            return;
        }

        renderJson(new JsonResult("success", null, "0", project, null));

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

        Project project = ProjectDao.projectDao.update(map);
        renderJson(new JsonResult("success", null, "0", project, null));
    }

    public void delete(){
        Long id = getParaToLong("id");
        if(id == null){
            renderJson(new JsonResult("请选择要删除的数据", null, "1", null, null));
            return;
        }
        boolean isDelete = ProjectDao.projectDao.delete(id);
        if(isDelete){
            renderJson(new JsonResult("success", null, "0", null, null));
        }else{
            renderJson(new JsonResult("删除数据失败", null, "1", null, null));
        }

    }

    @Clear(SessionInterceptor.class)
    public void getList(){
        List<ProjectDao> projectDaos = ProjectDao.projectDao.list();
        for(ProjectDao project:projectDaos){
            try{
                String longText =DesEncryptionUtils.decrypt(project.getStr("content"));
                project.set("content",longText);

            }catch (Exception e){
                renderJson(new JsonResult("参数的参数有误", null, "1", null, null));
                return;
            }
        }
        renderJson(new JsonResult("success", null, "0", projectDaos, null));
    }

}
