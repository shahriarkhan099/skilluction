package info.shahriarkhan.skilluction.service;

import info.shahriarkhan.skilluction.model.User;

public interface UserService {

    User loadUserByEmail(String email);

    User createUser(String email, String password);

    void assignRoleToUser(String email, String roleName);

    boolean doseCurrentUserHasRole(String roleName);
}
