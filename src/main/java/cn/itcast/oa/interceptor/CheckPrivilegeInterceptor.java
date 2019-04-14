package cn.itcast.oa.interceptor;

import cn.itcast.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        //从session中获取当前用户对象
        User user = (User) ActionContext.getContext().getSession().get("user");

        //获取当前访问的URL,并且去掉当前应用程序的前缀
        String privilegeUrl = null;

        String namespace = invocation.getProxy().getNamespace();
        String actionName = invocation.getProxy().getActionName();
        if (namespace.endsWith("/")) {
            privilegeUrl = namespace + actionName;
        } else {
            privilegeUrl = namespace + "/" + actionName;
        }
        if (privilegeUrl.startsWith("/")) {
            privilegeUrl = privilegeUrl.substring(1);
        }

        //如果没有登录，
        if (user == null) {
            //如果是登录路径，放行
            if (privilegeUrl.startsWith("userAction_login")) {
                return invocation.invoke();
            } else {
                //如果不是登录路径， 返回到登录页面
                return "loginUI";
            }

        } else {
            //如果已经登录
            if (user.hasPrivilegeByUrl(privilegeUrl)) {
                //如果有权限，放行
                return invocation.invoke();
            } else {
                //没有权限，返回提示页面
                return "noPrivilegeError";
            }
        }

    }

}

