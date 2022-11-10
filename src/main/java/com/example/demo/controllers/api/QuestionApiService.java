package com.example.demo.controllers.api;
import com.example.demo.entities.Answer;
import com.example.demo.entities.Question;
import com.example.demo.entities.User;
import com.example.demo.services.QuestionService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class QuestionApiService {
    @Autowired
    private QuestionService questionService;
    @GetMapping("question/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable("id") int id)
    {
        Optional<Question> question = this.questionService.getQuestion(id);

        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("questions")
    public List<Question> getQuestions() {
        return this.questionService.getQuestions();
    }

    @PostMapping("question")
    public Question addQuestion(@RequestBody Question newQuestion) {
        this.questionService.addQuestion(newQuestion);
        return newQuestion;
    }

    @PutMapping("question/{id}")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable("id") int id,
            @RequestBody Question newQuestion
    ) {
        try {
            return ResponseEntity.ok().body(this.questionService.updateQuestion(id, newQuestion));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("question/{id}")
    public void deleteQuestion(@PathVariable("id") int id) {
        this.questionService.deleteQuestion(id);
    }

}
