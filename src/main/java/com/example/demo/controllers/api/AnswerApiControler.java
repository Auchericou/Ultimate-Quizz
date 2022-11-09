package com.example.demo.controllers.api;

import com.example.demo.entities.Answer;
import com.example.demo.entities.User;
import com.example.demo.services.AnswerService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api")
public class AnswerApiControler {
    @Autowired
    private AnswerService answerService;
    @GetMapping("answer/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable("id") int id)
    {
        Optional<Answer> answer = this.answerService.getAnswer(id);

        return answer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("answers")
    public List<Answer> getAnswers(@RequestParam(value = "id", required = false) int id) {
        if(id == 0) {
            return this.answerService.getAnswers();
        }
        return this.answerService.getAnswerById(id);
    }

    @PostMapping("answer")
    public Answer addAnswer(@RequestBody Answer newAnswer) {
        this.answerService.addAnswer(newAnswer);
        return newAnswer;
    }

    @PutMapping("answer/{id}")
    public ResponseEntity<Answer> updateAnswer(
            @PathVariable("id") int id,
            @RequestBody Answer newAnswer
    ) {
        try {
            return ResponseEntity.ok().body(this.answerService.updateAnswer(id, newAnswer));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("answer/{id}")
    public void deleteAnswer(@PathVariable("id") int id) {
        this.answerService.deleteAnswer(id);
    }

}
