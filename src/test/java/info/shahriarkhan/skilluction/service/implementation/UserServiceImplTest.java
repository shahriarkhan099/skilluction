package info.shahriarkhan.skilluction.service.implementation;

import info.shahriarkhan.skilluction.model.Role;
import info.shahriarkhan.skilluction.model.User;
import info.shahriarkhan.skilluction.dao.RoleDao;
import info.shahriarkhan.skilluction.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDao userDao;
    @Mock
    private RoleDao roleDao;
    @Mock
    private User mockedUser;
    @InjectMocks
    private UserServiceImpl userService;
    @Test
    void testLoadUserByEmail() {
        userService.loadUserByEmail("user@gmail.com");
        verify(userDao, times(1)).findByEmail(any());
    }

    @Test
    void testCreateUser() {
        String email = "user@gmail.com";
        String password = "password";
        User user = new User(email, password);
        userService.createUser(email, password);
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userDao).save(argumentCaptor.capture());

        User actualUser = argumentCaptor.getValue();

        assertEquals(user, actualUser);
    }

    @Test
    void testAssignRoleToUser() {
        Role role = new Role();
        role.setRoleId(1L);
        when(userDao.findByEmail(any())).thenReturn(mockedUser);
        when(roleDao.findByName(any())).thenReturn(role);

        userService.assignRoleToUser("userRole@gmail.com", "password");

        verify(mockedUser, times(1)).assignRoleToUser(role);

    }
}