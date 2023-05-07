package info.shahriarkhan.skilluction.dao;

import info.shahriarkhan.skilluction.model.Course;
import info.shahriarkhan.skilluction.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Sql(
        scripts = {"file:src/test/resources/db/clearAll.sql", "file:src/test/resources/db/skilluction-db.sql"}
)
class CourseDaoTest extends AbstractTest {

    @Autowired
    private CourseDao courseDao;

    @Test
    void testFindCoursesByCourseNameContains() {
        List<Course> courses = courseDao.findCoursesByCourseNameContains("Spring");
        int excpectedResult = 2;
        assertEquals(excpectedResult, courses.size());
    }

    @Test
    void testGetCourseByStudentId() {
        List<Course> courses = courseDao.getCourseByStudentId(1L);
        int expectedResult = 1;
        assertEquals(expectedResult, courses.size());

    }
}