package cn.itcast.oa.service;

import cn.itcast.oa.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void delete(Long id);
}
