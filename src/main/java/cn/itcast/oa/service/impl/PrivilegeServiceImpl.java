package cn.itcast.oa.service.impl;

import cn.itcast.oa.base.impl.BaseDaoImpl;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {

    @Override
    public List<Privilege> findTopList() {
        return getSession().createQuery("FROM Privilege p WHERE p.parent IS NULL").list();

    }

    @Override
    public List<String> findAllPrivilegeUrls() {
        return getSession().createQuery("select distinct p.url from Privilege p where p.url is not null").list();
    }

}
