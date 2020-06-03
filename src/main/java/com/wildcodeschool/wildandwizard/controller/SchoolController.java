package com.wildcodeschool.wildandwizard.controller;

import com.wildcodeschool.wildandwizard.repository.SchoolRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SchoolController {

    private SchoolRepository repository = new SchoolRepository();

    // Exemple : http://localhost:8080/schools
    @GetMapping("/schools")
    public String getAll(Model model) {

        model.addAttribute("schools", repository.findAll());

        return "school_get_all";
    }

    // Exemple 1:  http://localhost:8080/school?id=5
    // Exemple 2: http://localhost:8080/school?country=USA
    @GetMapping("/school")
    public String getById(Model model, @RequestParam(value = "id", required = false) Long id, @RequestParam(value = "country", required = false) String country) {

        if ( id != null ) { model.addAttribute("school", repository.findById(id)); return "school_get"; }

        if ( country != null ) { model.addAttribute("schools", repository.findByCountry(country)); return "school_get_all"; }

        return "school_get_all";
    }

    // Exemple : http://localhost:8080/schools/search?country=USA
    @GetMapping("/schools/search")
    public String getByCountry(Model model, @RequestParam String country) {

        model.addAttribute("schools", repository.findByCountry(country));

        return "school_get_all";
    }

}