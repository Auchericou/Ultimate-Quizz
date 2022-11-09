package com.example.demo.services;
import com.example.demo.entities.Answer;
import com.example.demo.entities.User;
import com.example.demo.repositories.AnswerRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public AnswerService(ArrayList<Answer> Answers) {

    }

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    public List<Answer> getAnswerById(int id) {
        return answerRepository.findAllById(id);
    }

    public Optional<Answer> getAnswer(int id) {
        return answerRepository.findById(id);
    }

    public void addAnswer(Answer user) {
        answerRepository.save(user);
    }

    public Answer updateAnswer(int id, Answer answer) throws Exception {
        Optional<Answer> answerExist = getAnswer(id);

        if (answerExist.isPresent())
        {
            answer.setId(id);
            return answerRepository.save(answer);
        }
        else {
            throw new Exception();
        }
    }

    public void deleteAnswer(int id) {
        this.answerRepository.deleteById(id);
    }
}
