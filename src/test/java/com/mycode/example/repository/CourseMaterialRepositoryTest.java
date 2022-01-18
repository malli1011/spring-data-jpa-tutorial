package com.mycode.example.repository;

import java.util.List;

import com.mycode.example.entity.Course;
import com.mycode.example.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterialTest() {
        Course course = Course.builder().courseTitle("DSA")
                .credit(6).build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .course(course)
                .url("www.google.com")
                .build();
        repository.save(courseMaterial);
    }

    @Test
    //@Transactional // Since course is courseMaterial is lazily loaded, we need to add the transaction.
    // or we can exclude the course from toString method of CourseMaterial using @ToString.Exclude on course variable
    public void printAllCoursesTest() {
        List<CourseMaterial> courseMaterials = repository.findAll();
        courseMaterials.forEach(System.out::println);
    }
}