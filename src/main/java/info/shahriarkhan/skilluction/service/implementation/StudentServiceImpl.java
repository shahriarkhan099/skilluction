package info.shahriarkhan.skilluction.service.implementation;

import info.shahriarkhan.skilluction.dao.StudentDao;
import info.shahriarkhan.skilluction.model.Course;
import info.shahriarkhan.skilluction.model.Student;
import info.shahriarkhan.skilluction.model.User;
import info.shahriarkhan.skilluction.service.StudentService;
import info.shahriarkhan.skilluction.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    private UserService userService;

    public StudentServiceImpl(StudentDao studentDao, UserService userService) {
        this.studentDao = studentDao;
        this.userService = userService;
    }

    @Override
    public Student loadStudentById(Long studentId) {
        return studentDao.findById(studentId).orElseThrow(
                ()-> new EntityNotFoundException("Student with ID " + studentId + " NOT FOUND")
        );
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return studentDao.findStudentByName(name);
    }

    @Override
    public Student loadStudentByEmail(String email) {
        return studentDao.findStudentByEmail(email);
    }

    @Override
    public Student createStudent(String firstNme, String lastName, String level, String email, String password) {
        User user =
                userService.createUser(email, password);
        userService.assignRoleToUser(email, "Student");
        return studentDao.save(new Student(firstNme, lastName, level, user));
    }

    @Override
    public Student updateStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    public List<Student> fetchStudents() {
        return studentDao.findAll();
    }

    @Override
    public void removeStudent(Long studentId) {
        Student student =
                loadStudentById(studentId);
        Iterator<Course> courseIterator =
                student.getCourses().iterator();
        if (courseIterator.hasNext()){
            Course course = courseIterator.next();
            course.removeStudentFromCourse(student);
        }
        studentDao.deleteById(studentId);
    }
}
