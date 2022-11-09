package com.example.demo.repositories;
import com.example.demo.entities.Answer;
import com.example.demo.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
public List<Answer> findAllById(int id);
}
