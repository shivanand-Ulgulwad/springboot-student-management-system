package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.app.entity.Student;
import com.app.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String students(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String keyword, Model model ) {
        Page<Student> studentPage = studentService.getAllStudents( page, keyword );
        model.addAttribute( "students", studentPage.getContent() );
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute( "currentPage", page );
        model.addAttribute( "totalPages", studentPage.getTotalPages() );
        model.addAttribute( "keyword", keyword ); return "students";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute( "student", new Student() );
        return "student-form";
    }

    @PostMapping
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult result, @RequestParam(value = "file", required = false) MultipartFile file ) {
        if (result.hasErrors()) { return "student-form";
        }
        studentService.saveStudent( student, file );
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model ) {
        model.addAttribute( "student", studentService.getStudentById(id) );
        return "student-edit";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult result,
                                @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        if (result.hasErrors()) {
            return "edit_student";
        }

        Student existingStudent = studentService.getStudentById(id);

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setAge(student.getAge());

        // Update image only if new image uploaded
        if (file != null && !file.isEmpty()) {

            String uploadDir = "uploads/";

            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            String fileName = file.getOriginalFilename();

            Path filePath = Paths.get(uploadDir, fileName);

            Files.copy(file.getInputStream(), filePath,
                    StandardCopyOption.REPLACE_EXISTING);

            existingStudent.setImageName(fileName);
        }

        studentService.saveStudent(existingStudent, file);

        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id ) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
