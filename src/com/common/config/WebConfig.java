package com.common.config;


import com.controller.*;
import com.interceptor.ExceptionInterceptor;
import com.interceptor.SessionInterceptor;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.model.MappingKit;
import com.model.bean.Manpower;
import com.model.bean.Project;

/**
 * API引导式配置
 */
public class WebConfig extends JFinalConfig {
    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        // 加载少量必要配置，随后可用PropKit.get(...)获取值
        me.setEncoding("UTF-8");
        PropKit.use("a_little_config.txt");
        me.setDevMode(PropKit.getBoolean("devMode", false));
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add("/user", UserController.class, "/login");    // 第三个参数为该Controller的视图存放路径
        me.add("/articleCategory", ArticleCategoryController.class);
        me.add("/article", ArticleController.class);
        me.add("/project", ProjectController.class);
        me.add("/general", GeneralController.class);
        me.add("/link",FriendLinkController.class);
        me.add("/contact",ContactController.class);
        me.add("/manpower",ManpowerController.class);
        me.add("/",FrontController.class,"/front");
//        me.add("/blog", BlogController.class);			// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
//        me.add("/user", UserController.class,"/login");  //用户操作controller
//        me.add("/user", UserController.class,"/login");  //用户操作controller
    }

    public static C3p0Plugin createC3p0Plugin() {
        return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        // 配置C3p0数据库连接池插件
        C3p0Plugin C3p0Plugin = createC3p0Plugin();
        me.add(C3p0Plugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
        me.add(arp);
        arp.setShowSql(true);

        // 所有配置在 MappingKit 中搞定
        MappingKit.mapping(arp);
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {
        me.add(new ExceptionInterceptor());
//        me.add(new SessionInterceptor());
    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {

    }

    /**
     * 建议使用 JFinal 手册推荐的方式启动项目
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     */
    public static void main(String[] args) {
        JFinal.start("WebRoot", 8081, "/", 5);
    }


}
