package cn.itcast.oa.service.impl;

import cn.itcast.oa.base.impl.BaseDaoImpl;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

    //登录方法
    @Override
    public User getByLoginnameAndPassword(String loginName, String password) {
        return (User) getSession().createQuery(
                "from User u where u.loginName = ? and u.password = ?")
                .setParameter(0, loginName)
                .setParameter(1, DigestUtils.md5DigestAsHex(password.getBytes()))
                .uniqueResult();

    }
}
