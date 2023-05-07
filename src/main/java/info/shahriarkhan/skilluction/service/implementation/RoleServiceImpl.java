package info.shahriarkhan.skilluction.service.implementation;

import info.shahriarkhan.skilluction.dao.RoleDao;
import info.shahriarkhan.skilluction.model.Role;
import info.shahriarkhan.skilluction.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role loadRoleByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public Role createRole(String name) {
        return roleDao.save(new Role(name));
    }
}
