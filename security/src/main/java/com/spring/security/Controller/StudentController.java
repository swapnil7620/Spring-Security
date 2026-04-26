package com.spring.security.Controller;


import com.spring.security.Entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {


    private List<Student> students = new ArrayList<>(List.of(
            new Student("swapnil", 1, 80),
            new Student("Tejas", 2, 90),
            new Student(" Nishnat", 1, 80)

    ));

    @GetMapping("/getAll")
    public List<Student> getStudent() {
        return students;
    }


    @GetMapping("/getToken")
    public CsrfToken getToken(HttpServletRequest session){
        return (CsrfToken) session.getAttribute("_csrf");

    }
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;

    }
}
