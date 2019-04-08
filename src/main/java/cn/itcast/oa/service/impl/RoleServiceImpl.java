package cn.itcast.oa.service.impl;

import cn.itcast.oa.dao.impl.RoleDaoImpl;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDaoImpl roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }
}
