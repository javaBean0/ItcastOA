package cn.itcast.oa.web.controller;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class HomeController extends ActionSupport {


    public String index() throws Exception {
        return "index";
    }

    public String top() throws Exception {
        return "top";
    }

    public String right() throws Exception {
        return "right";
    }

    public String bottom() throws Exception {
        return "bottom";
    }

    public String left() throws Exception {
        return "left";
    }

}
