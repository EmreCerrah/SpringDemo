package com.springDemo.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService  {
    // Hibernetin gucunu kullanarak databese baslanti yapip verileri yoneten sinif
    private final StudentRepositorty studentRepositorty;
    @Autowired
    public StudentService(StudentRepositorty studentRepositorty) {
        this.studentRepositorty = studentRepositorty;
    }

    public List<Student> getStudents(){
        return studentRepositorty.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepositorty.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw  new IllegalStateException("email taken");
        }
        studentRepositorty.save(student);
    }

    public void deleteStuden(Long id) {
        boolean exists = studentRepositorty.existsById(id);
        if (!exists){
            throw new IllegalStateException("Student Not Found!");
        }
        studentRepositorty.deleteById(id);
    }
    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepositorty.findById(id).orElseThrow(()->
            new IllegalStateException("Student Not Found!")
        );
        name.trim();
        email.trim();
        if (name !=null && name.length()>0 ){
            student.setName(name);
        }
        if (email !=null && email.length()>0 ){
            Optional<Student> studentByEmail = studentRepositorty.findStudentByEmail(student.getEmail());
            if (studentByEmail.isPresent()){
                throw  new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
        studentRepositorty.save(student);


    }
}
