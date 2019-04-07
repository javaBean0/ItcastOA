package cn.itcast.oa.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    public String test(){
        return "success";
    }

}
