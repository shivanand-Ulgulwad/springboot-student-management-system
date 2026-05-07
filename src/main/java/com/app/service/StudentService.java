package com.app.service;

import com.app.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    Student saveStudent(Student student, MultipartFile file); 
    Page<Student> getAllStudents(int page, String keyword);
    Student getStudentById(Long id); Student
    updateStudent(Long id, Student student, MultipartFile file);
    void deleteStudent(Long id);
}
