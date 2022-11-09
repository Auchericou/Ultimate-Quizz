/*
package com.example.demo.controllers.view;

import com.example.demo.entities.Personne;
import com.example.demo.services.AnnuaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AnnuaireMvcController {

    @Autowired
    private AnnuaireService annuaireService;

    @GetMapping("/annuaire")
    public String getAnnuaireView(Model model) {
        model.addAttribute("personnes", annuaireService.getPersonnes());
        return "";
    }

    @GetMapping("/ajouter/personne")
    public String addPersonnePage() {
        return "";
    }

    @PostMapping("/ajouter/addPersonne")
    public void addPersonne(Personne personne, Model model, HttpServletResponse httpServletResponse) throws IOException {
        annuaireService.addPersonne(personne);
        httpServletResponse.sendRedirect("/annuaire");
    }
}
*/
