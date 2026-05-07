package com.app.service.impl;

import com.app.entity.Student;
import com.app.repository.StudentRepository;
import com.app.service.StudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @Override public Student saveStudent( Student student, MultipartFile file ) {
        if (!file.isEmpty()) { String fileName = UUID.randomUUID() + file.getOriginalFilename();
        try { Path path = Paths.get(uploadPath);
            if (!Files.exists(path)) { Files.createDirectories(path); }
            Files.copy( file.getInputStream(), path.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING );
            student.setImageName(fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        }
        return studentRepository.save(student); }

    @Override public Page<Student> getAllStudents(int page, String keyword ) {
        Pageable pageable = PageRequest.of( page, 5, Sort.by("name") );
        if (keyword != null && !keyword.isEmpty()) {
            return studentRepository .findByNameContainingIgnoreCase( keyword, pageable );
        }
        return studentRepository.findAll(pageable);
    }

    @Override public Student getStudentById(Long id) {
        return studentRepository .findById(id) .orElseThrow();
    }

    @Override public Student updateStudent(Long id, Student student, MultipartFile file ) {
        Student existing = getStudentById(id);
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setCourse(student.getCourse());
        existing.setAge(student.getAge());
        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID() + file.getOriginalFilename();
            try {
                Path path = Paths.get(uploadPath);
                Files.copy( file.getInputStream(),
                        path.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING );
                existing.setImageName(fileName);
            }
            catch (IOException e) {
                e.printStackTrace();
            } }
        return studentRepository.save(existing);
    }

    @Override public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
