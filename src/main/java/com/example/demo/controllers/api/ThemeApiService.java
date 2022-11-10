package com.example.demo.controllers.api;
import com.example.demo.entities.Theme;
import com.example.demo.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ThemeApiService {
    @Autowired
    private ThemeService themeService;
    @GetMapping("theme/{id}")
    public ResponseEntity<Theme> getTheme(@PathVariable("id") int id)
    {
        Optional<Theme> theme = this.themeService.getTheme(id);

        return theme.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("themes")
    public List<Theme> getThemes() {
        return this.themeService.getThemes();
    }

    @PostMapping("theme")
    public Theme addTheme(@RequestBody Theme newTheme) {
        this.themeService.addTheme(newTheme);
        return newTheme;
    }

    @PutMapping("theme/{id}")
    public ResponseEntity<Theme> updateTheme(
            @PathVariable("id") int id,
            @RequestBody Theme newTheme
    ) {
        try {
            return ResponseEntity.ok().body(this.themeService.updateTheme(id, newTheme));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("theme/{id}")
    public void deleteTheme(@PathVariable("id") int id) {
        this.themeService.deleteTheme(id);
    }

}
