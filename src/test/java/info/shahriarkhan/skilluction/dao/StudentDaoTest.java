package info.shahriarkhan.skilluction.dao;

import info.shahriarkhan.skilluction.AbstractTest;
import info.shahriarkhan.skilluction.model.Student;
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
class StudentDaoTest extends AbstractTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    void testFindStudentByName() {
        List<Student> students = studentDao.findStudentByName("std2LN");
        assertEquals(1, students.size());
    }

    @Test
    void testFindStudentByEmail() {
        Student expectedStudent = new Student();
        expectedStudent.setStudentId(1L);
        expectedStudent.setFirstName("std1FN");
        expectedStudent.setLastName("std1LN");
        expectedStudent.setLevel("beginner");
        Student student = studentDao.findStudentByEmail("stdUser1@gmail.com");
        assertEquals(expectedStudent,student);
    }

}