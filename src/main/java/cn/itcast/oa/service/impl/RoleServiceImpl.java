package cn.itcast.oa.service.impl;

import cn.itcast.oa.base.impl.BaseDaoImpl;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends BaseDaoImpl<Role> implements RoleService {


}
