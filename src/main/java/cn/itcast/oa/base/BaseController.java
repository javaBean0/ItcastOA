package cn.itcast.oa.base;

import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;

public class BaseController<T> extends ActionSupport implements ModelDriven<T> {

    @Resource
    protected UserService userService;
    @Resource
    protected RoleService roleService;
    @Resource
    protected DepartmentService departmentService;
    @Resource
    protected PrivilegeService privilegeService;
    @Resource
    protected ForumService forumService;
    @Resource
    protected TopicService topicService;
    @Resource
    protected ReplyService replyService;


    protected T model;

    public BaseController(){
        try {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class clazz = (Class) pt.getActualTypeArguments()[0];
            model = (T)clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getModel() {
        return model;
    }

    protected User getCurrentUser(){
        return (User) ActionContext.getContext().getSession().get("user");
    }

}
