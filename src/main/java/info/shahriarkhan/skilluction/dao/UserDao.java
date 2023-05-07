package info.shahriarkhan.skilluction.dao;

import info.shahriarkhan.skilluction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
