package cn.itcast.oa.web.controller;

import cn.itcast.oa.base.BaseController;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.utils.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;

import java.util.HashSet;
import java.util.List;

@Controller
@Scope("prototype")
public class UserController extends BaseController<User> {

    private Long departmentId;
    private Long[] roleIds;

    /**
     * 列表
     */
    public String list() throws Exception {
        List<User> userList = userService.findAll();
        for (User user : userList) {
            if ("admin".equals(user.getLoginName())) {
                userList.remove(user);
                break;
            }
        }
        ActionContext.getContext().put("userList", userList);
        return "list";
    }

    /**
     * 添加页面
     */
    public String addUI() throws Exception {
        //准备数据，departmentList
        List<Department> topList = departmentService.findTopList();
        List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
        ActionContext.getContext().put("departmentList", departmentList);
        //准备数据， roleList
        List<Role> roleList = roleService.findAll();
        ActionContext.getContext().put("roleList", roleList);
        return "saveUI";
    }

    /**
     * 添加
     */
    public String add() throws Exception {
        //新建对象，并设置属性（model也可以）
        model.setDepartment(departmentService.getById(departmentId));
        List<Role> roleList = roleService.getByIds(roleIds);
        model.setRoles(new HashSet<Role>(roleList));
        model.setPassword(DigestUtils.md5DigestAsHex("1234".getBytes()));
        userService.save(model);
        return "toList";

    }

    /**
     * 编辑页面
     */
    public String editUI() throws Exception {
        //准备数据，departmentList
        List<Department> topList = departmentService.findTopList();
        List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
        ActionContext.getContext().put("departmentList", departmentList);
        //准备数据， roleList
        List<Role> roleList = roleService.findAll();
        ActionContext.getContext().put("roleList", roleList);
        User user = userService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(user);
        if (user.getDepartment() != null) {
            departmentId = user.getDepartment().getId();
        }
        return "saveUI";
    }

    /**
     * 编辑
     */
    public String edit() throws Exception {
        User user = userService.getById(model.getId());
        BeanUtils.copyProperties(model, user);
        user.setDepartment(departmentService.getById(departmentId));
        user.setRoles(new HashSet<Role>(roleService.getByIds(roleIds)));
        userService.update(user);
        return "toList";
    }

    /**
     * 删除
     */
    public String delete() throws Exception {
        userService.delete(model.getId());
        return "toList";
    }

    /**
     * 登录页面
     */
    public String loginUI() throws Exception {
        return "loginUI";
    }

    /**
     * 登录
     */
    public String login() throws Exception {
        //如果直接通过url访问
        if (model.getLoginName() == null) {
            addFieldError("login", "您还没有登录，请登录！");
            return "loginUI";
        }
        User user = userService.getByLoginnameAndPassword(model.getLoginName(), model.getPassword());
        if (user == null) {
            addFieldError("login", "用户名或密码不正确");
            return "loginUI";
        }
        //登录成功，保存用户信息到session中
        ActionContext.getContext().getSession().put("user", user);
        return "toIndex";
    }

    /**
     * 注销
     */
    public String logout() throws Exception {
        ActionContext.getContext().getSession().remove("user");
        return "logout";
    }

    /**
     * 初始化密码
     */
    public String initPassword() throws Exception {
        User user = userService.getById(model.getId());
        user.setPassword(DigestUtils.md5DigestAsHex("1234".getBytes()));
        userService.update(user);
        return "toList";
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }
}
