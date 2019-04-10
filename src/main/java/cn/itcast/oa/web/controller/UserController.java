package cn.itcast.oa.web.controller;

import cn.itcast.oa.base.BaseController;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
        ActionContext.getContext().put("userList", userList);
        return "list";
    }

    /**
     * 添加页面
     */
    public String addUI() throws Exception {
        //准备数据，departmentList
        //TODO 应该是显示树状结构， 先使用所有部门列表代替
        List<Department> departmentList = departmentService.findAll();
        ActionContext.getContext().put("departmentList", departmentList);
        //准备数据， roleList
        List<Role> roleList = roleService.findAll();
        ActionContext.getContext().put("roleList", roleList);
        return "addUI";
    }

    /**
     * 添加
     */
    public String add() throws Exception {
        //新建对象，并设置属性（model也可以）
        model.setDepartment(departmentService.getById(departmentId));
        List<Role> roleList = roleService.getByIds(roleIds);
        model.setRoles(new HashSet<Role>(roleList));
        userService.save(model);
        return "toList";

    }

    /**
     * 编辑页面
     */
    public String editUI() throws Exception {
        return "editUI";
    }

    /**
     * 编辑
     */
    public String edit() throws Exception {
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
     * 初始化密码
     */
    public String initPassword() throws Exception {
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
