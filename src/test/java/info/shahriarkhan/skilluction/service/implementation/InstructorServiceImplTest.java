package info.shahriarkhan.skilluction.service.implementation;

import info.shahriarkhan.skilluction.dao.InstructorDao;
import info.shahriarkhan.skilluction.model.Course;
import info.shahriarkhan.skilluction.model.Instructor;
import info.shahriarkhan.skilluction.model.User;
import info.shahriarkhan.skilluction.service.CourseService;
import info.shahriarkhan.skilluction.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceImplTest {

    @Mock
    private InstructorDao instructorDao;
    @Mock
    private UserService userService;
    @Mock
    private CourseService courseService;
    @InjectMocks
    private InstructorServiceImpl instructorService;
    @Test
    void testLoadInstructorById() {
        Instructor instructor =
                new Instructor();
        instructor.setInstructorId(1L);

        when(instructorDao.findById(1L)).thenReturn(Optional.of(instructor));
        Instructor actualInstructor = instructorService.loadInstructorById(1L);

        assertEquals(instructor, actualInstructor);
    }

    @Test
    void testFindInstructorByName() {
        String instructorName = "instructorFN";
        instructorService.findInstructorByName(instructorName);
        verify(instructorDao).findInstructorsByName(instructorName);
    }

    @Test
    void testLoadInstructorByEmail() {
        String instructorEmail = "instructor@gmail.com";
        instructorService.loadInstructorByEmail(instructorEmail);
        verify(instructorDao).findInstructorByEmail(instructorEmail);
    }

    @Test
    void testCreateInstructor() {
        User user =
                new User(
                        "user@gmail.com",
                        "password"
                );

        when(userService.createUser(any(),any())).thenReturn(user);
        instructorService.createInstructor(
                "firstName",
                "lastName",
                "summary",
                "user@gmail.com",
                "password"
        );
        verify(instructorDao).save(any());

    }

    @Test
    void testUpdateInstructor() {
        Instructor instructor =
                new Instructor();
        instructor.setInstructorId(1L);

        instructorService.updateInstructor(instructor);
        verify(instructorDao).save(instructor);

    }

    @Test
    void testFetchInstructor() {
        instructorService.fetchInstructor();
        verify(instructorDao).findAll();
    }

    @Test
    void testDeleteInstructor() {
        Instructor instructor =
                new Instructor();
        instructor.setInstructorId(1L);

        Course course =
                new Course();
        course.setCourseId(1L);

        instructor.getCourses().add(course);

        when(instructorDao.findById(1L)).thenReturn(Optional.of(instructor));

        instructorService.deleteInstructor(1L);

        verify(courseService, times(1)).removeCourse(any());
        verify(instructorDao).deleteById(any());
    }
}