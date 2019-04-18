package cn.itcast.oa.test.testCopyProperties;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class testCopyProperties {


    @Test
    public void test1(){
        person p1 = new person();
        p1.setId(new Long(1));
        p1.setUsername("zhangsan");
        p1.setPassword("zhangsan");
        //p1.setAddr("南阳市");
        person p2 = new person();

        p2.setAddr("北京市");
        BeanUtils.copyProperties(p1, p2);
        System.out.println(p2);
    }
}
