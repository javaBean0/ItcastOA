package cn.itcast.oa.service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Department;

import java.util.List;

public interface DepartmentService extends BaseDao<Department> {

    List<Department> findTopList();

    List<Department> findChildren(Long parentId);
}
