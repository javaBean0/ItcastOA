package cn.itcast.oa.web.controller;

import cn.itcast.oa.base.BaseController;
import cn.itcast.oa.domain.Department;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class DepartmentController extends BaseController<Department> {

    /**
     * 列表
     */
    public String list() throws Exception {
        List<Department> departmentList = departmentService.findAll();
        ActionContext.getContext().put("departmentList", departmentList);
        return "list";
    }

    /**
     * 删除
     */
    public String delete() throws Exception {
        departmentService.delete(model.getId());
        return "toList";
    }

    /**
     * 添加页面
     */
    public String addUI() throws Exception {
        return "addUI";
    }

    /**
     * 添加
     */
    public String add() throws Exception {
        departmentService.save(model);
        return "toList";
    }

    /**
     * 编辑页面
     */
    public String editUI() throws Exception {
        Department department = departmentService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(department);
        return "editUI";
    }

    /**
     * 编辑
     */
    public String edit() throws Exception {
        Department department = departmentService.getById(model.getId());
        BeanUtils.copyProperties(model, department);
        departmentService.update(department);
        return "toList";
    }

}
