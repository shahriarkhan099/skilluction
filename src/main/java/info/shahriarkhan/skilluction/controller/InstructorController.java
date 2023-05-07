package info.shahriarkhan.skilluction.controller;


import info.shahriarkhan.skilluction.model.Instructor;
import info.shahriarkhan.skilluction.model.User;
import info.shahriarkhan.skilluction.service.InstructorService;
import info.shahriarkhan.skilluction.service.UserService;
import info.shahriarkhan.skilluction.constants.AppConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(
        value = "/instructor"
)
public class InstructorController {

    private InstructorService instructorService;

    private UserService userService;

    public InstructorController(InstructorService instructorService, UserService userService) {
        this.instructorService = instructorService;
        this.userService = userService;
    }

    @GetMapping(
            value = "/list"
    )
    @PreAuthorize("hasAuthority('Admin')")
    public String instructorsList(Model model, @RequestParam(name = AppConstants.KEYWORD, defaultValue = "") String keyword) {
        List<Instructor> instructors = instructorService.findInstructorByName(keyword);
        model.addAttribute(AppConstants.LIST_INSTRUCTORS, instructors);
        model.addAttribute(AppConstants.KEYWORD, keyword);
        return "instructor_views/instructor";
    }

    @GetMapping(
            value = "/delete"
    )
    @PreAuthorize("hasAuthority('Admin')")
    public String deleteInstructor(Long instructorId, String keyword) {
        instructorService.deleteInstructor(instructorId);
        return "redirect:/instructor/list";
    }

    @GetMapping(
            value = "/update"
    )
    @PreAuthorize("hasAuthority('Instructor')")
    public String updateInstructor(Model model, Long instructorId, Principal principal) {
        Instructor instructor = instructorService.loadInstructorByEmail(principal.getName());
        model.addAttribute(AppConstants.INSTRUCTOR, instructor);
        return "instructor_views/update";
    }

    @PostMapping(
            value = "/edit"
    )
    @PreAuthorize("hasAuthority('Instructor')")
    public String editInstructor(Instructor instructor) {
        instructorService.updateInstructor(instructor);
        return "redirect:/courses/list/instructor";
    }

    @GetMapping(
            value = "/create"
    )
    @PreAuthorize("hasAuthority('Admin')")
    public String createInstructor(Model model) {
        model.addAttribute(AppConstants.INSTRUCTOR, new Instructor());
        return "instructor_views/create";
    }

    @PostMapping(
            value = "/save"
    )
    @PreAuthorize("hasAuthority('Admin')")
    public String saveInstructor(@Validated Instructor instructor, BindingResult bindingResult) {
        User user = userService.loadUserByEmail(instructor.getUser().getEmail());
        if (user != null) {
            bindingResult.rejectValue("user.email", null, "Instructor Email Already Exist");
        }
        if (bindingResult.hasErrors()) {
            return "instructor_views/create";
        }

        instructorService.createInstructor(
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getSummary(),
                instructor.getUser().getEmail(),
                instructor.getUser().getPassword()
        );
        return "redirect:/instructor/list";
    }


}
