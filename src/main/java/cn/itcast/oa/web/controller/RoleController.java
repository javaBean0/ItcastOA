package cn.itcast.oa.web.controller;

import cn.itcast.oa.base.BaseController;
import cn.itcast.oa.domain.Role;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class RoleController extends BaseController<Role> {


    /**
     * 列表
     * @return
     * @throws Exception
     */
    public String list() throws Exception{
        List<Role> roleList = roleService.findAll();
        ActionContext.getContext().put("roleList", roleList);
        return "list";
    }

    /**
     * 删除
     * @return
     * @throws Exception
     */
    public String delete() throws Exception{
        roleService.delete(model.getId());
        return "toList";
    }

    /**添加页面*/
    public String addUI() throws Exception{
        return "addUI";
    }

    /**添加*/
    public String add() throws Exception{
        roleService.save(model);
        return "toList";
    }

    /**修改页面*/
    public String editUI() throws Exception{
        Role role = roleService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(role);
        return "editUI";
    }

    /**修改*/
    public String edit() throws Exception{
        Role role = roleService.getById(model.getId());
        System.out.println("role : " + role.getName() + " :  " + role.getDescription());
        System.out.println("model : " + model.getName() +  ": " + model.getDescription());
        BeanUtils.copyProperties(model, role);
        roleService.update(role);
        return "toList";
    }


}