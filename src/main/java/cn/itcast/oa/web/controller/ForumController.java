package cn.itcast.oa.web.controller;

import cn.itcast.oa.base.BaseController;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created with IDEA
 * author:bigStone
 * Date:2019/4/17
 * *
 */

@Controller
@Scope("prototype")
public class ForumController extends BaseController<Forum> {

    /**
     * 板块列表
     */
    public String list() throws Exception {
        List<Forum> forumList = forumService.findAll();
        ActionContext.getContext().put("forumList", forumList);
        return "list";
    }

    /**
     * 显示单个板块列表
     */
    public String show() throws Exception {
        // 准备数据：forum
        Forum forum = forumService.getById(model.getId());
        ActionContext.getContext().put("forum", forum);

        //准备数据topicList
        List<Topic> topicList = topicService.findByForum(forum);
        ActionContext.getContext().put("topicList", topicList);
        return "show";
    }

}
