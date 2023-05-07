package info.shahriarkhan.skilluction.service;

import info.shahriarkhan.skilluction.model.Role;

public interface RoleService {

    Role loadRoleByName(String name);

    Role createRole(String name);


}
