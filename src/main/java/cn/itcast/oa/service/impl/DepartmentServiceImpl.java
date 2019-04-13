package cn.itcast.oa.service.impl;

import cn.itcast.oa.base.impl.BaseDaoImpl;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService {

    @Override
    public List<Department> findTopList() {
        return getSession().createQuery("FROM Department d WHERE d.parent IS NULL").list();
    }

    @Override
    public List<Department> findChildren(Long parentId) {
        return getSession().createQuery("FROM Department d WHERE d.parent.id = ?")
                .setParameter(0, parentId).list();
    }
}
