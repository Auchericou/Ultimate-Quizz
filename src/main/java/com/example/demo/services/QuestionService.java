package com.example.demo.services;

import com.example.demo.entities.Question;
import com.example.demo.entities.User;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public QuestionService(ArrayList<Question> questions) {

    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionById(int id) {
        return questionRepository.findAllById(id);
    }

    public Optional<Question> getQuestion(int id) {
        return questionRepository.findById(id);
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    public Question updateQuestion(int id, Question question) throws Exception {
        Optional<Question> questionExist = getQuestion(id);

        if (questionExist.isPresent())
        {
            question.setId(id);
            return questionRepository.save(question);
        }
        else {
            throw new Exception();
        }
    }

    public void deleteQuestion(int id) {
        this.questionRepository.deleteById(id);
    }
}
