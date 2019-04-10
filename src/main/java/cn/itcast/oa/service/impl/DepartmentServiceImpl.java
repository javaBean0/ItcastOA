package cn.itcast.oa.service.impl;

import cn.itcast.oa.base.impl.BaseDaoImpl;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService {


}
