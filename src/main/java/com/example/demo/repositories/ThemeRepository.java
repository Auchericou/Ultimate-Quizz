package com.example.demo.repositories;

import com.example.demo.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    public List<Theme> findAllById(int id);
}
