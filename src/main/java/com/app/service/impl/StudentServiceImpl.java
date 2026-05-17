package com.app.service.impl;

import com.app.entity.Student;
import com.app.exception.FileStorageException;
import com.app.exception.InvalidFileException;
import com.app.exception.StudentNotFoundException;
import com.app.repository.StudentRepository;
import com.app.service.StudentService;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Value("${upload.path}")
    private String uploadPath;


    @Override
    public Student saveStudent(Student student,
                               MultipartFile file) {

        validateStudent(student);

        if (file != null && !file.isEmpty()) {

            validateFile(file);

            String fileName = uploadFile(file);

            student.setImageName(fileName);
        }

        return studentRepository.save(student);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Student> getAllStudents(int page,
                                        String keyword) {

        Pageable pageable =
                PageRequest.of(page, 5,
                        Sort.by("name"));

        if (keyword != null &&
                !keyword.trim().isEmpty()) {

            return studentRepository
                    .findByNameContainingIgnoreCase(
                            keyword,
                            pageable
                    );
        }

        return studentRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(id));
    }


    @Override
    public Student updateStudent(Long id,
                                 Student student,
                                 MultipartFile file) {

        Student existing =
                getStudentById(id);

        validateStudent(student);

        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setCourse(student.getCourse());
        existing.setAge(student.getAge());

        if (!file.isEmpty()) {

            validateFile(file);

            String fileName = uploadFile(file);

            existing.setImageName(fileName);
        }

        return studentRepository.save(existing);
    }



    @Override
    public void deleteStudent(Long id) {

        Student student = getStudentById(id);

        try {

            studentRepository.delete(student);

        } catch (DataIntegrityViolationException ex) {

            log.error("Unable to delete student with id {}",
                    id,
                    ex);

            throw ex;
        }
    }



    private String uploadFile(MultipartFile file) {

        try {

            Path uploadDir = Paths.get(uploadPath);

            if (!Files.exists(uploadDir)) {

                Files.createDirectories(uploadDir);
            }

            String fileName =
                    UUID.randomUUID()
                            + "_"
                            + file.getOriginalFilename();

            Path filePath =
                    uploadDir.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return fileName;

        } catch (IOException ex) {

            log.error("File upload failed", ex);

            throw new FileStorageException(
                    "Failed to upload file."
            );
        }
    }



    private void validateFile(MultipartFile file) {

        List<String> allowedTypes = List.of(
                "image/png",
                "image/jpeg",
                "image/jpg"
        );

        if (!allowedTypes.contains(
                file.getContentType())) {

            throw new InvalidFileException(
                    "Only PNG and JPG images are allowed."
            );
        }

        long maxSize = 5 * 1024 * 1024;

        if (file.getSize() > maxSize) {

            throw new InvalidFileException(
                    "File size must be less than 5MB."
            );
        }
    }


    private void validateStudent(Student student) {

        if (student.getAge() < 5 ||
                student.getAge() > 100) {

            throw new IllegalArgumentException(
                    "Age must be between 5 and 100."
            );
        }

        if (student.getName() == null ||
                student.getName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Student name is required."
            );
        }
    }
}