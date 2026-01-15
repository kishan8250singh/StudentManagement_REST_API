package com.springboot.demoSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "users")
public class StudentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(unique = false)
    private String username;

    private String email;
    private int attendance ;
    @ElementCollection
    @CollectionTable(
            name = "student_marks",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @MapKeyColumn(name = "subject")
    @Column(name = "score")
    private Map<String, Integer> marks;

    // optional: password, role
    private String password;
    private String role; // e.g. "ROLE_USER", "ROLE_ADMIN"


}
