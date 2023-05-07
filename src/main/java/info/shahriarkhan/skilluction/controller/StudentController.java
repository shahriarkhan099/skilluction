package info.shahriarkhan.skilluction.controller;

import info.shahriarkhan.skilluction.model.Student;
import info.shahriarkhan.skilluction.model.User;
import info.shahriarkhan.skilluction.service.StudentService;
import info.shahriarkhan.skilluction.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

import static info.shahriarkhan.skilluction.constants.AppConstants.*;

@Controller
@RequestMapping(
        value = "/student"
)
public class StudentController {

    private StudentService studentService;

    private UserService userService;

    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping(
            value = "/list"
    )
    @PreAuthorize("hasAuthority('Admin')")
    public String studentList(Model model, @RequestParam(name = KEYWORD, defaultValue = "") String keyword){
        List<Student> students = studentService.findStudentsByName(keyword);
        model.addAttribute(LIST_STUDENTS, students);
        model.addAttribute(KEYWORD, keyword);

        return "student_views/students";
    }

    @GetMapping(
            value = "/delete"
    )
    @PreAuthorize("hasAuthority('Admin')")
    public String deleteStudent(Long studentId, String keyword){
        studentService.removeStudent(studentId);
        return "redirect:/student/list?keyword=" + keyword;
    }

    @GetMapping(
            value = "/update"
    )
    @PreAuthorize("hasAuthority('Student')")
    public String updateStudent(Model model, Principal principal){
        Student student = studentService.loadStudentByEmail(principal.getName());
        model.addAttribute(STUDENT, student);
        return "student_views/update";
    }

    @PostMapping(
            value = "/edit"
    )
    public String editStudent(Student student){
        studentService.updateStudent(student);
        return "redirect:/courses/list/student";
    }

    @GetMapping(
            value = "/create"
    )
    @PreAuthorize("hasAuthority('Admin')")
    public String createStudent(Model model){
        model.addAttribute(STUDENT, new Student());
        return "student_views/create";
    }

    @PostMapping(
            value = "/save"
    )
    @PreAuthorize("hasAuthority('Admin')")
    public String saveStudent(Student student, BindingResult bindingResult){
        User user = userService.loadUserByEmail(student.getUser().getEmail());
        if (user != null)
            bindingResult.rejectValue("user.email", null, "Student Email Already Exist");
        if (bindingResult.hasErrors()) return "student_views/create";
        studentService.createStudent(
                student.getFirstName(),
                student.getLastName(),
                student.getLevel(),
                student.getUser().getEmail(),
                student.getUser().getPassword()
        );

        return "redirect:/student/list";
    }


}
