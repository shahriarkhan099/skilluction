package info.shahriarkhan.skilluction.service.implementation;

import info.shahriarkhan.skilluction.model.Course;
import info.shahriarkhan.skilluction.model.Instructor;
import info.shahriarkhan.skilluction.model.Student;
import info.shahriarkhan.skilluction.dao.CourseDao;
import info.shahriarkhan.skilluction.dao.InstructorDao;
import info.shahriarkhan.skilluction.dao.StudentDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseDao courseDao;

    @Mock
    private InstructorDao instructorDao;

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private CourseServiceImpl courseService;
    @Test
    void testLoadCourseById() {
        Course course = new Course();
        course.setCourseId(1L);

        when(courseDao.findById(any())).thenReturn(Optional.of(course));

        Course actualCourse = courseService.loadCourseById(1L);

        assertEquals(course, actualCourse);

    }

    @Test
    void testExceptionForNotFoundCourseById(){
        assertThrows(EntityNotFoundException.class, ()->courseService.loadCourseById(2L));
    }

    @Test
    void testCreateCourse() {
        Instructor instructor =
                new Instructor();
        instructor.setInstructorId(1L);

        when(instructorDao.findById(any())).thenReturn(Optional.of(instructor));

        courseService.createCourse(
                "JPA",
                "1H 30min",
                "Hello JPA",
                1L
        );
        verify(courseDao).save(any());
    }

    @Test
    void testCreateOrUpdateCourse() {
        Course course =
                new Course();

        course.setCourseId(1L);

        courseService.createOrUpdateCourse(course);

        ArgumentCaptor<Course> argumentCaptor = ArgumentCaptor.forClass(Course.class);

        verify(courseDao).save(argumentCaptor.capture());

        Course capturedValue = argumentCaptor.getValue();

        assertEquals(course, capturedValue);

    }

    @Test
    void testFindCoursesByCourseName() {

        String courseName = "JPA";
        courseService.findCoursesByCourseName(courseName);
        verify(courseDao).findCoursesByCourseNameContains(courseName);

    }

    @Test
    void testAssignStudentToCourse() {
        Student student =
                new Student();
        student.setStudentId(2L);

        Course course =
                new Course();
        course.setCourseId(1L);

        when(studentDao.findById(any())).thenReturn(Optional.of(student));
        when(courseDao.findById(any())).thenReturn(Optional.of(course));

        courseService.assignStudentToCourse(1L, 2L);
    }

    @Test
    void testFetchAllCourses() {
        courseService.fetchAllCourses();
        verify(courseDao).findAll();
    }

    @Test
    void testFetchCoursesForStudent() {
        courseService.fetchCoursesForStudent(1L);
        verify(courseDao).getCourseByStudentId(1L);
    }

    @Test
    void testRemoveCourse() {
        courseService.removeCourse(1L);
        verify(courseDao).deleteById(1L);
    }
}