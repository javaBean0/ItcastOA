package cn.itcast.oa.web.controller;

import cn.itcast.oa.base.BaseController;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * Created with IDEA
 * author:bigStone
 * Date:2019/4/17
 **/

@Controller
@Scope("prototype")
public class ReplyController extends BaseController<Reply> {

    private Long topicId;


    /**发表新回复页面 */
    public String addUI() throws Exception {
        //准备数据topic
        Topic topic = topicService.getById(topicId);
        ActionContext.getContext().put("topic", topic);
        return "addUI";
    }

    /**发表新回复*/
    public String add() throws Exception {
        //封装
        model.setAuthor(getCurrentUser());
        model.setTopic(topicService.getById(topicId));
        model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
        model.setPostTime(new Date());
        topicService.saveTopic(model);

        //保存



        return "toTopicShow";
    }




    //-------------------------

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}
