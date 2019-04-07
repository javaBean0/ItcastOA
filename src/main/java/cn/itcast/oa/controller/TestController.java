package cn.itcast.oa.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("prototype")
public class TestController {


    public String test(){
        return "success";
    }

}
