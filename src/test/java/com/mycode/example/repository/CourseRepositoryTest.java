package com.mycode.example.repository;

import java.util.List;

import com.mycode.example.entity.Course;
import com.mycode.example.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void getAllCoursesTest() {
        List<Course> courses = courseRepository.findAll();
        courses.forEach(System.out::println);

    }

    @Test
    public void saveCourseWithTeacherObjectTest() {
        Teacher teacher = Teacher.builder()
                .firstName("Sakthi")
                .lastName("Bandi")
                .build();

        Course course = Course.builder()
                .courseTitle("Kotlin")
                .credit(10)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void findAllWithPaginationTest() {
        Pageable firstPage = PageRequest.of(0, 2);
        Pageable secondPage = PageRequest.of(1, 2);
        List<Course> courses = courseRepository.findAll(firstPage).getContent();
        long totalElements = courseRepository.findAll(firstPage).getTotalElements();
        System.out.println("Courses : " + courses);
        System.out.println("Total Elements :" + totalElements);
        List<Course> courses2 = courseRepository.findAll(secondPage).getContent();
        long totalElements2 = courseRepository.findAll(secondPage).getTotalElements();
        System.out.println("Courses 2: " + courses2);
        System.out.println("Total Elements 2 :" + totalElements2);

    }

}