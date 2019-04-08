package cn.itcast.oa.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Scope("prototype")
public class TestController {


    public String test(){
        return "success";
    }

    List list = new ArrayList();

}
