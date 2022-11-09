package com.example.demo.entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "historyquizzs")
public class HistoryQuizz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizz_id")
    private List<Quizz> quizzs = new ArrayList<>();

    @Column(nullable = false)
    private Integer point;

    public HistoryQuizz() {
    }

    public HistoryQuizz(int id, User user, List<Quizz> quizzs, Integer point) {
        this.id = id;
        this.user = user;
        this.quizzs = quizzs;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Quizz> getQuizzs() {
        return quizzs;
    }

    public void setQuizzs(List<Quizz> quizzs) {
        this.quizzs = quizzs;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
