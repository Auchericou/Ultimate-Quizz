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

/*    @ManyToOne
    private User user;*/

/*    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<Quizz> quizzs = new ArrayList<>();*/

    @ManyToOne
    @JoinColumn(name = "quizz_id")
    private Quizz quizz;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(nullable = false)
    private Integer point;

    public HistoryQuizz() {
    }

    public HistoryQuizz(int id, User user, Quizz quizz, Integer point) {
        this.id = id;
        this.user = user;
        this.quizz = quizz;
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

    public Quizz getQuizz() {
        return quizz;
    }

    public void setQuizz(List<Quizz> quizzs) {
        this.quizz = quizz;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
