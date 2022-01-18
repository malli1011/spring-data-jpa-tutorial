package com.mycode.example.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "Student",
        uniqueConstraints = @UniqueConstraint(name = "emailid_unique", columnNames = "email_id")
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id @Column(name = "student_id", nullable = false)
    @SequenceGenerator(
            name = "student_seq",
            sequenceName = "student_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_seq"
    )
    private Long studentId;
    private String firstName;
    private String lastName;
    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Embedded
    private Guardian guardian;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course_mapping",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            )
    )
    private List<Course> courses;

}
