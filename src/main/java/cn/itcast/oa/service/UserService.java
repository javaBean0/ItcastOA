package cn.itcast.oa.service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.User;

public interface UserService extends BaseDao<User> {
    User getByLoginnameAndPassword(String loginName, String password);
}
