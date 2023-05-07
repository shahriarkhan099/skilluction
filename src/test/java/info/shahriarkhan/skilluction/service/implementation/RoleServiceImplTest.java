package info.shahriarkhan.skilluction.service.implementation;

import info.shahriarkhan.skilluction.dao.RoleDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void testLoadRoleByName() {
        roleService.loadRoleByName("Admin");
        verify(roleDao).findByName(any());
    }

    @Test
    void testCreateRole() {
        roleService.createRole("Admin");
        verify(roleDao, times(1)).save(any());
    }
}