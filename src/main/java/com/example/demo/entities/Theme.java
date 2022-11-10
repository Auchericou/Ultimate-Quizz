package com.example.demo.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "themes")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false , unique = true , length = 255)
    private String name;

    public Theme() {
    }

    public Theme(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<Quizz> quizzs = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quizz> getQuizzs() {
        return quizzs;
    }

    public void setThemes(List<Quizz> quizzs) {
            this.quizzs = quizzs;
    }
}
