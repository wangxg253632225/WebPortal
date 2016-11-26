package com.model;

import com.jfinal.plugin.activerecord.IBean;
import com.model.bean.UserBasicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xg on 16/11/14.
 */
public class UserDao extends UserBasicInfo<UserDao> implements IBean {

    private static final long serialVersionUID = -5436299664296094413L;
    public static final UserDao userDao = new UserDao();


    /**
     * 获取用户列表
     * @param username
     * @param password
     * @return
     */
    public List<UserDao> getUser(String username, String password) {
        List<UserDao> userDaoList = new ArrayList<UserDao>();
        String sql = "select * from USER_BASIC_INFO where USERNAME=?  and  PASSWORD=? ";
        if(username != null && password != null ){
            userDaoList = userDao.find(sql,new Object[]{username,password});
        }
        return userDaoList;
    }

    /**
     * 获取具体用户信息
     * @param username
     * @param password
     * @return
     */
    public List<UserDao> getUserByUsernameAndPassword(String username, String password) {
        List<UserDao> userDaoList = new ArrayList<UserDao>();
        String sql = "select * from USER_BASIC_INFO where USERNAME=?  and  PASSWORD=? ";
        if(username != null && password != null ){
            userDaoList = userDao.find(sql,new Object[]{username,password});
        }
        return userDaoList;
    }

    public List<UserDao> getUserList(){
        String sql = "select * from USER_BASIC_INFO " ;
        List<UserDao> userDaoList = new ArrayList<UserDao>();
        userDaoList  = userDao.find(sql);
        return userDaoList;
    }

    /**
     * 校验用户是否已经存在
     * @param username
     * @return
     */
    public int findOneRecord(String username){
        int count = 0 ;
        String sql = "select * from USER_BASIC_INFO where USERNAME=?";
        List<UserDao> lists = userDao.find(sql,new Object[]{username});
        if(lists != null && lists.size() > 0){
            count = lists.size();
        }
        return count ;
    }

}
