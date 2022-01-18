package com.mycode.example.repository;

import java.util.List;

import com.mycode.example.entity.Guardian;
import com.mycode.example.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest  // This will impact the database. so don't use it in normal code.
        //@DataJpaTest // This should be used for testing the Repository classes, it will flush the data after test execution. It will not impact the DB.
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudentTest() {
        Guardian guardian = new Guardian("Sakthi", "Sakthi@gmail.com", "9876543210");
        Student student = Student.builder().emailId("Malli2@gmail.com")
                .firstName("Mallikarjun")
                .lastName("Bandi")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printStudentsListTest() {
        List<Student> students = studentRepository.findAll();
        students.forEach(System.out::println);
    }

    @Test
    public void pintStudentByNameTest() {
        List<Student> students = studentRepository.findByFirstName("Malli");
        students.forEach(System.out::println);
    }

    @Test
    public void pintStudentByNameContainingTest() {
        List<Student> students = studentRepository.findByFirstNameContaining("Malli");
        students.forEach(System.out::println);
    }

    @Test
    public void pintStudentByGuardianNameTest() {
        List<Student> students = studentRepository.findByGuardianName("Sakthi");
        students.forEach(System.out::println);
    }

    @Test
    public void getStudentByEmailIdTest() {
        Student studentByEmailId = studentRepository.getStudentByEmailId("Malli@gmail.com");
        System.out.println(studentByEmailId);
    }

    @Test
    public void getStudentByEmailIdNativeTest() {
        Student student = studentRepository.getStudentByEmailIdNative("Malli@gmail.com");
        System.out.println(student);
    }

    @Test
    public void getStudentByEmailIdNativeNamedParamTest() {
        Student student = studentRepository.getStudentByEmailIdNativeNamedParam("Malli@gmail.com");
        System.out.println(student);
    }

    @Test
    public void updateStudentByEmailIdTest() {
        int status = studentRepository.updateStudentNameByEmailId("Arjun", "Malli@gmail.com");
        System.out.println("Update Status: " + status);
    }
}