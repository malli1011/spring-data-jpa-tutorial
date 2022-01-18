package com.mycode.example.repository;

import javax.transaction.Transactional;
import java.util.List;

import com.mycode.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String firstName);

    List<Student> findByGuardianName(String firstName);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    //we can use @Query to define the JPQL, if we cannot achieve the result using method names like above.
    // Here we use the entity instead of DB table.
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailId(String emailId);

    //Use native sql query
    @Query(value = "select * from student s where s.email_id=?1",
            nativeQuery = true)
    Student getStudentByEmailIdNative(String emailId);

    //Use native sql query and named parameters.
    @Query(value = "select * from student s where s.email_id=:emailId",
            nativeQuery = true)
    Student getStudentByEmailIdNativeNamedParam(@Param("emailId") String emailId);

    @Modifying
    @Transactional // In a normal app we add this at service layer and make multiple DB update calls.
    @Query(value = "update student set first_name=?1 where email_id=?2",
            nativeQuery = true)
    int updateStudentNameByEmailId(String firstName,String emailId);

}
