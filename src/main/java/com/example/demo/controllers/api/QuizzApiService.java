package com.example.demo.controllers.api;
import com.example.demo.entities.Quizz;
import com.example.demo.services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class QuizzApiService {
    @Autowired
    private QuizzService quizzService;
    @GetMapping("quizz/{id}")
    public ResponseEntity<Quizz> getQuizz(@PathVariable("id") int id)
    {
        Optional<Quizz> quizz = this.quizzService.getQuizz(id);

        return quizz.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("quizzs")
    public List<Quizz> getQuizzs() {
        return this.quizzService.getQuizzs();
    }

    @PostMapping("quizz")
    public Quizz addQuizz(@RequestBody Quizz newQuizz) {
        this.quizzService.addQuizz(newQuizz);
        return newQuizz;
    }

    @PutMapping("quizz/{id}")
    public ResponseEntity<Quizz> updateQuizz(
            @PathVariable("id") int id,
            @RequestBody Quizz newQuizz
    ) {
        try {
            return ResponseEntity.ok().body(this.quizzService.updateQuizz(id, newQuizz));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("quizz/{id}")
    public void deleteQuizz(@PathVariable("id") int id) {
        this.quizzService.deleteQuizz(id);
    }

}
