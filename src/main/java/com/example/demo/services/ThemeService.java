package com.example.demo.services;

import com.example.demo.entities.Theme;
import com.example.demo.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    public ThemeService(ArrayList<Theme> themes) {

    }

    public List<Theme> getThemes() {
        return themeRepository.findAll();
    }

    public List<Theme> getThemeById(int id) {
        return themeRepository.findAllById(id);
    }

    public Optional<Theme> getTheme(int id) {
        return themeRepository.findById(id);
    }

    public void addTheme(Theme theme) {
        themeRepository.save(theme);
    }

    public Theme updateTheme(int id, Theme theme) throws Exception {
        Optional<Theme> themeExist = getTheme(id);

        if (themeExist.isPresent())
        {
            theme.setId(id);
            return themeRepository.save(theme);
        }
        else {
            throw new Exception();
        }
    }

    public void deleteTheme(int id) {
        this.themeRepository.deleteById(id);
    }
}
