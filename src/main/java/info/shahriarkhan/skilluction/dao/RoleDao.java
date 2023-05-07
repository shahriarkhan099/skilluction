package info.shahriarkhan.skilluction.dao;

import info.shahriarkhan.skilluction.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
