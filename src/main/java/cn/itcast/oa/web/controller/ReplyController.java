package cn.itcast.oa.web.controller;

import cn.itcast.oa.base.BaseController;
import cn.itcast.oa.domain.Reply;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created with IDEA
 * author:bigStone
 * Date:2019/4/17
 **/

@Controller
@Scope("prototype")
public class ReplyController extends BaseController<Reply> {

    /**发表新回复页面 */
    public String addUI() throws Exception {

        return "addUI";
    }

    /**发表新回复*/
    public String add() throws Exception {

        return "toTopicShow";
    }

}
