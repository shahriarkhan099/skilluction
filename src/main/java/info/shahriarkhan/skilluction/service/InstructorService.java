package info.shahriarkhan.skilluction.service;

import info.shahriarkhan.skilluction.model.Instructor;

import java.util.List;

public interface InstructorService {

    Instructor loadInstructorById(Long instructorId);

    List<Instructor> findInstructorByName(String name);

    Instructor loadInstructorByEmail(String email);

    Instructor createInstructor(String firstName, String lastName, String summary, String email, String password);

    Instructor updateInstructor(Instructor instructor);

    List<Instructor> fetchInstructor();

    void deleteInstructor(Long instructorId);
}
