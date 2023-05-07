package info.shahriarkhan.skilluction.dao;

import info.shahriarkhan.skilluction.AbstractTest;
import info.shahriarkhan.skilluction.model.Instructor;
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
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql", "file:src/test/resources/db/skilluction-db.sql"})
class InstructorDaoTest extends AbstractTest {

    @Autowired
    private InstructorDao instructorDao;

    @Test
    void testFindInstructorsByName() {
        List<Instructor> instructors = instructorDao.findInstructorsByName("instructor1FN");
        int expectedValue = 1;
        assertEquals(expectedValue, instructors.size());
    }

    @Test
    void testFindInstructorByEmail() {
        Instructor expectedInstructor = new Instructor();
        expectedInstructor.setInstructorId(1L);
        expectedInstructor.setFirstName("instructor1FN");
        expectedInstructor.setLastName("instructor1LN");
        expectedInstructor.setSummary("Experienced Instructor");
        Instructor instructor = instructorDao.findInstructorByEmail("instructorUser1@gmail.com");

        assertEquals(expectedInstructor, instructor);

    }

    @Test
    public void testFindInstructorByEmailDoseNotExist(){
        Instructor instructor = instructorDao.findInstructorByEmail("deyabakheet@gmail.com");
        assertNull(instructor);
    }
}