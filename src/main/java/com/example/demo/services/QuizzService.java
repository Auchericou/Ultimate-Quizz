package com.example.demo.services;

import com.example.demo.entities.Quizz;
import com.example.demo.entities.Quizz;
import com.example.demo.repositories.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizzService {
    @Autowired
    private QuizzRepository quizzRepository;

    public QuizzService(ArrayList<Quizz> quizzs) {

    }

    public List<Quizz> getQuizzs() {
        return quizzRepository.findAll();
    }

    public List<Quizz> getQuizzById(int id) {
        return quizzRepository.findAllById(id);
    }

    public Optional<Quizz> getQuizz(int id) {
        return quizzRepository.findById(id);
    }

    public void addQuizz(Quizz quizz) {
        quizzRepository.save(quizz);
    }

    public Quizz updateQuizz(int id, Quizz quizz) throws Exception {
        Optional<Quizz> quizzExist = getQuizz(id);

        if (quizzExist.isPresent())
        {
            quizz.setId(id);
            return quizzRepository.save(quizz);
        }
        else {
            throw new Exception();
        }
    }

    public void deleteQuizz(int id) {
        this.quizzRepository.deleteById(id);
    }
}
