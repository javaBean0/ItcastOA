package cn.itcast.oa.controller;

import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class RoleController extends ActionSupport implements ModelDriven<Role> {

    @Autowired
    private RoleService roleService;

    private Role model = new Role();

    @Override
    public Role getModel() {
        return model;
    }

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
        return "toList";
    }

    /**修改页面*/
    public String editUI() throws Exception{
        return "editUI";
    }

    /**修改*/
    public String edit() throws Exception{
        return "edit";
    }


}