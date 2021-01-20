package web.service;

import org.springframework.stereotype.Service;
import web.dao.RoleRepository;
import web.model.Role;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleDao;

    public RoleServiceImpl(RoleRepository roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role findRoleByRoleName(String role) { return roleDao.findRoleByRole(role);
    }
}
