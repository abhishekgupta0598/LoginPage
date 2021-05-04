package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/student")
    public Iterable<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable String id){
        return studentRepository.findById(id).orElseThrow(null);
    }

    @GetMapping("student/{studentId}/course/{id}")
    public Course getCourse(@PathVariable String id){
        return courseRepository.findById(id).orElseThrow(null);
    }

    @PostMapping("/student")
    public void createStudent(@RequestBody Student data){
        studentRepository.save(data);
    }

    @PostMapping("/student/{id}/course")
    public void createCourse(@RequestBody Course data, @PathVariable String id){
        Student s = getStudent(id);
        s.setCourse(data, data.getId());
        courseRepository.save(data);
    }

    @PutMapping("/student/{id}")
    public void updateStudent(@RequestBody Student data, @PathVariable String id){
        Student s = getStudent(id);
        if(s.getId().equals(id)){
            studentRepository.save(data);
        }
        else {
            studentRepository.save(data);
        }
    }

    @PutMapping("/student/{studentId}/course/{id}")
    public void updateCourse(@PathVariable String id, @RequestBody Course data){
        Course c = getCourse(id);
        if(c.getId().equals(id)){
            courseRepository.save(data);
        }
        else {
            courseRepository.save(data);
        }
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable String id){
        studentRepository.deleteById(id);
    }
}
