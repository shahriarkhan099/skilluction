package info.shahriarkhan.skilluction.service.implementation;

import info.shahriarkhan.skilluction.dao.RoleDao;
import info.shahriarkhan.skilluction.dao.UserDao;
import info.shahriarkhan.skilluction.model.Role;
import info.shahriarkhan.skilluction.model.User;
import info.shahriarkhan.skilluction.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleDao roleDao;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User createUser(String email, String password) {
        User user = loadUserByEmail(email);
        if (user != null) throw new RuntimeException("User with email : " + email + " aleady exist");
        String encodedPassword = passwordEncoder.encode(password);
        return userDao.save(new User(email, encodedPassword));
    }

    @Override
    public void assignRoleToUser(String email, String roleName) {
        User user =
                loadUserByEmail(email);
        Role role =
                roleDao.findByName(roleName);
        user.assignRoleToUser(role);
    }

    @Override
    public boolean doseCurrentUserHasRole(String roleName) {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(
                        grantedAuthority -> grantedAuthority
                                .getAuthority()
                                .equals(roleName)
                );

    }
}
