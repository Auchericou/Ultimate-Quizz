package com.example.demo.repositories;


import com.example.demo.entities.Question;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    public List<Question> findAllById(int id);
}
