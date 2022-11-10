package com.example.demo.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzs")
public class Quizz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false)
    private LocalDate updateDate;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne
    private Theme theme;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<Question> questions = new ArrayList<>();

/*    @ManyToMany
    @JoinTable(name="historyquizzs",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "quizzs_id")})
    private List<HistoryQuizz> historyQuizzs = new ArrayList<>();*/

    public Quizz() {
    }

    public Quizz(int id, LocalDate creationDate, LocalDate updateDate, Boolean isActive, Theme theme, List<Question> questions) {
        this.id = id;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.isActive = isActive;
        this.theme = theme;
        this.questions = questions;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        if(questions.size() > 5 && questions.size() < 20) {
            this.questions = questions;
        }
    }
}
