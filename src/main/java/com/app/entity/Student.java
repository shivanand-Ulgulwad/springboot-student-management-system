package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student { @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Course is required")
    private String course;

    @Min(value = 18, message = "Minimum age is 18")
    private int age;

    private String imageName;
}
