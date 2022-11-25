package com.example.sprinbootdemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> students = studentRepository.
                findStudentByEmail(student.getEmail());
        if(students.isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists)
            throw new IllegalStateException("Student with "+ id + " not exists in DB");
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String emailId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists)
            throw new IllegalStateException("Student with "+ studentId + " not exists in DB");
        Student student = studentRepository.findById(studentId).
                orElseThrow(() ->  new IllegalStateException("Student with "+ studentId + " not exists in DB"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(emailId != null && emailId.length() > 0 && !Objects.equals(student.getEmail(),emailId)){
            Optional<Student> students = studentRepository.findStudentByEmail(emailId);
            if(students.isPresent()){
                throw new IllegalStateException("EmailId Taken");
            }
            student.setEmail(emailId);
        }

    }
}
