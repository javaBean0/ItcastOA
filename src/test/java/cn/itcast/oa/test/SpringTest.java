package cn.itcast.oa.test;

import cn.itcast.oa.service.TestService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    private ApplicationContext ac = new ClassPathXmlApplicationContext("config/spring/applicationContext.xml");



    @Test
    public void testTransaction(){
        TestService testService = (TestService) ac.getBean("testService");
        testService.saveTwoUser();
    }


}
