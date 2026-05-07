package com.app.repository;

import com.app.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Page<Student> findByNameContainingIgnoreCase(String keyword, Pageable pageable );
}
