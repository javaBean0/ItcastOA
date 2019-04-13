package cn.itcast.oa.web.controller;

import cn.itcast.oa.base.BaseController;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.utils.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class DepartmentController extends BaseController<Department> {

    private Long parentId;

    /**
     * 列表
     */
    public String list() throws Exception {


        List<Department> departmentList = null;
       // departmentList = departmentService.findAll();
        if(parentId == null){
                departmentList = departmentService.findTopList();
        }else{
            departmentList = departmentService.findChildren(parentId);
            Department parent = departmentService.getById(parentId);
            ActionContext.getContext().put("parent", parent);
        }
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
        List<Department> topList = departmentService.findTopList();
        List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
        ActionContext.getContext().put("departmentList", departmentList);
        return "saveUI";
    }

    /**
     * 添加
     */
    public String add() throws Exception {
        model.setParent(departmentService.getById(parentId));
        departmentService.save(model);
        return "toList";
    }

    /**
     * 编辑页面
     */
    public String editUI() throws Exception {
        List<Department> topList = departmentService.findTopList();
        List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
        ActionContext.getContext().put("departmentList", departmentList);
        Department department = departmentService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(department);
        if (department.getParent() != null) {
            parentId = department.getParent().getId();
        }
        return "saveUI";
    }

    /**
     * 编辑
     */
    public String edit() throws Exception {
        Department department = departmentService.getById(model.getId());
        BeanUtils.copyProperties(model, department);
        department.setParent(departmentService.getById(parentId));
        departmentService.update(department);
        return "toList";
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
