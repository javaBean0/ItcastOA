package cn.itcast.oa.service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Privilege;

import java.util.List;

public interface PrivilegeService extends BaseDao<Privilege> {
    List<Privilege> findTopList();

    List<String> findAllPrivilegeUrls();
}
