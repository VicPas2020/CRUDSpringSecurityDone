package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleRepository;
import web.model.Role;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Autowired // можно и не указывать - кострукторы Autowired по умолчанию
    public RoleServiceImpl(RoleRepository roleDao) {
        this.roleRepository = roleDao;
    }

    @Override
    public Role findRoleByRoleName(String role) { return roleRepository.findRoleByRole(role);
    }
}
