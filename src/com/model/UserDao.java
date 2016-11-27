package com.model;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Page;
import com.model.bean.UserBasicInfo;
import org.eclipse.jetty.server.Authentication;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xg on 16/11/14.
 */
public class UserDao extends UserBasicInfo<UserDao> implements IBean {

    private static final long serialVersionUID = -5436299664296094413L;
    public static final UserDao userDao = new UserDao();


    /**
     * 获取用户列表
     *
     * @param username
     * @param password
     * @return
     */
    public List<UserDao> getUser(String username, String password) {
        List<UserDao> userDaoList = new ArrayList<UserDao>();
        String sql = "select * from USER_BASIC_INFO where USERNAME=?  and  PASSWORD=? ";
        if (username != null && password != null) {
            userDaoList = userDao.find(sql, new Object[]{username, password});
        }
        return userDaoList;
    }

    /**
     * 获取具体用户信息
     *
     * @param username
     * @param password
     * @return
     */
    public List<UserDao> getUserByUsernameAndPassword(String username, String password) {
        List<UserDao> userDaoList = new ArrayList<UserDao>();
        String sql = "select * from USER_BASIC_INFO where USERNAME=?  and  PASSWORD=? ";
        if (username != null && password != null) {
            userDaoList = userDao.find(sql, new Object[]{username, password});
        }
        return userDaoList;
    }

    public Map<String,Object> getUserList(int pageNum, int pageSize) {
        Map<String,Object> returnData = new HashMap<String,Object>();
        Page<UserDao> userDaoPage = userDao.paginate(pageNum, pageSize, "select *", "from USER_BASIC_INFO");
            List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
            returnData.put("pageNumber", userDaoPage.getPageNumber());
            returnData.put("pageSize", userDaoPage.getPageSize());
            returnData.put("totalRow", userDaoPage.getTotalRow());
            returnData.put("totalPage", userDaoPage.getTotalPage());
            returnData.put("firstPage", userDaoPage.isFirstPage());
            returnData.put("lastPage", userDaoPage.isLastPage());
            for(int i = 0 ; i < userDaoPage.getList().size(); i ++ ){
                Map<String,Object>  returnMap = new HashMap();
                returnMap.put("ID",userDaoPage.getList().get(i).get("ID"));
                returnMap.put("USERNAME",userDaoPage.getList().get(i).get("USERNAME"));
                returnMap.put("PASSWORD",userDaoPage.getList().get(i).get("PASSWORD"));
                returnMap.put("CREATE_TIME",userDaoPage.getList().get(i).get("CREATE_TIME"));
                list.add(returnMap);
            }
            returnData.put("list",list);
        return returnData;
    }

    /**
     * 校验用户是否已经存在
     *
     * @param username
     * @return
     */
    public int findOneRecord(String username) {
        int count = 0;
        String sql = "select * from USER_BASIC_INFO where USERNAME=?";
        List<UserDao> lists = userDao.find(sql, new Object[]{username});
        if (lists != null && lists.size() > 0) {
            count = lists.size();
        }
        return count;
    }

    public void addUser(String username, String password) {
        UserDao user = new UserDao();
        user.set("USERNAME", username);
        user.set("PASSWORD", password);
        user.save();
    }

}
