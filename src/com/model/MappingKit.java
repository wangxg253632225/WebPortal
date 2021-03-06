package com.model;

import com.demo.common.model.Blog;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Created by xg on 16/11/14.
 */
public class MappingKit {

    public static void mapping(ActiveRecordPlugin arp) {
        arp.addMapping("blog", "id", Blog.class);
        arp.addMapping("gov_user","id", UserDao.class); //操作用户dao
        arp.addMapping("gov_article_category","id", ArticleCategoryDao.class); //操作用户dao
        arp.addMapping("gov_article","id", ArticleDao.class); //操作用户dao
        arp.addMapping("gov_friendship_link","id", FriendLinkDao.class); //操作友情链接dao
        arp.addMapping("gov_project", "id", ProjectDao.class);//公司项目
        arp.addMapping("gov_general", "id", GeneralDao.class);//公司概述
        arp.addMapping("gov_contact", "id", ContactDao.class);//联系信息
        arp.addMapping("gov_manpower", "id", ManpowerDao.class);//人力资源
    }

}
