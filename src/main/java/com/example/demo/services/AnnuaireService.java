package com.example.demo.services;

import com.example.demo.entities.Personne;
import com.example.demo.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnnuaireService {

    @Autowired
    private PersonneRepository personneRepository;

    public AnnuaireService(ArrayList<Personne> personnes) {
//        Personne p1 = new Personne("Alain", "Delon", 55);
//        Personne p2 = new Personne("Hovannes", "Markarian", 26);
//
//        addPersonne(p1);
//        addPersonne(p2);
    }

    public List<Personne> getPersonnes() {
        return personneRepository.findAll();
    }
    public List<Personne> getPersonnesByNom(String nom) {
        return personneRepository.findAllByNom(nom);
    }

    public Optional<Personne> getPersonne(int id) {
        return personneRepository.findById(id);
    }

    public void addPersonne(Personne personne) {
        personneRepository.save(personne);
    }

    public Personne updatePersonne(int id, Personne personne) throws Exception {
        Optional<Personne> personneExistante = getPersonne(id);

        if (personneExistante.isPresent()) {
            personne.setId(id);
            return personneRepository.save(personne);
        } else {
            throw new Exception();
        }
    }
    public void deletePersonne(int id) {
        this.personneRepository.deleteById(id);
    }
}
