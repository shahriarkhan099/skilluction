package info.shahriarkhan.skilluction.service.implementation;

import info.shahriarkhan.skilluction.dao.StudentDao;
import info.shahriarkhan.skilluction.model.Course;
import info.shahriarkhan.skilluction.model.Student;
import info.shahriarkhan.skilluction.model.User;
import info.shahriarkhan.skilluction.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private StudentDao studentDao;

    @Mock
    private UserService userService;


    @Test
    void testLoadStudentById() {
        Student student = new Student();
        student.setStudentId(1L);

        when(studentDao.findById(any())).thenReturn(Optional.of(student));

        Student actualStudent = studentService.loadStudentById(1L);

        assertEquals(student, actualStudent);

    }

    @Test
    void testExceptionForNotFoundUserById(){
        assertThrows(EntityNotFoundException.class, ()-> studentService.loadStudentById(any()));
    }

    @Test
    void testFindStudentsByName() {
        String name = "testName";
        studentService.findStudentsByName(name);
        verify(studentDao).findStudentByName(name);
    }

    @Test
    void testLoadStudentByEmail() {
        String email = "test@gmail.com";
        studentService.loadStudentByEmail(email);
        verify(studentDao).findStudentByEmail(email);

    }

    @Test
    void testCreateStudent() {
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("password");

        when(userService.createUser(any(), any())).thenReturn(user);
        studentService.createStudent("firstName", "lastName", "level", "user@gmail.com", "password");
        verify(studentDao).save(any());

    }

    @Test
    void testUpdateStudent() {
        Student student =
                new Student();
        student.setStudentId(1L);
        studentService.updateStudent(student);
        verify(studentDao).save(student);

    }

    @Test
    void testFetchStudents() {
        studentService.fetchStudents();
        verify(studentDao, times(1)).findAll();
    }

    @Test
    void testRemoveStudent() {
        Student student =
                new Student();
        Course course =
                new Course();
        student.setStudentId(1L);
        course.setCourseId(1L);

        when(studentDao.findById(any())).thenReturn(Optional.of(student));
        studentService.removeStudent(1L);
        verify(studentDao).deleteById(any());
    }
}