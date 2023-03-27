package es.taw.grupo25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    @GetMapping("/")
    public String showOptions(){
        return "empresa/index";
    }

    @GetMapping("/registrar")
    public String doRegister(){
        return "empresa/register";
    }

    @PostMapping("/registrar")
    public String registerEmpresa(){

        return "redirect:/empresa/";
    }
}
