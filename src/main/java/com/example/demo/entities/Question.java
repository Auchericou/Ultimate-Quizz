package com.example.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String label;

    @ManyToOne
    private Quizz quizz;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<Answer> answers = new ArrayList<>();

    public Question() {
    }

    public Question(int id, String label, Quizz quizz, List<Answer> answers) {
        this.id = id;
        this.label = label;
        this.quizz = quizz;
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Quizz getQuizz() {
        return quizz;
    }

    public void setQuizz(Quizz quizz) {
        this.quizz = quizz;
    }
}
