package com.example.demo.repositories;


import com.example.demo.entities.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizzRepository extends JpaRepository<Quizz, Integer> {
    public List<Quizz> findAllById(int id);
}
