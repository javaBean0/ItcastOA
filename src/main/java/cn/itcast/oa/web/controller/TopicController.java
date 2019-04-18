package cn.itcast.oa.web.controller;

import cn.itcast.oa.base.BaseController;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

/**
 * Created with IDEA
 * author:bigStone
 * Date:2019/4/17
 **/
@Controller
@Scope("prototype")
public class TopicController extends BaseController<Topic> {

    private Long forumId;

    /**显示单个主题（主贴 + 回帖列表）*/
    public String show() throws Exception {

        // 准备数据：topic
        Topic topic = topicService.getById(model.getId());
        ActionContext.getContext().put("topic", topic);

        // 准备数据：replyList
        List<Reply> replyList = replyService.findByTopic(topic);
        ActionContext.getContext().put("replyList", replyList);

        return "show";
    }

    /**发表新主题页面 */
    public String addUI() throws Exception {
        //准备数据： forum
        Forum forum = forumService.getById(forumId);
        ActionContext.getContext().put("forum", forum);
        return "addUI";
    }

    /**发表新主题*/
    public String add() throws Exception {

        //封装
        // >> 表单中的字段，已经封装了title，content, faceIcon
        model.setForum(forumService.getById(forumId));

        // >>可以直接获取的信息
        model.setAuthor(getCurrentUser());//作者，当前登录的用户
        model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());//IP地址，当前请求的IP地址
        model.setPostTime(new Date());//发表时间，当前时间

        // >> 应放到业务方法的一个其他设置
        topicService.saveTopic(model);

        return "toShow";
    }

    public Long getForumId() {
        return forumId;
    }

    public void setForumId(Long forumId) {
        this.forumId = forumId;
    }
}
