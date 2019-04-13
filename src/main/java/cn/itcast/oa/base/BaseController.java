package cn.itcast.oa.base;

import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.PrivilegeService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.UserService;
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
}
