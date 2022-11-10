package com.example.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 50)
    private String mail;

    @Column(nullable = false, unique = true, length = 25)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "lastname", nullable = false, length = 20)
    private String lastname;

    @Column(name = "firstname", nullable = false, length = 20)
    private String firstname;

    @Column(name= "isAdmin", nullable = false)
    private boolean isAdmin;

/*    @ManyToMany
    @JoinTable(name="historyquizzs",
    joinColumns = {@JoinColumn(name = "id")},
    inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private List<HistoryQuizz> historyQuizzs = new ArrayList<>();*/



    public User() {
    }

    public User(int id, String mail, String username, String password, String lastname, String firstname, boolean isAdmin) {
        this.id = id;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.isAdmin = isAdmin;
    }

    //GETTER & SETTER

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

/*    public List<HistoryQuizz> getHistoryQuizzs() {
        return historyQuizzs;
    }
    public void addHistoryQuizz(HistoryQuizz historyQuizz)
    {
        this.historyQuizzs.add(historyQuizz);
    }

    public void setHistoryQuizzs(List<HistoryQuizz> historyQuizzs) {
        this.historyQuizzs = historyQuizzs;
    }*/
}
