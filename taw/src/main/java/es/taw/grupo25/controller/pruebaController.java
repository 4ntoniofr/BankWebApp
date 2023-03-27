package es.taw.grupo25.controller;

import es.taw.grupo25.repository.RolClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class pruebaController {
    @Autowired
    private RolClienteRepository rep;
    @GetMapping("/")
    public String d(Model model){
        model.addAttribute("lista",rep.findAll());
        return "prueba";
    }
}
