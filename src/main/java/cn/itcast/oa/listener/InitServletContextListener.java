package cn.itcast.oa.listener;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;
import com.opensymphony.xwork2.ActionContext;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class InitServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();

        //得到Service的实例对象
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
        PrivilegeService privilegeService = (PrivilegeService) applicationContext.getBean("privilegeServiceImpl");
        //准备所有顶级权限的集合（顶级菜单）
        List<Privilege> topPrivilegeList = privilegeService.findTopList();
        application.setAttribute("topPrivilegeList", topPrivilegeList);
        List<String> allPrivilegeUrls = privilegeService.findAllPrivilegeUrls();
        application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
    }


    public void contextDestroyed(ServletContextEvent sce) {

    }


















}
