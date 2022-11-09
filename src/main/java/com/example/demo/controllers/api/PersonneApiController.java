package com.example.demo.controllers.api;

import com.example.demo.entities.Personne;
import com.example.demo.services.AnnuaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class PersonneApiController {

    @Autowired
    private AnnuaireService annuaireService;
    @GetMapping("personne/{id}")
    public ResponseEntity<Personne> getPersonne(@PathVariable("id") int id) {
        Optional<Personne> personne = this.annuaireService.getPersonne(id);

        return personne.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("personnes")
    public List<Personne> getPersonnes(@RequestParam(value = "nom", required = false) String nom) {
        if(nom == null) {
            return this.annuaireService.getPersonnes();
        }
        return this.annuaireService.getPersonnesByNom(nom);
    }

    @PostMapping("personne")
    public Personne addPersonne(@RequestBody Personne newPersonne) {
        this.annuaireService.addPersonne(newPersonne);
        return newPersonne;
    }
    @PutMapping("personne/{id}")
    public ResponseEntity<Personne> updatePersonne(
            @PathVariable("id") int id,
            @RequestBody Personne newPersonne
    ) {
        try {
            return ResponseEntity.ok().body(this.annuaireService.updatePersonne(id, newPersonne));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("personne/{id}")
    public void deletePersonne(@PathVariable("id") int id) {
        this.annuaireService.deletePersonne(id);
    }

}
