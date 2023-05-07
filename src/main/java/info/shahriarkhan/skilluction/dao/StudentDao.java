package info.shahriarkhan.skilluction.dao;

import info.shahriarkhan.skilluction.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, Long> {

    @Query(
            value = "select s from Student as s where s.firstName like %:name% or s.lastName like %:name%"
    )
    List<Student> findStudentByName(@Param("name") String name);

    @Query(
            value = "select s from Student as s where s.user.email=:email"
    )
    Student findStudentByEmail(@Param("email") String email);


}
