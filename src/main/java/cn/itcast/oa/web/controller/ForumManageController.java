package cn.itcast.oa.web.controller;

import cn.itcast.oa.base.BaseController;
import cn.itcast.oa.domain.Forum;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class ForumManageController extends BaseController<Forum> {

    /**
     * 列表
     */
    public String list() throws Exception {
        List<Forum> forumList = forumService.findAll();
        ActionContext.getContext().put("forumList", forumList);
        ActionContext.getContext().getValueStack().set("forumList", forumList);
        return "list";
    }

    /**
     * 删除
     */
    public String delete() throws Exception {
        forumService.delete(model.getId());
        return "toList";
    }

    /**
     * 添加页面
     */
    public String addUI() throws Exception {
        forumService.saveForum(model);
        return "saveUI";
    }

    /**
     * 添加
     */
    public String add() throws Exception {
        forumService.save(model);
        return "toList";
    }

    /**
     * 修改页面
     */
    public String editUI() throws Exception {
        Forum forum = forumService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(forum);
        return "saveUI";
    }

    /**
     * 修改
     */
    public String edit() throws Exception {
        //从数据库中查出原有对象
        Forum forum = forumService.getById(model.getId());
        //设置要修改的属性
        forum.setName(model.getName());
        forum.setDescription(model.getDescription());
        //更新到数据库中
        forumService.update(forum);
        return "toList";
    }

    /**
     * 上移
     */
    public String moveUp() throws Exception {
        forumService.moveUp(model.getId());
        return "toList";
    }

    /**
     * 下移
     */
    public String moveDown() throws Exception {
        forumService.moveDown(model.getId());
        return "toList";
    }

}
