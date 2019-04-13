package cn.itcast.oa.web.controller;

import cn.itcast.oa.base.BaseController;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.Role;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.List;

@Controller
@Scope("prototype")
public class RoleController extends BaseController<Role> {

    private Long[] privilegeIds;

    /**
     * 列表
     *
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        List<Role> roleList = roleService.findAll();
        ActionContext.getContext().put("roleList", roleList);
        return "list";
    }

    /**
     * 删除
     *
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        roleService.delete(model.getId());
        return "toList";
    }

    /**
     * 添加页面
     */
    public String addUI() throws Exception {
        return "saveUI";
    }

    /**
     * 添加
     */
    public String add() throws Exception {
        roleService.save(model);
        return "toList";
    }

    /**
     * 修改页面
     */
    public String editUI() throws Exception {
        Role role = roleService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(role);
        return "saveUI";
    }

    /**
     * 修改
     */
    public String edit() throws Exception {
        Role role = roleService.getById(model.getId());
        BeanUtils.copyProperties(model, role);
        roleService.update(role);
        return "toList";
    }

    /**
     * 设置权限页面
     */
    public String setPrivilegeUI() throws Exception {
        //准备数据
        Role role = roleService.getById(model.getId());
        ActionContext.getContext().put("role", role);
        List<Privilege> topPrivilegeList = privilegeService.findTopList();
        ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);
        //准备回显数据
        privilegeIds = new Long[role.getPrivileges().size()];
        int index = 0;
        for (Privilege privilege : role.getPrivileges()) {
            privilegeIds[index++] = privilege.getId();
        }
        return "setPrivilegeUI";
    }

    /**
     * 设置权限
     */
    public String setPrivilege() throws Exception {
        //从数据库中取出原对象
        Role role = roleService.getById(model.getId());
        if (privilegeIds != null) {
            List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
            role.setPrivileges(new HashSet<>(privilegeList));
        }
        roleService.update(role);
        return "toList";
    }

//-----------------------------------------------------

    public Long[] getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(Long[] privilegeIds) {
        this.privilegeIds = privilegeIds;
    }
}