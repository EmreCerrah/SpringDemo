package com.springDemo.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepositorty extends JpaRepository<Student, Long> {
        // Jpa interfade'ini kullanmak icin kaydetme silme islemlerini hibernet sayesinda calistirabilmek icin ectende edilen sinif

    @Query("SELECT s FROM Student s WHERE s.email=?1")
    Optional<Student> findStudentByEmail(String email);
}
