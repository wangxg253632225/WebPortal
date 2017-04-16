package com.model;

import com.common.result.JsonResult;
import com.common.util.StringUtils;
import com.exception.ServiceException;
import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Page;
import com.model.bean.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xg on 16/11/14.
 */
public class ContactDao extends Contact<ContactDao> implements IBean {

    private static final long serialVersionUID = -5436299664296094413L;
    public static final ContactDao contactDao = new ContactDao();


    /**
     * 获取用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Map<String,Object> getList(int pageNum, int pageSize) {
        Map<String,Object> returnData = new HashMap<String,Object>();
        Page<ContactDao> contactDaoPage = contactDao.paginate(pageNum, pageSize, "select *", "from gov_contact");
        List<Map<String,Object>> contactDaoList = new ArrayList<Map<String, Object>>();
        returnData.put("pageNumber", contactDaoPage.getPageNumber());
        returnData.put("pageSize", contactDaoPage.getPageSize());
        returnData.put("totalRow", contactDaoPage.getTotalRow());
        returnData.put("totalPage", contactDaoPage.getTotalPage());
        returnData.put("firstPage", contactDaoPage.isFirstPage());
        returnData.put("lastPage", contactDaoPage.isLastPage());
        for(int i = 0 ; i < contactDaoPage.getList().size(); i ++ ){
            Map<String,Object>  returnMap = new HashMap();
            returnMap.put("id",contactDaoPage.getList().get(i).get("id"));
            returnMap.put("companyName",contactDaoPage.getList().get(i).get("company_name"));
            returnMap.put("mobilePhone",contactDaoPage.getList().get(i).get("mobile_phone"));
            returnMap.put("email",contactDaoPage.getList().get(i).get("email"));
            returnMap.put("businessTime",contactDaoPage.getList().get(i).get("business_time"));
            returnMap.put("fax",contactDaoPage.getList().get(i).get("fax"));
            returnMap.put("zipCode",contactDaoPage.getList().get(i).get("zip_code"));
            returnMap.put("address",contactDaoPage.getList().get(i).get("address"));
            returnMap.put("addressLng",contactDaoPage.getList().get(i).get("address_lng"));
            returnMap.put("addressLat",contactDaoPage.getList().get(i).get("address_lat"));
            returnMap.put("content",contactDaoPage.getList().get(i).get("content"));
            returnMap.put("remark",contactDaoPage.getList().get(i).get("remark"));
            returnMap.put("landlineTelephone",contactDaoPage.getList().get(i).get("landline_telephone"));
            returnMap.put("linkMan",contactDaoPage.getList().get(i).get("link_man"));
            contactDaoList.add(returnMap);
        }
        returnData.put("list",contactDaoList);
        return returnData;
    }





}
