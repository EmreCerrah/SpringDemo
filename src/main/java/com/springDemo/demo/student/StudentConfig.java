package com.springDemo.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepositorty repositorty){
        return  args -> {
            Student emre = new Student(
                    "Emre", "emre@gmail.com", 1994
            );
            Student irmak = new Student(
                    "Irmak", "irmak@gmail.com",1994
            );
            repositorty.saveAll(
                    List.of(emre,irmak)
            );
        };
    }
}
