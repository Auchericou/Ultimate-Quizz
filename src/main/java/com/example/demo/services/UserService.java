package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(ArrayList<User> users) {

    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getUserByUsername(String username) {
        return userRepository.findAllByUsername(username);
    }

    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }


    public User updateUser(int id, User user) throws Exception {
        Optional<User> userExist = getUser(id);

        if (userExist.isPresent())
        {
            user.setId(id);
            return userRepository.save(user);
        }
        else {
            throw new Exception();
        }
    }

    public void deleteUser(int id) {
        this.userRepository.deleteById(id);
    }
}
